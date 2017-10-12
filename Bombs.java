import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;
import java.util.Random;

public class Bombs extends JFrame implements ActionListener, MouseListener{

        private int gameTime = 0;
        private int clickCount = 1;
        private int height;
        private int width;
        private JButton[][] buttonArray;
        private int[][] mineArray;
        private int buttonWidth;
        private int buttonHeight;
        //private MinesweeperBoard gameBoard;
        private JPanel window;


    public Bombs(){


        super("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
        setResizable(false);


        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        JMenu settingsMI = new JMenu("Settings");


        JMenuItem startMI = new JMenuItem("Start", KeyEvent.VK_S);
        JMenuItem helpMI = new JMenuItem("Help", KeyEvent.VK_H);
        JMenuItem quitMI = new JMenuItem("Quit", KeyEvent.VK_Q);
        JMenuItem beginnerMI = new JMenuItem("Beginner", KeyEvent.VK_B);
        JMenuItem intermediateMI = new JMenuItem("Intermediate", KeyEvent.VK_I);
        JMenuItem expertMI = new JMenuItem("Expert", KeyEvent.VK_E);
        JMenuItem customMI = new JMenuItem("Custom...", KeyEvent.VK_C);
        JLabel timerLabel = new JLabel("Timer: 0");

        Thread thread = new Thread();
        thread.start();
        window = new JPanel();

        Timer gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (clickCount > 0) {
                    gameTime++;
                    timerLabel.setText("Timer: " + Integer.toString(gameTime));
                }
            }
        }, 0, 1000);


        startMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You pressed the start button, yo!");
                MinesweeperSquares(5, 5);
                gameTime = 0;
            }
        });

        helpMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickCount = 0;
                JOptionPane.showMessageDialog(window,"Game has \"bombs\" in unknown random locations.\n" +
                        "The goal is to avoid clicking on a cell where a bomb is located. \nA cell has 8 neighbors:" +
                        "the north, south, east and west cells, together with the four diagonal ones.\nCells on the " +
                        "boundary of the grid have fewer neighbors (only five for a cell on an edge and corner\n" +
                        "cells only have three). \nThe player scores a victory by clicking to reveal all cells that " +
                        "are not bombs without ever hitting a bomb.\n\nBeginner: 5x5 grid with 5 bombs.\n" +
                        "Intermediate: 8x8 with 15 bombs.\nExpert: 10x10 with 30 bombs.\n" +
                        "Custom: 2-10 rows & columns with bombs no more than half number of cells\n");
                clickCount = 1;
            }
        });
        quitMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        customMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int gridSize = Integer.parseInt(JOptionPane.showInputDialog("What grid size would you like?\n(Enter number 2-10)"));
                while (gridSize < 2 || gridSize > 10){
                    gridSize = Integer.parseInt(JOptionPane.showInputDialog("What grid size would you like?\n(Enter number 2-10)"));
                }
                int bombSize = Integer.parseInt(JOptionPane.showInputDialog("How many bombs do you want?\n(Half the number of squares or less)"));
                while (bombSize > ((gridSize*gridSize)/2)){
                    bombSize = Integer.parseInt(JOptionPane.showInputDialog("How many bombs do you want?\n(Half the number of squares or less"));
                }

                gameTime = 0;
                MinesweeperSquares(gridSize, bombSize);

            }
        });
        beginnerMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int gridSize = 5;
                MinesweeperSquares(5,5);
                gameTime = 0;
            }
        });
        intermediateMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MinesweeperSquares(8,15);
                gameTime = 0;
            }
        });
        expertMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MinesweeperSquares(10,30);
                gameTime = 0;
            }
        });

        fileMenu.add(startMI);
        fileMenu.add(settingsMI);
        fileMenu.add(helpMI);
        fileMenu.add(quitMI);

        settingsMI.add(beginnerMI);
        settingsMI.add(intermediateMI);
        settingsMI.add(expertMI);
        settingsMI.add(customMI);

        menuBar.add(fileMenu);
        menuBar.add(timerLabel);

        MinesweeperSquares(5, 5);
        setJMenuBar(menuBar);
        //setContentPane(window);
        add(window);
        setVisible(true);
    }



    public void MinesweeperSquares(int size, int bombnum){
        window.removeAll();
        window.setLayout(new GridLayout(size,size));
        height = size;
        width = size;
        buttonWidth = (500/size);
        buttonHeight = (500/size);

        buttonArray = new JButton[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                JButton new_button = new JButton();
                window.add(new_button);
                buttonArray[i][j] = new_button;
                buttonArray[i][j].addActionListener(this);
            }
        }

        Random generatex = new Random();
        mineArray = new int[size][size];
        for (int q = 0; q < bombnum; q++){
            int randRow = generatex.nextInt(height);
            int randCol = generatex.nextInt(width);
            mineArray[randRow][randCol] = -1;
        }
        setVisible(true);
    }


    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
         JButton currentButt = (JButton) e.getSource();
         int currentButtx = currentButt.getX();
         int currentButty = currentButt.getY();
         currentButtx = (currentButtx/buttonWidth);
         currentButty = (currentButty/buttonHeight);

         int buttonValue = mineArray[currentButty][currentButtx];
         if (buttonValue == 0){
             currentButt.setBackground(Color.blue);
         }

         else if (buttonValue == -1){
             currentButt.setBackground(Color.red);

             for (int s = 0; s < mineArray.length; s++ ){
                 for (int t = 0; t < mineArray[s].length; t++){
                     if (mineArray[s][t] == -1){
                        buttonArray[s][t].setBackground(Color.red);
                        buttonArray[s][t].setText("*\n*\n*");
                     }
                     /*else{
                         mineArray[s][t] = 0;
                         if (mineArray[s][t]==0) {
                             buttonArray[s][t].setBackground(Color.blue);
                         }
                     }*/
                 }
             }

             //stops timer
             clickCount = 0;
             JOptionPane.showMessageDialog(null, "You clicked a bomb! \n Better luck next time!");
             System.exit(0);

        }
    }

    public static void main(String[] args){
        Bombs createWindow = new Bombs();
    }
}
