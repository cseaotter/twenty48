package org.cis120.twentyfortyeight;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Paths;


public class RunTwentyFortyEight implements Runnable, ScoreListener {
    static final String PATH_TO_OUTPUT = "files/highest_score.txt";
    private int highestScore;
    private JLabel highScoreLabel;
    private JLabel currScoreLabel;
    private JLabel status;


    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for
        // local variables.

        // Top-level frame in which game components live.
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("2048");
        frame.setLocation(400, 400);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final org.cis120.twentyfortyeight.GameCourt court = new GameCourt();
        court.addScoreListener(this);
        frame.add(court, BorderLayout.CENTER);


        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        readHighScore();


        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> court.reset());
        highScoreLabel = new JLabel("Highest Score: " + highestScore);
        currScoreLabel = new JLabel("Current Score: " + 0);

        control_panel.add(highScoreLabel, BorderLayout.WEST); // left
        control_panel.add(reset, BorderLayout.CENTER);
        control_panel.add(currScoreLabel, BorderLayout.EAST); // right


        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        if (court.hasLost()) {
            status.setText("You lose!");
        }

        // Start game
        court.requestFocusInWindow();
    }

    private void readHighScore() {
        // highest score tracker
        File tempFile = new File(PATH_TO_OUTPUT);
        if (tempFile.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(PATH_TO_OUTPUT));
                String str = br.readLine();
                try {
                    highestScore = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    highestScore = 0;
                }

                System.out.println("highest = " + highestScore);
                br.close();
            } catch (FileNotFoundException e) {
                System.out.println("Ran into FileNotFound Exception");
            } catch (IOException e) {
                System.out.println("Ran into I/O Exception1");
            }
        } else {
            try {
                tempFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Ran into I/O Exception2");
            }
        }
    }

    private void updateHighestScore() {
        File file = Paths.get(PATH_TO_OUTPUT).toFile();
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            String str = Integer.toString(highestScore);
            bw.write(str);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Ran into I/O Exception3");
        }
    }

    @Override
    public void onUpdate(int currScore) {
        currScoreLabel.setText("Current Score: " + currScore);
        if (currScore > highestScore) {
            highestScore = currScore;
            System.out.println("Curr = " + currScore + ", High = " + highestScore);
            updateHighestScore();
            highScoreLabel.setText("Highest Score: " + highestScore);
        }
    }

    @Override
    public void result(String res) {
        status.setText(res);
    }
}
