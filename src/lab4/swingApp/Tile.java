package lab4.swingApp;

import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {

    private static final Font font = new Font("Times New Roman", Font.BOLD, 40);

    private int posX;
    private int posY;

    Tile(String text, int posX, int posY) {
        super(text);
        this.posX = posX;
        this.posY = posY;
        this.setSize(100, 100);
        this.setFont(font);
        this.setFocusable(true);
    }

    Tile(int posX, int posY) {
        super();
        this.posX = posX;
        this.posY = posY;
        this.setSize(100, 100);
        this.setFont(font);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
