package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JPanel {

    Pieces[][] pieces;
    int x;
    int y;
    int freeX;
    int freeY;
    int clickedX;
    int clickedY;


    public Board(int rows, int collums) {
        x = collums;
        y = rows;
        setForm();
    }

    private void setForm() {
        pieces = new Pieces[x][y];
        setSize(500, 500);
        setLayout(new GridLayout(x, y, 10, 10));
        setBackground(Color.ORANGE);
        //setBorder();
        for (Pieces[] row : pieces) {
            for (int i = 0; i < pieces.length; i++) {
                row[i] = new Pieces();
            }
        }
        pieceFormation();
    }

    private void pieceFormation() {
        int a = 0;
        for (int i = 1; i < pieces.length - 1; i++) {
            for (int j = 1; j < pieces[i].length - 1; j++) {
                pieces[i][j].setText(String.valueOf(++a));

                if ((j == pieces[i].length - 2) && (i == pieces.length - 2)) {
                    pieces[i][j].setBackground(Color.BLACK);
                    pieces[i][j].setText(String.valueOf(x * y));
                    //pieces[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    freeY = i;
                    freeX = j;
                } else {
                    pieces[i][j].addMouseListener(mouseAdapter);
                }
                add(pieces[i][j]);
            }
        }
    }

    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            for (int i = 1; i < pieces.length - 1; i++) {
                for (int j = 1; j < pieces[i].length - 1; j++) {
                    if (e.getSource() == pieces[i][j]) {
                        pieces[i][j].setBackground(Color.ORANGE);
                        clickedY = i;
                        clickedX = j;
                    }
                }
            }
        }
    };

    @Override
    public void mouseReleased(MouseEvent e) {
        for (Pieces[] pieces1 : pieces) {
            for (Pieces pieces2 : pieces1) {
                if (e.getSource() == pieces2) {
                    pieces2.setBackground(pieces1.getPermanentColor());
                    if (isMovable()) {
                        slide();
                        updatePuzzle();
                        if (isSolved()) {
                            JOptionPane.showMessageDialog(null, "Du vann!");
                        }
                        return;
                    }
                }
            }
        }
    }


}
    public void shuffle() {
        int a = (int) Math.pow(yLength * xLength, 2);
        for (int i = 0; i <= a; i++) {
            while (!isMovable()) {
                this.clickedY = (int) (Math.random() * yLength + 1);
                this.clickedX = (int) (Math.random() * xLength + 1);
            }
            slide();
        }
        updatePuzzle();
    }

    private boolean isMovable() {
        return ((clickedX < freeX || clickedX > freeX) && (clickedY == freeY))
                || ((clickedY < freeY || clickedY > freeY) && (clickedX == freeX));
    }

    private void slide() {
        Pieces[] tempBrickor = new Bricka[Math.abs(clickedY-freeY + clickedX-freeX)];
        int direction = 1;
        int step = 1;

        if ((clickedY-freeY + clickedX-freeX) > 0) {
            direction = -1;
            step = -1;
        }

        int counter = 0;
        if (clickedX == freeX) {
            for (int i = clickedY; i != freeY;) {
                tempBrickor[counter] = brickor[i][freeX];
                if (clickedY > freeY)
                    i--;
                else
                    i++;
                counter++;
            }
        } else {
            for (int i = clickedX; i != freeX;) {
                tempBrickor[counter] = brickor[freeY][i];
                if (clickedX > freeX)
                    i--;
                else
                    i++;
                counter++;
            }
        }

        brickor[clickedY][clickedX] = brickor[freeY][freeX];
        if (clickedX == freeX) {
            for (Bricka b : tempBrickor) {
                brickor[clickedY+direction][freeX] = b;
                direction += step;
            }
        } else {
            for (Bricka b : tempBrickor) {
                brickor[freeY][clickedX+direction] = b;
                direction += step;
            }
        }
        freeX = clickedX;
        freeY = clickedY;
    }

    private void updatePuzzle() {
        removeAll();
        for (int i = 1; i < brickor.length - 1; i++) {
            for (int j = 1; j < brickor[i].length - 1; j++) {
                add(brickor[i][j]);
            }
        }
        revalidate();
        repaint();
    }

    private boolean isSolved() {
        int x = 0;
        for (int i = 1; i < brickor.length - 1; i++) {
            for (int j = 1; j < brickor[i].length - 1 ; j++) {
                if (!brickor[i][j].getText().equals(String.valueOf(++x)))
                    return false;
            }
        }
        return true;
    }
}
