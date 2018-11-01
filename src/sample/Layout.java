package sample;

import javax.swing.*;
import java.awt.*;

public class Layout extends JPanel {

    int rows = 4;
    int collums = 4;


    JPanel menuPanel = new JPanel();


    /**
     * knapp layout
     * @param newGame
     * @param exit
     */
    public Layout(JButton newGame, JButton exit) {

        ;
        menuPanel.setLayout(new GridLayout(3, 1));
        menuPanel.add(newGame);
        menuPanel.add(exit);

        setLayout(new GridLayout(2, 1));


        add(menuPanel);
        setPreferredSize(new Dimension(100, 100));

    }

    public int getRows() {
        return rows;
    }

    public int getCollums() {
        return collums;
    }
}
