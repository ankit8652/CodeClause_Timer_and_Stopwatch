import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CountdownTimer implements ActionListener {

    private JFrame frame;
    private JLabel timeLabel;
    private JButton startButton;
    private JButton stopButton;
    private Timer timer;
    private int remainingTime;
    private boolean isRunning;

    public CountdownTimer() {
        frame = new JFrame("Countdown Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 500);
        frame.setLayout(new GridLayout(5, 3));

        JLabel inputLabel = new JLabel("Enter countdown time (in minutes):");
        inputLabel.setFont(new Font("Tahoma", Font.PLAIN,35));
        frame.add(inputLabel);

        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Tahoma", Font.PLAIN, 35));
        inputField.setSize(50, 50);
        frame.add(inputField);

        


        JButton setButton = new JButton("Set");
        setButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
        setButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                try {
                    int minutes = Integer.parseInt(input);
                    setCountdownTime(minutes);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a number.");
                }
            }
        });
        frame.add(setButton);

        JPanel timePanel = new JPanel();
        timePanel.setLayout(new FlowLayout());
        timeLabel = new JLabel("00:00");
        timeLabel.setFont(new Font("Tahoma", Font.BOLD, 80));
        timePanel.add(timeLabel);
        frame.add(timePanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        startButton = new JButton("Start");
        startButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        startButton.addActionListener(this);
        buttonPanel.add(startButton);

        stopButton = new JButton("Stop");
        stopButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        stopButton.addActionListener(this);
        buttonPanel.add(stopButton);

        frame.add(buttonPanel);

        stopButton.setBackground(Color.decode("#B70404"));
        startButton.setBackground(Color.decode("#5D9C59"));

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                updateTimeLabel();
                if (remainingTime <= 0) {
                    timer.stop();
                    isRunning = false;
                    startButton.setEnabled(true);
                }
            }
        });

        isRunning = false;

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (!isRunning) {
                timer.start();
                isRunning = true;
                startButton.setEnabled(false);
            }
        } else if (e.getSource() == stopButton) {
            timer.stop();
            isRunning = false;
            startButton.setEnabled(true);
            updateTimeLabel();
        }
    }

    private void setCountdownTime(int minutes) {
        remainingTime = minutes * 60;
        updateTimeLabel();
    }

    private void updateTimeLabel() {
        int minutes = remainingTime / 60;
        int seconds = remainingTime % 60;
        String timeString = String.format("%02d:%02d", minutes, seconds);
        timeLabel.setText(timeString);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CountdownTimer();
            }
        });
    }
}
