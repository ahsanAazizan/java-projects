import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Panel extends JPanel implements ActionListener {
    // Declaration
    static final int SCREEN_WIDTH = 600, SCREEN_HEIGHT = 600, UNIT_SIZE = 40,
            GAME_UNIT = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE, DELAY = 80;
    final int x[] = new int[GAME_UNIT], y[] = new int[GAME_UNIT];
    int body = 5;
    int foodEaten;
    boolean running = true;
    int foodX, foodY;
    char dir = 'R';
    Timer timer;
    Random rand;

    Panel(){
        rand = new Random();
        this.setFocusable(true);
        this.addKeyListener(new MyKey());
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        start();
    }
    public void start(){
        newFood();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void newFood(){
        foodX = rand.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        foodY = rand.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void gameOver(Graphics g){
        // Score
        g.setColor(Color.red);
        g.setFont(new Font("SansSerif", Font.BOLD, 35));
        FontMetrics scoreMetrics = getFontMetrics(g.getFont());
        g.drawString("SCORE : " + foodEaten, (SCREEN_WIDTH - scoreMetrics.stringWidth("SCORE : " + foodEaten)) / 2, g.getFont().getSize());

        // Game over screen
        String gameOver = "GAME OVER";
        g.setColor(Color.red);
        g.setFont(new Font("SansSerif", Font.BOLD, 70));
        FontMetrics met = getFontMetrics(g.getFont());
        g.drawString(gameOver, (SCREEN_WIDTH - met.stringWidth(gameOver)) / 2, SCREEN_HEIGHT / 2);
    }

    public void draw(Graphics g){
        if (running){
            /* debugging code
            for(int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++){
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_WIDTH);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            } */
            g.setColor(new Color(1, 77, 16));
            g.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < body; i++){
                if (i == 0){
                    g.setColor(new Color(0, 112, 22));
                } else {
                    g.setColor(new Color(0, 199, 40));
                }
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
            g.setColor(Color.red);
            g.setFont(new Font("SansSerif", Font.BOLD, 35));
            FontMetrics met = getFontMetrics(g.getFont());
            g.drawString("SCORE : " + foodEaten, (SCREEN_WIDTH - met.stringWidth("SCORE : " + foodEaten)) / 2, g.getFont().getSize());
        }else gameOver(g);
    }

    public void move(){
        for (int i = body; i > 0; i--){
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (dir){
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void paint(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void checkCollision(){
        // Check if head collides with the body
        for (int i = body; i > 0; i--){
            if ((x[0] == x[i]) && (y[0] == y[i])){
                running = false;
            }
        }

        // Check if head touches border
        if (x[0] < 0 || x[0] > SCREEN_WIDTH || y[0] < 0 || y[0] > SCREEN_HEIGHT){
            running = false;
        }

        if(!running) timer.stop();
    }

    public void checkFood(){
        if ((x[0] == foodX) && (y[0] == foodY)){
            body++;
            foodEaten++;
            newFood();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if (running){
            move();
            checkFood();
            checkCollision();
        }
        repaint();
    }

    public class MyKey extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent key){
            switch (key.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if (dir != 'R'){
                        dir = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (dir != 'L'){
                        dir = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (dir != 'D'){
                        dir = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (dir != 'U'){
                        dir = 'D';
                    }
                    break;
            }
        }
    }
}
