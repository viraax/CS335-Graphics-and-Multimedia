import javax.swing.*;
import java.awt.*;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

public class PolygonPanel extends JPanel implements ActionListener,MouseListener,Runnable {
    private int xcord, ycord;
    Polygon rectangle;
    Timer time;
    boolean is_clicked = false;
    int degrees = 0;
    int times_clicked = 0;

   // Graphics g = PolygonPanel.super.getGraphics();

    PolygonPanel(){
        int [] x = {150,285,285,150};
        int [] y = {155,155,240,240};
        rectangle = new Polygon(x,y,4);
        addMouseListener(this);
        System.out.println("Running..."); /*DELETE LATER*/ }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.orange);
        g2d.fillRect(0,0,500,500);
        //AffineTransform transform = g2d.getTransform();
        g2d.rotate(Math.toRadians(degrees), xcord, ycord);
        g2d.setColor(Color.black);  //unhide (to make new red rectangles)
        //g2d.fillPolygon(rectangle); //unhide
        //g2d.setTransform(transform);
        g2d.fillPolygon(rectangle); //use transform instead //hide
    }

    @Override
    public void run() {
        time = new Timer();
        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(is_clicked) {
                    // Rotate the darn rectangle
                       degrees = (degrees+1) % 360;
                       repaint();
                       if (degrees == 0) {
                           is_clicked = false;
                    }
                }
                else{
                    degrees = 0;
                    repaint();
                }
            }
        }, 0, 1000/60);
    }


    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseClicked(MouseEvent e) {
        xcord = e.getX();
        ycord = e.getY();

        if(rectangle.contains(xcord,ycord)){
            is_clicked = true;
            System.out.println("Inside");

        }
        else{
            System.out.println("Outside of Bounds");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //repaint();
    }


}
