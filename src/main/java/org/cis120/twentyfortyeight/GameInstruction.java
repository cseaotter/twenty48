package org.cis120.twentyfortyeight;

import javax.swing.*;
import java.awt.*;

/**
 * This class is responsible for the game instruction window that pops up when I run the game.
 * After I press "start game" button, it will run the RunTwentyFortyEight class and run the game.
 */
public class GameInstruction implements Runnable {
    private final JFrame frame = new JFrame("2048");

    /**
     * Runs the game instruction window. Displays instruction text and start game button.
     */
    public void run() {
        frame.setLocation(400, 400);
        frame.setSize(600, 400);

        // Status panel
        final JPanel instructorPanel = new JPanel();
        frame.add(instructorPanel, BorderLayout.CENTER);

        JLabel label = new JLabel("<html>Welcome to 2048!<br>" + "<br>" + "Instructions:<br>"
                + "1. Press start game button to start the game<br>"
                + "2. Use keyboard directions (eg: up) to move the numbered tiles in the direction"
                + " you want<br>"
                + "3. When two tiles with the same number collide, they merge into one tile with "
                + "their sum<br>"
                + "4. When you cannot move any tiles, you lose<br>"
                + "5. You can press the reset button to reset the game</html>");

        label.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        instructorPanel.add(label, BorderLayout.CENTER);

        final JPanel statusPanel = new JPanel();
        frame.add(statusPanel, BorderLayout.SOUTH);

        JButton button = new JButton("start game!");
        button.addActionListener(e -> startGame());
        statusPanel.add(button, BorderLayout.PAGE_END);

        // Put the frame on the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * When the start game button is pressed, this method will run the RunTwentyFortyEight class
     * to start the game.
     */
    private void startGame() {
        Runnable game = new org.cis120.twentyfortyeight.RunTwentyFortyEight();
        SwingUtilities.invokeLater(game);
        frame.dispose();
    }
}
