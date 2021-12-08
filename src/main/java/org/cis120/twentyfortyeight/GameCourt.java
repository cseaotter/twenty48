package org.cis120.twentyfortyeight;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class GameCourt extends JPanel {
    public static final int COURT_WIDTH = 400;
    public static final int COURT_HEIGHT = 400;

    private int[][] numBoard = new int[4][4];
    private Tile[][] blocks = new Tile[4][4];
    private Map<Integer, NumberedTile> map = new HashMap<>();

    public GameCourt(JLabel status) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(new Color(187, 173, 160)));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();

        numInMap(); // TODO: idk where to put it??
        testTile();

        // This key listener allows the square to move as long as an arrow key
        // is pressed, by changing the square's velocity accordingly. (The tick
        // method below actually moves the square.)
        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    onKeyLeft();
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

                } else if (e.getKeyCode() == KeyEvent.VK_UP) {

                }
            }

            public void keyReleased(KeyEvent e) {
                generateRandomTwo();
            }
        });

        //repaint();
    }

    private void onKeyLeft() {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                int temp = c;
                int curr = numBoard[r][temp];

                while (curr != 0 && temp > 0) {
                    int left = numBoard[r][temp - 1];
                    if (left != 0) {
                        break;
                    }
                    temp--;
                }

                numBoard[r][temp] = curr;
                if (temp != c) {
                    numBoard[r][c] = 0;
                }
            }
        }

        printBoard();
    }

    private void printBoard() {
        System.out.println("board = " + Arrays.deepToString(numBoard));
    }

    private void numInMap() {
        map.put(2, new NumberedTile(COURT_WIDTH, COURT_HEIGHT, new Color(238, 228, 218), 2));
        map.put(4, new NumberedTile(COURT_WIDTH, COURT_HEIGHT, new Color(236, 204, 202), 4));
        map.put(8, new NumberedTile(COURT_WIDTH, COURT_HEIGHT, new Color(242, 177, 121), 8));
        map.put(16, new NumberedTile(COURT_WIDTH, COURT_HEIGHT, new Color(245, 149, 101), 16));
        map.put(32, new NumberedTile(COURT_WIDTH, COURT_HEIGHT, new Color(245, 124, 95), 32));
        map.put(64, new NumberedTile(COURT_WIDTH, COURT_HEIGHT, new Color(246, 93, 59), 64));
        map.put(128, new NumberedTile(COURT_WIDTH, COURT_HEIGHT, new Color(237, 206, 113), 128));
        map.put(256, new NumberedTile(COURT_WIDTH, COURT_HEIGHT, new Color(237, 204, 99), 256));
        map.put(512, new NumberedTile(COURT_WIDTH, COURT_HEIGHT, new Color(228, 192, 42), 512));
        map.put(1024, new NumberedTile(COURT_WIDTH, COURT_HEIGHT, new Color(239, 197, 63), 1024));
        map.put(2048, new NumberedTile(COURT_WIDTH, COURT_HEIGHT, new Color(238, 194, 46), 2048));
        map.put(4096, new NumberedTile(COURT_WIDTH, COURT_HEIGHT, new Color(103, 214, 145), 4096));
        map.put(8192, new NumberedTile(COURT_WIDTH, COURT_HEIGHT, new Color(39, 187, 103), 8192));
        map.put(16384, new NumberedTile(COURT_WIDTH, COURT_HEIGHT, new Color(35, 140, 81), 16384));
        map.put(32768, new NumberedTile(COURT_WIDTH, COURT_HEIGHT, new Color(77, 171, 0), 32768));
        map.put(65536, new NumberedTile(COURT_WIDTH, COURT_HEIGHT, new Color(69, 140, 2), 65536));
    }

    /**
     * Everywhere on the 4x4 board is filled with a number
     * @return
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

    public void generateRandomTwo() {
        if (!isFull()) {
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
        // TODO: empties the board
        // TODO: randomize a 2 somewhere on the board
        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * Draws the game board.
     *
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draws board grid
        g.setColor(new Color(187, 173, 160));
        g.drawLine(100, 0, 100, 400);
        g.drawLine(200, 0, 200, 400);
        g.drawLine(300, 0, 300, 400);
        g.drawLine(0, 100, 400, 100);
        g.drawLine(0, 200, 400, 200);
        g.drawLine(0, 300, 400, 300);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = numBoard[i][j];
                if (num != 0) {
                    NumberedTile tile = map.get(num);
                    tile.draw(g, j * 100, i * 100);
                }
            }
        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }

    private void testTile() {
        numBoard[0][0] = 2;
        numBoard[1][1] = 4;
    }
}

