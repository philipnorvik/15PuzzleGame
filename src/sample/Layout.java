package sample;

import javax.swing.*;
import java.awt.*;

public class Layout extends JPanel {

    int rows = 4;
    int collums = 4;

    JPanel topPanel = new JPanel();
    JPanel botPanel = new JPanel();




    public Layout(JButton newGame, JButton exit) {

        topPanel.setLayout(new BorderLayout());
        botPanel.setLayout(new GridLayout(3, 1));
        botPanel.add(newGame);
        botPanel.add(exit);

        setLayout(new GridLayout(3, 1));

        add(topPanel);

        add(botPanel);
        setPreferredSize(new Dimension(150, 0));

    }

    public int getRows() {
        return rows;
    }

    public int getCollums() {
        return collums;
    }
}
