package sample;

import javax.swing.*;
import java.awt.*;

public class Pieces extends JLabel {

    protected Color pieceColor;

    public Pieces(){
        setForm();
    }

    public void setPieceColor(Color pieceColor){
        this.setBackground(Color.BLACK);
    }

    public void setForm(){
        setFont(new Font("Times New Roman", Font.ITALIC, 12));
        setForeground(Color.ORANGE);
        setOpaque(true);
    }

    public Color getPieceColor() {
        return pieceColor;
    }
}
