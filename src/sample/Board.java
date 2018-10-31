package sample;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    Pieces[][] pieces;
    int x;
    int y;
    

    public Board (int rows, int collums){
        x = collums;
        y = rows;
        setForm();
    }

    private void setForm() {
        pieces = new Pieces[x][y];
        setSize(500,500);
        setLayout(new GridLayout(x,y,10,10));
        setBackground(Color.ORANGE);
        //setBorder();
        for (Pieces[]row : pieces){
            for(int i = 0; i < pieces.length; i++){
                row[i] = new Pieces();
            }
        }
        pieceFormation();
    }

    private void pieceFormation() {

    }


}
