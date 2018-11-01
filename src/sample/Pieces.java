package sample;

import javax.swing.*;
import java.awt.*;

public class Pieces extends JLabel {

    protected Color pieceColor;

    public Pieces(){
        setForm();
    }

    public void setPieceColor(Color pieceColor){
        this.setBackground(Color.ORANGE);
    }

    public void setForm(){
        setFont(new Font("Times New Roman", Font.ITALIC, 32));
        setForeground(Color.BLACK);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(true);
        //setSize(40, 40);
    }



    public Color getPieceColor() {
        return pieceColor;
    }
}
