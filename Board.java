import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Board
{
    // Array to hold board cards
    private ArrayList<FlippableCard> cards;

    // Resource loader
    private ClassLoader loader = getClass().getClassLoader();

    public Board(int size, ActionListener AL) {
        // Allocate and configure the game board: an array of cards
        cards = new ArrayList<FlippableCard>();

        // Fill the Cards array
        int imageIdx = 1;
        for (int i = 0; i < size; i += 2) {

            // Load the front image from the resources folder
            String imgPath = "res/hub" + imageIdx + ".jpg";
            ImageIcon img = new ImageIcon(loader.getResource(imgPath));

            // Setup two cards at a time
            FlippableCard c1 = new FlippableCard(img);
            FlippableCard c2 = new FlippableCard(img);
            c1.setID(imageIdx);
            c2.setID(imageIdx);
            c1.addActionListener(AL);
            c2.addActionListener(AL);
            imageIdx++;  // get ready for the next pair of cards

            // Add them to the array
            cards.add(c1);
            cards.add(c2);
        }

        /*
         * To-Do: Randomize the card positions
         */

       Collections.shuffle(cards);
       for (FlippableCard card : cards) {
           card.hideFront();
       }
    }

    public void fillBoardView(JPanel view)
    {
        for (FlippableCard c : cards) {
            view.add(c);
        }
    }

    public void resetBoard()
    {
        //* To-Do: Reset the flipped cards and randomize the card positions

        Collections.shuffle(cards);
        for (FlippableCard card : cards) {
            card.hideFront();
        }
    }
}
