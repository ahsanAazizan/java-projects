import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {
    JFrame frame = new JFrame("Simple Ass Timer");
    JButton start = new JButton("Start"), reset = new JButton("Reset");
    JLabel label = new JLabel(), dateLabel = new JLabel();
    int elapsedTime = 0;
    int seconds = 0, min = 0, hours = 0;
    boolean hasStarted = false;
    String secondsString = String.format("%02d", seconds), minString = String.format("%02d", min),
            hoString = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime += 1000;
            hours = elapsedTime / 3600000;
            min = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;

            hoString = String.format("%02d", hours);
            minString = String.format("%02d", min);
            secondsString = String.format("%02d", seconds);
            label.setText(hoString + " : " + minString + " : " + secondsString);
        }
    });

    Main(){
        label.setText(hoString+" : "+minString+" : "+secondsString);
        label.setBounds(100,100,200,100);
        label.setFont(new Font("SansSerif",Font.BOLD,35));
        label.setBorder(BorderFactory.createBevelBorder(1));
        label.setOpaque(true);
        label.setHorizontalAlignment(JTextField.CENTER);

        start.setBounds(100,300,100,50);
        start.setFont(new Font("SansSerif",Font.PLAIN,20));
        start.setFocusable(false);
        start.addActionListener(this);

        reset.setBounds(200,300,100,50);
        reset.setFont(new Font("SansSerif",Font.PLAIN,20));
        reset.setFocusable(false);
        reset.addActionListener(this);

        frame.add(start);
        frame.add(reset);
        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == start){
            if (!hasStarted){
                hasStarted = true;
                start.setText("Stop");
                // start the timer
                timer.start();
            }else {
                hasStarted = false;
                start.setText("Start");
                // stop the timer
                timer.stop();
            }
        }

        if (ae.getSource() == reset){
            hasStarted = false;
            start.setText("Start");

            // reset the timer
            timer.stop();
            elapsedTime = 0;
            seconds = 0;
            min = 0;
            hours = 0;
            // update the label
            hoString = String.format("%02d", hours);
            minString = String.format("%02d", min);
            secondsString = String.format("%02d", seconds);
            label.setText(hoString + " : " + minString + " : " + secondsString);
        }
    }
}
