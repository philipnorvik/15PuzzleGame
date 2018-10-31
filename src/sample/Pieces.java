package sample;

import javax.swing.*;
import java.awt.*;

public class Pieces extends JPanel {

    protected Color pieceColor;

    protected Pieces(){
        setForm();
    }

    public void setPieceColor(Color pieceColor){
        this.setBackground(Color.BLACK);
    }

    protected void setForm(){
        setFont(new Font("Times New Roman", Font.ITALIC, 12));
        setForeground(Color.ORANGE);
        setOpaque(true);
    }




}
