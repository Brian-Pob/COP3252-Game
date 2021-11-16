import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
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
        JButton[] tableau = new JButton[7];

        panel.add(newGame); // Components Added using Flow Layout
        panel.add(undo);
        int x = 50;
        int y = 300;
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

        // Text Area at the Center
        frame.getContentPane().setBackground(VERY_DARK_GREEN);
        frame.getContentPane().add(BorderLayout.NORTH, panel);

        frame.add(tp);
        frame.setVisible(true);
        frame.setResizable(false);

        for (int i = 0; i < gameModel.getTableau().length; i++) { // length of list of stacks
            System.out.println("Stack " + Integer.toString(i));
            for (int j = 0; j < gameModel.getTableau()[i].size(); j++) { // iterates through the stack

                System.out.println(gameModel.getTableau()[i].get(j) + " " + gameModel.getTableau()[i].get(j).isFaceUp());
                // put card behind the button
            }
        }
        // Brian added a comment
    }
}

class TestPane extends JLayeredPane {
    public TestPane(Model gameModel) {
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        List<Card> tempCardList = gameModel.getMasterList();
        int x = 20, y = 1000;
//        for (Card card : tempCardList) {
//            System.out.println(card);
//            try {
//                ImageIcon imageIcon = card.getCardImage();
//                Image image = imageIcon.getImage();
//                Image newimg = image.getScaledInstance(150, 217, java.awt.Image.SCALE_SMOOTH);
//                imageIcon = new ImageIcon(newimg);
//                JLabel label = new JLabel(imageIcon);
//                label.setSize(label.getPreferredSize());
//                label.setLocation(x, y);
//                MouseHandler mh  = new MouseHandler();
//                label.addMouseListener(mh);
//                label.addMouseMotionListener(mh);
//                add(label);
//                x += 10;
//            } catch (Exception exp) {
//                exp.printStackTrace();
//            }
//        }
        for (int i = 0; i < tempCardList.size(); i++) {
//            System.out.println(tempCardList.get(i));
            try {
                ImageIcon imageIcon = tempCardList.get(i).getCardImage();
                Image image = imageIcon.getImage();
                Image newimg = image.getScaledInstance(150, 217, java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
                JLabel label = new JLabel(imageIcon);
                label.setSize(label.getPreferredSize());
                label.setLocation(x, y);
//                MouseHandler mh  = new MouseHandler();
//                label.addMouseListener(mh);
//                label.addMouseMotionListener(mh);
                add(label);
                y -= 25;
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }
}
