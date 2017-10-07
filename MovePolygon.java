import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class MovePolygon implements ActionListener {
    public static void main(String[] args){

        JButton stop = new JButton("Stop");
        JButton reset = new JButton("Reset");
        JPanel window = new JPanel();
        JPanel control = new JPanel();

        PolygonPanel polygon_panel = new PolygonPanel();

        JFrame frame = new JFrame("Draw Polygon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setBackground(Color.orange);
        frame.setLocationRelativeTo(null); //centers to middle of screen
        frame.setContentPane(polygon_panel);

        control.add(stop);
        control.add(reset);

        frame.add(control, BorderLayout.NORTH);
        frame.add(window, BorderLayout.SOUTH);
        frame.setResizable(false);
        frame.setVisible(true);

        Thread thread = new Thread(polygon_panel);
        thread.start();


        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hey");
                polygon_panel.is_clicked = false; }
        });
    }

    public void actionPerformed(ActionEvent e)
    {}
}

