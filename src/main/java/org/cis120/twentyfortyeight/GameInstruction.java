package org.cis120.twentyfortyeight;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Paths;


public class GameInstruction implements Runnable {
    private final JFrame frame = new JFrame("2048");
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for
        // local variables.

        // Top-level frame in which game components live.
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        frame.setLocation(400, 400);
        frame.setSize(600, 600);

        // Status panel
        final JPanel instructorPanel = new JPanel();
        frame.add(instructorPanel, BorderLayout.CENTER);

        JLabel label = new JLabel("<html>Welcome to 2048!<br>" + "<html> <br>"
                + "<html>Instructions:<br>"
                + "<html>1. Press start game button to start the game<br>"
                + "<html>2. Use keyboard directions (eg: up) to move the numbered tiles in the direction you want<br>"
                + "<html>3. When two tiles with the same number collide, they merge into one tile with their sum<br>"
                + "<html>4. When you cannot move any tiles, you lose<br>"
                + "<html>5. You can press the reset button to reset the game<br>"
        );
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

    private void startGame() {
        Runnable game = new org.cis120.twentyfortyeight.RunTwentyFortyEight();
        SwingUtilities.invokeLater(game);
        frame.dispose();
    }
}
