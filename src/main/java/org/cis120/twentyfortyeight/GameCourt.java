package org.cis120.twentyfortyeight;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * The GameCourt class implements the overall game logic and event-handling from the GUI.
 * There is also the logic of how to calculate and update scores and the numbers in the squares.
 * It handles keyboard events (eg: up) to trigger board updates.
 */
public class GameCourt extends JPanel {
    public static final int COURT_WIDTH = 600;
    public static final int COURT_HEIGHT = 600;
    public static final int THICKNESS = 5;

    private int[][] numBoard = new int[4][4];
    private Map<Integer, Tile> map = new HashMap<>();

    private int currScore;
    private ScoreListener listener;

    /**
     * This is the GameCourt constructor. Handles all events and logics.
     */
    public GameCourt() {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(new Color(187, 173, 160)));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();

        numInMap();
        initialBoard();

        // This key listener allows the square to move as long as an arrow key
        // is pressed, by changing the square's velocity accordingly. (The tick
        // method below actually moves the square.)
        addKeyListener(new KeyAdapter() {

            /**
             * Handles keyboard events.
             * @param e KeyEvent
             */
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    onKeyLeft(numBoard, false);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    onKeyRight(numBoard, false);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    onKeyDown(numBoard, false);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    onKeyUp(numBoard, false);
                }

                if (hasLost()) {
                    listener.result("You lose!");
                }

                repaint();
            }
        });
    }

    /**
     * Add score listener.
     * @param listener ScoreListener
     */
    public void addScoreListener(ScoreListener listener) {
        this.listener = listener;
    }

    /**
     * Move the tiles to the left of board when left key is pressed.
     * @param numBoard the current state of the game board
     * @param detect true if running this method is to check to see if there can be a valid move
     */
    private void onKeyLeft(int[][] numBoard, boolean detect) {
        boolean hasMoved = false;
        for (int r = 0; r < 4; r++) {
            List<Integer> temp = new ArrayList<>();

            for (int c = 0; c < 4; c++) {
                if (numBoard[r][c] != 0) {
                    temp.add(numBoard[r][c]);
                    if (temp.size() - 1 != c) {
                        hasMoved = true;
                    }
                }
            }

            int size = temp.size();

            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (i + 1 < size && temp.get(i).equals(temp.get(i + 1))) {
                    int mergedNum = temp.get(i) * 2;
                    result.add(mergedNum);
                    hasMoved = true;
                    if (!detect) {
                        currScore += mergedNum;
                        listener.onUpdate(currScore);
                    }
                    i++;
                } else {
                    result.add(temp.get(i));
                }
            }

            for (int c = 0; c < 4; c++) {
                if (c < result.size()) {
                    numBoard[r][c] = result.get(c);
                } else {
                    numBoard[r][c] = 0;
                }
            }
        }

        if (hasMoved) {
            generateRandomTwo();
        }
    }

    /**
     * Move the tiles to the right of board when right key is pressed.
     * @param numBoard the current state of the game board
     * @param detect true if running this method is to check to see if there can be a valid move
     */
    private void onKeyRight(int[][] numBoard, boolean detect) {
        rotateDown(numBoard);
        onKeyLeft(numBoard, detect);
        rotateDown(numBoard);
    }

    /**
     * Move the tiles to the top of board when up key is pressed.
     * @param numBoard the current state of the game board
     * @param detect true if running this method is to check to see if there can be a valid move
     */
    private void onKeyUp(int[][] numBoard, boolean detect) {
        rotateLeft(numBoard);
        onKeyLeft(numBoard, detect);
        rotateRight(numBoard);
    }

    /**
     * Move the tiles to the bottom of board when down key is pressed.
     * @param numBoard the current state of the game board
     * @param detect true if running this method is to check to see if there can be a valid move
     */
    private void onKeyDown(int[][] numBoard, boolean detect) {
        rotateRight(numBoard);
        onKeyLeft(numBoard, detect);
        rotateLeft(numBoard);
    }

    /**
     * Rotate the 2D array to the right (90 degrees).
     * @param board the 4x4 game board
     */
    private void rotateRight(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        int[][] temp = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int data = board[i][j];
                temp[j][rows - i - 1] = data;
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = temp[i][j];
            }
        }
    }

    /**
     * Rotate the 2D array to the left (90 degrees).
     * @param board the 4x4 game board
     */
    private void rotateLeft(int[][] board) {
        for (int i = 0; i < 3; i++) {
            rotateRight(board);
        }
    }

    /**
     * Rotate the 2D array 180 degrees.
     * @param board the 4x4 game board
     */
    private void rotateDown(int[][] board) {
        for (int i = 0; i < 2; i++) {
            rotateRight(board);
        }
    }

    /**
     * Adds the possible numbers and tiles to the map that stores the information about tiles.
     */
    private void numInMap() {
        map.put(0, new EmptyTile());
        map.put(2, new NumberedTile(new Color(238, 228, 218), 2));
        map.put(4, new NumberedTile(new Color(236, 204, 202), 4));
        map.put(8, new NumberedTile(new Color(242, 177, 121), 8));
        map.put(16, new NumberedTile(new Color(245, 149, 101), 16));
        map.put(32, new NumberedTile(new Color(245, 124, 95), 32));
        map.put(64, new NumberedTile(new Color(246, 93, 59), 64));
        map.put(128, new NumberedTile(new Color(237, 206, 113), 128));
        map.put(256, new NumberedTile(new Color(237, 204, 99), 256));
        map.put(512, new NumberedTile(new Color(228, 192, 42), 512));
        map.put(1024, new NumberedTile(new Color(239, 197, 63), 1024));
        map.put(2048, new NumberedTile(new Color(238, 194, 46), 2048));
        map.put(4096, new NumberedTile(new Color(103, 214, 145), 4096));
        map.put(8192, new NumberedTile(new Color(39, 187, 103), 8192));
        map.put(16384, new NumberedTile(new Color(35, 140, 81), 16384));
        map.put(32768, new NumberedTile(new Color(77, 171, 0), 32768));
        map.put(65536, new NumberedTile(new Color(69, 140, 2), 65536));
    }

    /**
     * Everywhere on the 4x4 board is filled with a number
     * @return true if the board is full
     */
    private boolean isFull() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (numBoard[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Determines whether the user or current game has lost.
     * @return true if the user has lost
     */
    public boolean hasLost() {
        if (!isFull()) {
            return false;
        }

        int[][] temp = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[i][j] = numBoard[i][j];
            }
        }

        onKeyLeft(temp, true);
        onKeyRight(temp, true);
        onKeyDown(temp, true);
        onKeyUp(temp, true);

        if (!Arrays.deepEquals(temp, numBoard)) {
            return false;
        }

        return true;
    }

    /**
     * Generate the number 2 tile randomly on an empty space of the game.
     */
    public void generateRandomTwo() {
        if (isFull()) {
            return;
        }

        int numEmpty = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (numBoard[i][j] == 0) {
                    numEmpty++;
                }
            }
        }

        int random = 1 + new Random().nextInt(numEmpty);
        int temp = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (numBoard[i][j] == 0) {
                    temp++;

                    if (temp == random) {
                        numBoard[i][j] = 2;
                        return;
                    }
                }
            }
        }
    }

    /**
     * reset the game to its initial state
     */
    public void reset() {
        // Make sure that this component has the keyboard focus
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                numBoard[i][j] = 0;
            }
        }
        generateRandomTwo();
        currScore = 0;
        listener.onUpdate(currScore);
        listener.result("Running...");
        repaint();
        requestFocusInWindow();
    }

    /**
     * Draws the game board.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(187, 173, 160));

        // Draws the tiles
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = numBoard[i][j];
                Tile tile = map.get(num);
                tile.draw(g, j * NumberedTile.SIZE, i * NumberedTile.SIZE);
            }
        }

        // Draws the borders of the grid
        g.setColor(new Color(187, 173, 160));
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(THICKNESS));
        for (int i = 0; i < 5; i++) {
            g.drawLine(i * NumberedTile.SIZE, 0, i * NumberedTile.SIZE, 4 * NumberedTile.SIZE);
            g.drawLine(0, i * NumberedTile.SIZE, 4 * NumberedTile.SIZE, i * NumberedTile.SIZE);
        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }

    /**
     * This is the initial board state. Generates a random 2 tile when the game starts or resets.
     */
    private void initialBoard() {
        generateRandomTwo();
    }
}

