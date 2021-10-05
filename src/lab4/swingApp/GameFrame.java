package lab4.swingApp;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame implements ActionListener, MenuListener {

    private final JMenuBar menuBar;
    private final JMenu fileMenu;
    private final JMenu aboutMenu;
    private final JMenuItem newItem;
    private final JMenuItem endItem;
    private final GameGrid gameGrid;

    public GameFrame() {

        this.setTitle("15 Puzzle Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());
        this.setResizable(false);

        this.menuBar = new JMenuBar();

        this.fileMenu = new JMenu("File");
        this.aboutMenu = new JMenu("About");

        this.newItem = new JMenuItem("New");
        this.endItem = new JMenuItem("End");

        this.newItem.setMnemonic(KeyEvent.VK_N);
        this.endItem.setMnemonic(KeyEvent.VK_E);

        this.newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        this.endItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));

        this.newItem.addActionListener(this);
        this.endItem.addActionListener(this);
        this.aboutMenu.addMenuListener(this);
        this.fileMenu.add(newItem);
        this.fileMenu.add(endItem);

        this.menuBar.add(fileMenu);
        this.menuBar.add(aboutMenu);
        this.setJMenuBar(menuBar);


        this.gameGrid = new GameGrid();
        gameGrid.setPreferredSize(new Dimension(400,400));
        gameGrid.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        gameGrid.setAlignmentY(JComponent.CENTER_ALIGNMENT);

        this.add(gameGrid);

        this.setLocationRelativeTo(null);
        this.processKeys();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(newItem)) {
            gameGrid.createNewGrid();
        }
        else if (e.getSource().equals(endItem)){
            System.exit(0);
        }

    }

    @Override
    public void menuSelected(MenuEvent e) {
        if (e.getSource().equals(aboutMenu)) {
            JOptionPane.showMessageDialog(this, "Кудрявцев Никита, группа, год", "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }

//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }

    private void processKeys() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
                e -> {
                    if (e.getID() == KeyEvent.KEY_TYPED)
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_DOWN:
                            gameGrid.moveDown();
                            break;
                        case KeyEvent.VK_UP:
                            gameGrid.moveUp();
                            break;
                        case KeyEvent.VK_RIGHT:
                            gameGrid.moveRight();
                            break;
                        case KeyEvent.VK_LEFT:
                            gameGrid.moveLeft();
                            break;
                        default:
                            break;
                    }

                    return false;
                }
        );
    }

}
