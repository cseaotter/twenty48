package org.cis120.twentyfortyeight;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Paths;


public class GameInstruction implements Runnable {
    private final JFrame frame = new JFrame("TOP LEVEL FRAME");
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

        JLabel label = new JLabel("<html>Use HTML to create<br>" + "a multiline message.");
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
