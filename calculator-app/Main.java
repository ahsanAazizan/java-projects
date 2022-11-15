import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main implements ActionListener {
    JFrame frame = new JFrame("Simple Ass Calculator");
    JTextField field;
    JButton numButtons[] = new JButton[10], funcButtons[] = new JButton[9],
            add =  new JButton("+"), sub = new JButton("-"), mult = new JButton("*"), div =  new JButton("/"),
            dec = new JButton("."), eq = new JButton("="), del = new JButton("del"), clr = new JButton("C"),
            neg = new JButton("(-)");
    JPanel panel = new JPanel();
    double firstNum, secondNum, res;
    char op;
    Font font = new Font("Monospaced", Font.BOLD, 25);


    // Calculator constructor
    Main(){
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        field = new JTextField();
        field.setBounds(50, 25, 300, 50);
        field.setFont(font);
        field.setEditable(false);
        frame.add(field);

        funcButtons[0] = add;
        funcButtons[1] = sub;
        funcButtons[2] = mult;
        funcButtons[3] = div;
        funcButtons[4] = dec;
        funcButtons[5] = eq;
        funcButtons[6] = del;
        funcButtons[7] = clr;
        funcButtons[8] = neg;

        for (int i = 0; i < funcButtons.length; i++){
            funcButtons[i].addActionListener(this);
            funcButtons[i].setFont(font);
            funcButtons[i].setFocusable(false);

        }

        for (int i = 0; i < 10; i++){
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].addActionListener(this);
            numButtons[i].setFont(font);
            numButtons[i].setFocusable(false);
        }

        del.setBounds(50, 430, 100, 50);
        clr.setBounds(250, 430, 100, 50);
        neg.setBounds(150, 430, 100, 50);

        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numButtons[1]);
        panel.add(numButtons[2]);
        panel.add(numButtons[3]);

        panel.add(add);

        panel.add(numButtons[4]);
        panel.add(numButtons[5]);
        panel.add(numButtons[6]);

        panel.add(sub);

        panel.add(numButtons[7]);
        panel.add(numButtons[8]);
        panel.add(numButtons[9]);

        panel.add(mult);
        panel.add(dec);
        panel.add(numButtons[0]);
        panel.add(eq);
        panel.add(div);

        frame.add(panel);
        frame.add(neg);
        frame.add(del);
        frame.add(clr);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        for (int i = 0; i < 10; i++){
            if (ae.getSource() == numButtons[i]){
                field.setText(field.getText().concat(String.valueOf(i)));
            }
        }

        if (ae.getSource() == dec)
            field.setText(field.getText().concat("."));
        if(ae.getSource() == add || ae.getSource() == sub || ae.getSource() == mult || ae.getSource() == div){
            firstNum = Double.parseDouble(field.getText());
            field.setText("");
            op = (ae.getSource() == add) ? '+' : (ae.getSource() == sub) ? '-' : (ae.getSource() == mult) ? '*' : (ae.getSource() == div) ? '/' : 'x';
        }

        if (ae.getSource() == eq){
            secondNum = Double.parseDouble(field.getText());
            switch (op){
                case '+':
                    res = firstNum + secondNum;
                    break;
                case '-':
                    res = firstNum - secondNum;
                    break;
                case '*':
                    res = firstNum * secondNum;
                    break;
                case '/':
                    res = firstNum / secondNum;
                    break;
            }
            field.setText(String.valueOf(res));
            firstNum = res;
        }

        if (ae.getSource() == clr){
            field.setText("");
        }

        if (ae.getSource() == del){
            String num = field.getText();
            field.setText(num.substring(0, num.length() - 1));
        }

        if (ae.getSource() == neg){
            double t = Double.parseDouble(field.getText()) * -1;
            field.setText(String.valueOf(t));
        }
    }
}
