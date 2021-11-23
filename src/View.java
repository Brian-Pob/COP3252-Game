import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Stack;


public class View extends JFrame{
    public static final Color VERY_DARK_GREEN = new Color(0, 102, 0);
    private final Model gameModel;
    private TestPane tp;

    public View(Model gameModel) {
        this.gameModel = gameModel;
    }

    public void initializeGame() {
        this.tp = new TestPane(gameModel);
        JFrame frame = new JFrame("Klondike Solitaire");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // uncomment to make the window fullscreen
//        frame.setSize(1440, 850);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        JButton newGame = new JButton("New Game");
        JButton undo = new JButton("Undo");

        panel.add(newGame); // Components Added using Flow Layout
        panel.add(undo);

        createButtons(gameModel, frame, 25, 275, 202, 175, 500, 7, 't'); //create tableau buttons
        createButtons(gameModel, frame, 25, 75, 150, 150, 200, 4, 'f'); //create foundation buttons
        createButtons(gameModel, frame, 1100, 75, 150, 150, 200, 1, 'i'); //create draw/discard buttons
        createButtons(gameModel, frame, 1250, 75, 150, 150, 200, 1, 'd'); //create draw buttons

        // Text Area at the Center
        frame.getContentPane().setBackground(VERY_DARK_GREEN);
        frame.getContentPane().add(BorderLayout.NORTH, panel);

        frame.add(tp);
        frame.setVisible(true);
        frame.setResizable(false);

    }

    void createButtons(Model gameModel ,JFrame frame, int x, int y, int xOffset, int width, int height, int numOfBtns, char btnType) {
        JButton[] btns = new JButton[numOfBtns];
        for (JButton jButton : btns) {
            jButton = new JButton("Button");
            switch (btnType) {
                case 'd':
                    jButton.addActionListener(e -> {
                        Card tempCard = gameModel.peekCard();
                        Card tempCard2 = gameModel.drawCard();
                        JLayeredPane lpane = getLayeredPane();
                        int yStarttemp = 65, xStarttemp = 1125;
//                        tp.addingDraw(gameModel);
                        try {
                            System.out.println("Inside try");
                            if (tempCard == null) {
                                tp.removeDrawCard();
                            } else {
                                tp.addDrawCard();
                                ImageIcon imageIcon = tempCard2.getCardImage();
                                Image image = imageIcon.getImage();
                                Image newimg = image.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
                                imageIcon = new ImageIcon(newimg);
                                JLabel label = new JLabel(imageIcon);
                                label.setSize(label.getPreferredSize());
                                label.setLocation(xStarttemp, yStarttemp);
                                lpane.add(label, 0);
                                lpane.setVisible(true);
                                frame.getContentPane().add(lpane);
                                frame.setVisible(true);
                            }
                        } catch (Exception exp) {
                            exp.printStackTrace();
                        }
                    });
                    break;
                case 'i':
                    break;
                case 'f':
                    break;
                case 't':
                    break;
            }
            jButton.setSize(width, height);
            jButton.setOpaque(false);
            jButton.setContentAreaFilled(false);
            jButton.setBorderPainted(false);
            frame.getContentPane().add(jButton);
            jButton.setLocation(x, y);
            jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            x += xOffset;
        }
    }
}

class TestPane extends JLayeredPane {
    private JLabel drawCardLabel;
    public TestPane(Model gameModel) {
//        this.drawCardLabel = new JLabel();
        addingTableau(gameModel);
//        addingFoundation(gameModel);
        addingDraw(gameModel);
    }

    public void addingDraw(Model gameModel) {
        int yStart = 65, xStart = 1275;
        int yOffset = 0, xOffset = 2;
        int x = xStart, y = yStart - yOffset;
        Card tempCard = gameModel.drawCard();
        try {
            ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("back.png")));
            if (gameModel.getDrawPile().peek().isFaceUp()) {
                imageIcon = gameModel.getDrawPile().peek().getCardImage();
            }
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            drawCardLabel = new JLabel(imageIcon);
            drawCardLabel.setSize(drawCardLabel.getPreferredSize());
            drawCardLabel.setLocation(x, y);
            add(drawCardLabel);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    void removeDrawCard() {
        this.remove(drawCardLabel);
    }

    void addDrawCard() {
        this.add(drawCardLabel, 0);
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
                    ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("back.png")));
                    if (gameModel.getTableau()[i].get(j).isFaceUp()) {
                        imageIcon = gameModel.getTableau()[i].get(j).getCardImage();
                    }
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
        // Brian added a comment
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
////        }
//    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }
}

// if a button has not been pressed, mark it as selected

// if another button has been pressed
// try to move cards from the first button stack to the next button stack

// you can move from discard pile to tableau, or foundation
// you can move from tableau to tableau, or foundation
