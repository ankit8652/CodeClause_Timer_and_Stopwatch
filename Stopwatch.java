import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Stopwatch implements ActionListener {

    JFrame frame = new JFrame("Stopwatch");
    JButton start = new JButton("START");
    JButton stop = new JButton("RESET");
    JLabel timeLabel = new JLabel();
    int elapsedTime = 0;
    int seconds = 0;
    int minute = 0;
    int hours = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minute);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            elapsedTime = elapsedTime + 1000;
            hours = (elapsedTime / 3600000);
            minute = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minute);
            hours_string = String.format("%02d", hours);
            timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        }
    });

    public Stopwatch() {

        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(50, 50, 300, 60);
        timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setBackground(Color.decode("#A2A8D3")); // Set the background color

        start.setBounds(100, 150, 100, 50);
        start.setBackground(Color.GREEN);
        start.setFont(new Font("Tahoma", Font.PLAIN, 20));
        start.setFocusable(false);
        start.setBackground(Color.decode("#5CB85C")); // Set the background color
        start.addActionListener(this);

        stop.setBounds(210, 150, 100, 50);
        stop.setBackground(Color.RED);
        stop.setFont(new Font("Tahoma", Font.PLAIN, 20));
        stop.setFocusable(false);
        stop.setBackground(Color.decode("#D9534F")); // Set the background color
        stop.addActionListener(this);

        frame.add(start);
        frame.add(stop);
        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode("#DBDFFD")); // Set the background color
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {

            if (started == false) {
                started = true;
                start.setText("STOP");
                start();
            } else {
                started = false;
                start.setText("START");
                stop();
            }

        }
        if (e.getSource() == stop) {
            started = false;
            start.setText("START");
            reset();
        }

    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minute = 0;
        hours = 0;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minute);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    }

}
