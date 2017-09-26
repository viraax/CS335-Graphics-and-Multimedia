import javax.swing.*;

public class Calculator{

    public static void main(String[] args){

        CalcBoard x = new CalcBoard();
        x.setSize(250,280);
        x.setTitle("Useless Calculator");
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        x.setVisible(true);
        x.setResizable(false); //Size of calculator can't be altered
    }

}
