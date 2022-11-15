import javax.swing.*;

public class Frame extends JFrame {
    Frame(){
        this.add(new Panel());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Snake Game");
        this.pack();
        this.setLocationRelativeTo(null); // Make the frame appear in the middle of the computer's screen
    }
}
