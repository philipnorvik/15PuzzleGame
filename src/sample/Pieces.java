package sample;

import javax.swing.*;
import java.awt.*;

public class Pieces extends JLabel {

    protected Color pieceColor;

    public Pieces(){
        setForm();
    }

    /**
     * vilken färg den ska ha
     * @param pieceColor
     */
    public void setPieceColor(Color pieceColor){
        this.setBackground(Color.ORANGE);
    }

    /**
     * hur bitarna ska se ut
     */
    public void setForm(){
        setFont(new Font("Times New Roman", Font.ITALIC, 32));
        setForeground(Color.BLACK);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(true);
    }



    public Color getPieceColor() {
        return pieceColor;
    }
}
