import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.util.List;


public class View extends JFrame{
    public static final Color VERY_DARK_GREEN = new Color(0, 102, 0);
    private final Model gameModel;

    public View(Model gameModel) {
        this.gameModel = gameModel;
//        initializeGame();
    }

    public void initializeGame() {
        JFrame frame = new JFrame("Klondike Solitaire");
        TestPane tp = new TestPane(gameModel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // uncomment to make the window fullscreen
        frame.setSize(1500, 900);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        JButton newGame = new JButton("New Game");
        JButton undo = new JButton("Undo");

        panel.add(newGame); // Components Added using Flow Layout
        panel.add(undo);

        createButtons(frame);

        // Text Area at the Center
        frame.getContentPane().setBackground(VERY_DARK_GREEN);
        frame.getContentPane().add(BorderLayout.NORTH, panel);

        frame.add(tp);
        frame.setVisible(true);
        frame.setResizable(false);

        // Brian added a comment
    }

    void createButtons(JFrame frame) {
        JButton[] tableau = new JButton[7];
        JButton[] foundation = new JButton[4];
        JButton[] dd = new JButton[2];
        int x = 25;
        int y = 275;
        for (JButton jButton : tableau) {
            jButton = new JButton("Button");
            jButton.setSize(175, 500);
            jButton.setOpaque(false);
            jButton.setContentAreaFilled(false);
            jButton.setBorderPainted(false);
            frame.getContentPane().add(jButton);
            jButton.setLocation(x, y);
            jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            x += 200;
        }
        int xFoundation = 25;
        int yFoundation = 75;
        for (JButton jButton : foundation) {
            jButton = new JButton("Button");
            jButton.setSize(150, 200);
            jButton.setOpaque(false);
            jButton.setContentAreaFilled(false);
            jButton.setBorderPainted(false);
            frame.getContentPane().add(jButton);
            jButton.setLocation(xFoundation, yFoundation);
            jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            xFoundation += 150;
        }
        int ddx = 1100;
        int ddy = 75;
        for (JButton jButton : dd) {
            jButton = new JButton("Button");
            jButton.setSize(150, 200);
            jButton.setOpaque(false);
            jButton.setContentAreaFilled(false);
            jButton.setBorderPainted(false);
            frame.getContentPane().add(jButton);
            jButton.setLocation(ddx, ddy);
            jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            ddx += 150;
        }
    }
}

class TestPane extends JLayeredPane {
    public TestPane(Model gameModel) {
        addingTableau(gameModel);
//        addingFoundation(gameModel);
        addingDraw(gameModel);
    }

    private void addingDraw(Model gameModel) {
        int yStart = 65, xStart = 1275;
        int yOffset = 0, xOffset = 2;

        int x = xStart, y = yStart - yOffset;
            for (int j = gameModel.getDrawPile().size() - 1 ; j >= 0; j--) { // iterates through the stack
                System.out.println(gameModel.getDrawPile().get(j) + " " + gameModel.getDrawPile().get(j).isFaceUp());
                try {
                    ImageIcon imageIcon = gameModel.getDrawPile().get(j).getCardImage();
                    Image image = imageIcon.getImage();
                    Image newimg = image.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newimg);
                    JLabel label = new JLabel(imageIcon);
                    label.setSize(label.getPreferredSize());
                    label.setLocation(x, y);
                    add(label);
                    x += xOffset;
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
                // put card behind the button
            }
        }

    void addingTableau(Model gameModel) {
        int yStart = 275, xStart = 75;
        int yOffset = 25, xOffset = 200;

        int x = xStart, y = yStart - yOffset;
        for (int i = 0; i < gameModel.getTableau().length; i++) { // length of list of stacks
            System.out.println("Stack " + Integer.toString(i));
            for (int j = gameModel.getTableau()[i].size() - 1 ; j >= 0; j--) { // iterates through the stack
//            for (int j = 0; j < gameModel.getTableau()[i].size(); j++) {
                System.out.println(gameModel.getTableau()[i].get(j) + " " + gameModel.getTableau()[i].get(j).isFaceUp());
                try {
                    ImageIcon imageIcon = gameModel.getTableau()[i].get(j).getCardImage();
                    Image image = imageIcon.getImage();
                    Image newimg = image.getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newimg);
                    JLabel label = new JLabel(imageIcon);
                    label.setSize(label.getPreferredSize());
                    label.setLocation(x, y);
                    add(label);
                    y -= yOffset;
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
                // put card behind the button
            }
            y = yStart + yOffset * i;
            x += xOffset;
        }
    }


//    void addingFoundation(Model gameModel) {
//        int yStart = 75, xStart = 75;
//        int yOffset = 25, xOffset = 200;
//
//        int x = xStart, y = yStart - yOffset;
//        for (int i = 0; i < gameModel.getFoundation().length; i++) { // length of list of stacks
//            System.out.println("Stack " + Integer.toString(i));
//            for (int j = gameModel.getFoundation()[i].size() - 1; j >= 0; j--) { // iterates through the stack
////            for (int j = 0; j < gameModel.getTableau()[i].size(); j++) {
//                System.out.println(gameModel.getFoundation()[i].get(j) + " " + gameModel.getFoundation()[i].get(j).isFaceUp());
//                try {
//                    ImageIcon imageIcon = gameModel.getFoundation()[i].get(j).getCardImage();
//                    Image image = imageIcon.getImage();
//                    Image newimg = image.getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH);
//                    imageIcon = new ImageIcon(newimg);
//                    JLabel label = new JLabel(imageIcon);
//                    label.setSize(label.getPreferredSize());
//                    label.setLocation(x, y);
//                    add(label);
//                    y -= yOffset;
//                } catch (Exception exp) {
//                    exp.printStackTrace();
//                }
//                // put card behind the button
//            }
//            y = yStart + yOffset * i;
//            x += xOffset;
//        }
//    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }

}
