import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
    public static final Color VERY_DARK_GREEN = new Color(0, 102, 0);
    public static void main(String[] args) {
        JFrame frame = new JFrame("Klondike Solitaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000,1000);

        JPanel panel = new JPanel();
        JButton newGame = new JButton("New Game");
        JButton undo = new JButton("Undo");
        panel.add(newGame); // Components Added using Flow Layout
        panel.add(undo);

        // Text Area at the Center
        frame.getContentPane().setBackground(VERY_DARK_GREEN);
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.setVisible(true);

        // Brian added a comment
    }
}
