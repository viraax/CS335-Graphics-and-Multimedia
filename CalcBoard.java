import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalcBoard extends JFrame{
    private JTextField answer;
    private JButton zero, one, two, three, four, five, six, seven, eight, nine, add, subtract, divide, multiply, decimal, equal;
    private JPanel window;

    public CalcBoard(){

        zero = new JButton("0");
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        four = new JButton("4");
        five = new JButton("5");
        six = new JButton("6");
        seven = new JButton("7");
        eight = new JButton("8");
        nine = new JButton("9");
        add = new JButton("+");
        subtract = new JButton("-");
        divide = new JButton("/");
        multiply = new JButton("*");
        decimal = new JButton(".");
        equal = new JButton("=");
        answer = new JTextField(null, 20);

        window = new JPanel();
        window.setBackground(Color.blue);
        window.setLayout(new FlowLayout());

        Dimension b_size = new Dimension(50, 50);
        zero.setPreferredSize(b_size);
        one.setPreferredSize(b_size);
        two.setPreferredSize(b_size);
        three.setPreferredSize(b_size);
        four.setPreferredSize(b_size);
        five.setPreferredSize(b_size);
        six.setPreferredSize(b_size);
        seven.setPreferredSize(b_size);
        eight.setPreferredSize(b_size);
        nine.setPreferredSize(b_size);
        add.setPreferredSize(b_size);
        subtract.setPreferredSize(b_size);
        divide.setPreferredSize(b_size);
        multiply.setPreferredSize(b_size);
        decimal.setPreferredSize(b_size);
        equal.setPreferredSize(b_size);


        window.add(answer, BorderLayout.NORTH);
        window.add(answer);
        window.add(seven);
        window.add(eight);
        window.add(nine);
        window.add(divide);
        window.add(four);
        window.add(five);
        window.add(six);
        window.add(multiply);
        window.add(one);
        window.add(two);
        window.add(three);
        window.add(subtract);
        window.add(zero);
        window.add(decimal);
        window.add(equal);
        window.add(add);



        this.setContentPane(window);
    }

}
