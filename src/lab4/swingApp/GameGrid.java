package lab4.swingApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GameGrid extends JPanel implements ActionListener {

    private Tile[] tiles = new Tile[16];

    private int emptyPosX;
    private int emptyPosY;

    public GameGrid() {
        super(new GridLayout(4, 4, 8, 8));

        for (int i = 0; i < 15; i++) {
            this.tiles[i] = new Tile(Integer.valueOf(i + 1).toString(), i%4, i/4);
        }
        this.tiles[15] = new Tile("", 3, 3);

        for (int i = 0; i < this.tiles.length; i++) {
            this.tiles[i].addActionListener(this);
            this.add(this.tiles[i]);
        }

        emptyPosX = 3;
        emptyPosY = 3;
    }


    public void createNewGrid() {

        ArrayList<Integer> newGrid = GameRandomized.randomizeTile();

        for (int i = 0; i < tiles.length - 1; i++) {
            this.tiles[i].setText(newGrid.get(i).toString());
            this.tiles[i].setPosX(i%4);
            this.tiles[i].setPosY(i/4);
        }

        this.tiles[15].setText("");
        this.tiles[15].setPosX(3);
        this.tiles[15].setPosY(3);

        this.emptyPosX = 3;
        this.emptyPosY = 3;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Tile source = (Tile) e.getSource();

        if ((Math.abs(emptyPosX - source.getPosX()) <= 1 && Math.abs(emptyPosY - source.getPosY()) <= 1) &&
                !((Math.abs(emptyPosX - source.getPosX()) == 1 && Math.abs(emptyPosY - source.getPosY()) == 1))) {

            String tmpText = source.getText();

            source.setText("");

            this.tiles[emptyPosY*4 + emptyPosX].setText(tmpText);
            emptyPosX = source.getPosX();
            emptyPosY = source.getPosY();

            if (isSolved()) {
                JOptionPane.showMessageDialog(this, "Вы решили \"15 Puzzle Game\"", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private boolean isSolved() {


        if (this.tiles[15].getText().equals("")) {
            int maxVal = Integer.parseInt(this.tiles[0].getText());

            for (int i = 0; i < 15; i++) {
                int currVal = Integer.parseInt(this.tiles[i].getText());
                if (maxVal > currVal) {
                    return false;
                }
                maxVal = currVal;
            }
            return true;
        }
        return false;

    }

    public void moveDown() {
        if (emptyPosY > 0 && emptyPosY <= 3) {
            int changeableY = emptyPosY - 1;
            String text = this.tiles[changeableY*4 + emptyPosX].getText();

            this.tiles[emptyPosY*4 + emptyPosX].setText(text);
            this.tiles[changeableY*4 + emptyPosX].setText("");
            emptyPosY--;
            if (isSolved()) {
                JOptionPane.showMessageDialog(this, "Вы решили \"15 Puzzle Game\"", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void moveUp() {
        if (emptyPosY < 3 && emptyPosY >= 0) {
            int changeableY = emptyPosY + 1;
            String text = this.tiles[changeableY*4 + emptyPosX].getText();

            this.tiles[emptyPosY*4 + emptyPosX].setText(text);
            this.tiles[changeableY*4 + emptyPosX].setText("");
            emptyPosY++;
            if (isSolved()) {
                JOptionPane.showMessageDialog(this, "Вы решили \"15 Puzzle Game\"", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void moveLeft() {
        if (emptyPosX < 3 && emptyPosX >= 0) {
            int changeableX = emptyPosX + 1;
            String text = this.tiles[emptyPosY*4 + changeableX].getText();

            this.tiles[emptyPosY*4 + emptyPosX].setText(text);
            this.tiles[emptyPosY*4 + changeableX].setText("");
            emptyPosX++;
            if (isSolved()) {
                JOptionPane.showMessageDialog(this, "Вы решили \"15 Puzzle Game\"", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void moveRight() {
        if (emptyPosX > 0 && emptyPosX <= 3) {
            int changeableX = emptyPosX - 1;
            String text = this.tiles[emptyPosY*4 + changeableX].getText();

            this.tiles[emptyPosY*4 + emptyPosX].setText(text);
            this.tiles[emptyPosY*4 + changeableX].setText("");
            emptyPosX--;
            if (isSolved()) {
                JOptionPane.showMessageDialog(this, "Вы решили \"15 Puzzle Game\"", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }


}
