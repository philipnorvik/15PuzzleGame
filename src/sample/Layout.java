package sample;

import javax.swing.*;
import java.awt.*;

public class Layout extends JPanel {

    int rows = 4;
    int collums = 4;

    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();




    public Layout(JButton newGame, JButton exit) {

        topPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new GridLayout(3, 1));
        bottomPanel.add(newGame);
        bottomPanel.add(exit);

        setLayout(new GridLayout(2, 1));

        add(topPanel);
        add(bottomPanel);
        setPreferredSize(new Dimension(100, 100));

    }

    public int getRows() {
        return rows;
    }

    public int getCollums() {
        return collums;
    }
}
