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
        panel.add(newGame); // Components Added using Flow Layout
        panel.add(undo);


        // Text Area at the Center
        frame.getContentPane().setBackground(VERY_DARK_GREEN);
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.add(tp);
        frame.setVisible(true);

        // Brian added a comment
    }
}

class TestPane extends JLayeredPane {
    public TestPane(Model gameModel) {
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        List<Card> tempCardList = gameModel.getMasterList();
        int x = 0, y = 0;
        for (Card card : tempCardList) {
            System.out.println(card);

            try {
                ImageIcon imageIcon = card.getCardImage();
                Image image = imageIcon.getImage();
                Image newimg = image.getScaledInstance(150, 217, java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
                JLabel label = new JLabel(imageIcon);
                label.setSize(label.getPreferredSize());
                label.setLocation(x, y);
                MouseHandler mh  = new MouseHandler();
                label.addMouseListener(mh);
                label.addMouseMotionListener(mh);
                add(label);
                y += 150;
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
    }

    private BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }

    public class MouseHandler extends MouseAdapter {

        private Point offset;

        @Override
        public void mousePressed(MouseEvent e) {
            JLabel label = (JLabel) e.getComponent();
            moveToFront(label);
            offset = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int x = e.getPoint().x - offset.x;
            int y = e.getPoint().y - offset.y;
            Component component = e.getComponent();
            Point location = component.getLocation();
            location.x += x;
            location.y += y;
            component.setLocation(location);
        }
    }
}
