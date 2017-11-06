import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {

    private int width = 300;
    private int height = 300;
    private int row = 20;
    private int col = 20;
    public JPanel[][] squares = new JPanel[row][col];

    public MazePanel() {
        //set jpanel[][]squares to equal to something and jpanel will go away
        //set jpanel size to 300,300
        //super();
        setLayout(new GridLayout(20,20));

        for(row = 0; row < 20; row++) {
            for(col = 0; col < 20; col++) {
                squares[row][col] = new JPanel();
                // squares[row][col].setLayout(new GridLayout(10,10));
                squares[row][col].setBackground(Color.black);
                squares[row][col].setBorder(BorderFactory.createLineBorder(Color.red));
                super.add(squares[row][col]);
            }
        }

        // ...
    }

    public void setColumn(int new_column){
        col = new_column;
    }

    public void setWidth(int new_row){
        width = new_row;
    }

}