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

    /**
     * settar rutorna storlek och gap
     */
    private void setForm() {
        pieces = new Pieces[x+2][y+2];
        setSize(50, 50);
        setLayout(new GridLayout(x, y, 1, 1));
        setBackground(Color.ORANGE);

        for (Pieces[] row : pieces) {
            for (int i = 0; i < pieces.length; i++) {
                row[i] = new Pieces();
            }
        }
        piecePlacement();
    }

    /**
     * lägger ut bitarna på board
     */
    private void piecePlacement() {
        int n = 0;
        for (int i = 1; i < pieces.length - 1; i++) {
            for (int j = 1; j < pieces[i].length - 1; j++) {
                pieces[i][j].setText(String.valueOf(++n));
                if(i % 2 == 0){
                    if (j % 2 == 0) {
                        pieces[i][j].setPieceColor(Color.ORANGE);
                        pieces[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    } else {
                        pieces[i][j].setPieceColor(Color.ORANGE);
                        pieces[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    }
                } else {
                    if (j % 2 == 1) {
                        pieces[i][j].setPieceColor(Color.ORANGE);
                        pieces[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    } else {
                        pieces[i][j].setPieceColor(Color.ORANGE);
                        pieces[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    }
                }
                if ((j == pieces[i].length - 2) && (i == pieces.length - 2)) {              //skapar den "tomma" rutan
                    pieces[i][j].setBackground(Color.BLACK);
                    pieces[i][j].setText("");
                    pieces[i][j].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
                    freeY = i;
                    freeX = j;
                } else {
                    pieces[i][j].addMouseListener(mouseAdapter);
                }
                add(pieces[i][j]);
            }
        }
    }


    /**
     * flytta på bitarna när det finns en tom ruta
     */
    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {
            for (Pieces[] piecesArray : pieces) {
                for (Pieces pieces1 : piecesArray) {
                    if (e.getSource() == pieces1) {
                        pieces1.setBackground(pieces1.getPieceColor());
                        if (isMovable()) {
                            slide();
                            updatePuzzle();
                            if (isSolved()) {
                                JOptionPane.showMessageDialog(null, "GRATTIS!!!");
                            }
                            return;
                        }
                    }
                }
            }
        };
        // highlight den biten man trycker på
        @Override
        public void mousePressed(MouseEvent e) {
            for (int i = 1; i < pieces.length - 1; i++) {
                for (int j = 1; j < pieces[i].length - 1; j++) {
                    if (e.getSource() == pieces[i][j]) {
                        pieces[i][j].setBackground(Color.WHITE);
                        clickedY = i;
                        clickedX = j;
                    }
                }
            }
        }
    };


    /**
     * kontrollera om biten man har klickat på kan flytta sig
     * att kolla om det går längs både x och y
     * @return
     */
    private boolean isMovable() {
        return ((clickedX < freeX || clickedX > freeX) && (clickedY == freeY))
                || ((clickedY < freeY || clickedY > freeY) && (clickedX == freeX));
    }

    /**
      skapar en förflyning genom att byta ut delar
     */
    private void slide() {
        Pieces[] tempPiece = new Pieces[Math.abs(clickedY-freeY + clickedX-freeX)];
        int direction = 1;
        int step = 1;

        if ((clickedY-freeY + clickedX-freeX) > 0) {
            direction = -1;
            step = -1;
        }

        int counter = 0;
        if (clickedX == freeX) {
            for (int i = clickedY; i != freeY;) {
                tempPiece[counter] = pieces[i][freeX];
                if (clickedY > freeY)
                    i--;
                else
                    i++;
                counter++;
            }
        } else {
            for (int i = clickedX; i != freeX;) {
                tempPiece[counter] = pieces[freeY][i];
                if (clickedX > freeX)
                    i--;
                else
                    i++;
                counter++;
            }
        }

        pieces [clickedY][clickedX] = pieces[freeY][freeX];
        if (clickedX == freeX) {
            for (Pieces b : tempPiece) {
                pieces[clickedY+direction][freeX] = b;
                direction += step;
            }
        } else {
            for (Pieces b : tempPiece) {
                pieces[freeY][clickedX+direction] = b;
                direction += step;
            }
        }
        freeX = clickedX;
        freeY = clickedY;
    }

    protected void updatePuzzle() {
        removeAll();
        for (int i = 1; i < pieces.length - 1; i++) {
            for (int j = 1; j < pieces[i].length - 1; j++) {
                add(pieces[i][j]);
            }
        }
        revalidate();
        repaint();
    }

    public void randomize() {
        int a = (int) Math.pow(y * x, 2);
        for (int i = 0; i <= a; i++) {
            while (!isMovable()) {
                this.clickedY = (int) (Math.random() * y + 1);
                this.clickedX = (int) (Math.random() * x + 1);
            }
            slide();
        }
        updatePuzzle();
    }

    /**
     * seger meddelande
     * @return
     */
    protected boolean isSolved() {
        int x = 0;
        for (int i = 1; i < pieces.length - 1; i++) {
            for (int j = 1; j < pieces[i].length - 1 ; j++) {
                if (!pieces[i][j].getText().equals(String.valueOf(++x)))
                    return false;
            }
        }
        return true;
    }
}
