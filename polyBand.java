import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Polygon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class polyBand extends JFrame {

  // simple hard-coded rectangle as starting point to draw
  private int x[] = {150,250,250,150};
  private int y[] = {150,150,200,200};
  private Polygon poly  = new Polygon(x,y,4);
  private Container c;
  private drawPanel panel;

  //boolean to tell if the polygon is being dragged
  private boolean isDragging1 = false;
  private boolean isDragging2 = false;
  private boolean isDragging3 = false;
  private boolean isDragging4 = false;
	
  public polyBand() {
    c = getContentPane();
    //create a new drawPanel with the banding boolean set
    panel = new drawPanel(poly);	

    //create a mouse listener to tell when the mouse is clicked and released
    panel.addMouseListener(new MouseListener(){  
      public void mouseExited(MouseEvent e){}
      public void mouseEntered(MouseEvent e){}
      public void mouseReleased(MouseEvent e){
        isDragging1 = false;
        isDragging2 = false;
        isDragging3 = false;
        isDragging4 = false;
      }
      public void mousePressed(MouseEvent e){
        if(panel.clickInPoly2(e.getPoint())){
          isDragging1 = true;
        }
        else if (panel.clickInPoly3(e.getPoint())){
            isDragging2 = true;
        }
        else if (panel.clickInPoly4(e.getPoint())){
            isDragging3 = true;
        }
        else if (panel.clickInPoly5(e.getPoint())){
            isDragging4 = true;
        }
      }
      public void mouseClicked(MouseEvent e){}
    });

    //create a motion listener to track the mouse dragging the polygon
    panel.addMouseMotionListener(new MouseMotionListener(){	
        public void mouseDragged(MouseEvent e) {
            if(isDragging1 /*&& panel.clickInPoly2(e.getPoint())*/){
                x[2] = e.getX();
                y[2] = e.getY();
                panel.setRightX(e.getX());
                panel.setRightY(e.getY());
                panel.setPoly(new Polygon(x,y,4));
                panel.repaint();
            }
            else if (isDragging2 /* && panel.clickInPoly3(e.getPoint())*/){
                x[3] = e.getX();
                y[3] = e.getY();
                panel.setLeftX(e.getX());
                panel.setLeftY(e.getY());
                panel.setPoly(new Polygon(x,y,4));
                panel.repaint();
            }
            else if (isDragging3){
                x[0] = e.getX();
                y[0] = e.getY();
                panel.setTopLeftX(e.getX());
                panel.setTopLeftY(e.getY());
                panel.setPoly(new Polygon(x,y,4));
                panel.repaint();
            }
            else if (isDragging4){
                x[1] = e.getX();
                y[1] = e.getY();
                panel.setTopRightX(e.getX());
                panel.setTopRightY(e.getY());
                panel.setPoly(new Polygon(x,y,4));
                panel.repaint();
            }
        }
        public void mouseMoved(MouseEvent e) {}
    });


    c.add(panel, BorderLayout.CENTER);
    setSize(400, 400);
    setVisible(true);
  }

  public static void main(String[] args) {
    polyBand poly = new polyBand();
    poly.addWindowListener( 
      new WindowAdapter(){
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
    });
  }

} // end PolyBand JFrame object
