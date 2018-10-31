package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Game extends JFrame {

    private final JButton newGame = new JButton("Nytt Spel");
    private final JButton exit = new JButton("Avsluta");

    private Board boardGame = new Board(4, 4);
    private final Layout layout = new Layout(newGame, exit);

    public void GameGUI() {
        setLayout(new BorderLayout());
        setTitle("15-Spel");
        setVisible(true);
        setSize(500, 500);

        setLocationRelativeTo(null);
        add(boardGame, BorderLayout.CENTER);
        add(layout, BorderLayout.WEST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        newGame.addActionListener((ActionEvent ae) -> {
            remove(boardGame);
            boardGame = new Board(layout.getRows(), layout.getCollums());
            setSize(layout.getCollums() * 85 + 150, layout.getRows() * 85);
            setLocationRelativeTo(null);
            add(boardGame);
            revalidate();
            repaint();
        });

        exit.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });
    }
}
