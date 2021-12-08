package org.cis120.twentyfortyeight;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;


public class GameCourt extends JPanel {
    public static final int COURT_WIDTH = 600;
    public static final int COURT_HEIGHT = 600;

    private int[][] numBoard = new int[4][4];
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
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    onKeyRight();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    onKeyDown();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    onKeyUp();
                }

                repaint();
            }

            public void keyReleased(KeyEvent e) {
                //generateRandomTwo();
            }
        });

        //repaint();
    }

    private void onKeyLeft()  {
        boolean hasMoved = false;
        for (int r = 0; r < 4; r++) {
            List<Integer> temp = new ArrayList<>();

            for (int c = 0; c < 4; c++) {
                if (numBoard[r][c] != 0) {
                    temp.add(numBoard[r][c]);
                }
            }

            int size = temp.size();
            if (size != 0 && size != 4) {
                hasMoved = true;
            }

            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (i + 1 < size && temp.get(i) == temp.get(i + 1)) {
                        result.add(temp.get(i) * 2);
                        hasMoved = true;
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
        printBoard();
    }

    private void onKeyRight()  {
        boolean hasMoved = false;

        for (int r = 0; r < 4; r++) {
            List<Integer> temp = new ArrayList<>();

            for (int c = 0; c < 4; c++) {
                if (numBoard[r][c] != 0) {
                    temp.add(numBoard[r][c]);
                }
            }

            int size = temp.size();
            if (size != 0 && size != 4) {
                hasMoved = true;
            }

            List<Integer> result = new ArrayList<>();
            for (int i = size - 1; i >= 0; i--) {
                if (i - 1 >= 0 && temp.get(i) == temp.get(i - 1)) {
                        result.add(temp.get(i) * 2);
                        hasMoved = true;
                        i--;
                } else {
                    result.add(temp.get(i));
                }
            }
            //reverse
            for (int c = 3; c >= 0; c--) {
                if (3 - c < result.size()) {
                    numBoard[r][c] = result.get(3 - c);
                } else {
                    numBoard[r][c] = 0;
                }
            }
        }

        if (hasMoved) {
            generateRandomTwo();
        }
        printBoard();
    }

    private void onKeyUp() {
        boolean hasMoved = false;
        for (int c = 0; c < 4; c++) {
            List<Integer> temp = new ArrayList<>();

            for (int r = 0; r < 4; r++) {
                if (numBoard[r][c] != 0) {
                    temp.add(numBoard[r][c]);
                }
            }

            int size = temp.size();
            if (size != 0 && size != 4) {
                hasMoved = true;
            }

            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (i + 1 < size && temp.get(i) == temp.get(i + 1)) {
                    result.add(temp.get(i) * 2);
                    hasMoved = true;
                    i++;
                } else {
                    result.add(temp.get(i));
                }
            }

            for (int r = 0; r < 4; r++) {
                if (r < result.size()) {
                    numBoard[r][c] = result.get(r);
                } else {
                    numBoard[r][c] = 0;
                }
            }
        }
        if (hasMoved) {
            generateRandomTwo();
        }
        printBoard();
    }

    private void onKeyDown() {
        boolean hasMoved = false;
        for (int c = 0; c < 4; c++) {
            List<Integer> temp = new ArrayList<>();

            for (int r = 0; r < 4; r++) {
                if (numBoard[r][c] != 0) {
                    temp.add(numBoard[r][c]);
                }
            }

            int size = temp.size();
            if (size != 0 && size != 4) {
                hasMoved = true;
            }

            List<Integer> result = new ArrayList<>();
            for (int i = size - 1; i >= 0; i--) {
                if (i - 1 >= 0 && temp.get(i) == temp.get(i - 1)) {
                    result.add(temp.get(i) * 2);
                    hasMoved = true;
                    i--;
                } else {
                    result.add(temp.get(i));
                }
            }
            //reverse
            for (int r = 3; r >= 0; r--) {
                if (3 - r < result.size()) {
                    numBoard[r][c] = result.get(3 - r);
                } else {
                    numBoard[r][c] = 0;
                }
            }
        }
        if (hasMoved) {
            generateRandomTwo();
        }
        printBoard();
    }

    private void rightDownMovement(int outer, int inner) {

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
        g.drawLine(NumberedTile.SIZE, 0, NumberedTile.SIZE, 4 * NumberedTile.SIZE);
        g.drawLine(2 * NumberedTile.SIZE, 0, 2 * NumberedTile.SIZE, 4 * NumberedTile.SIZE);
        g.drawLine(3 * NumberedTile.SIZE, 0, 3 * NumberedTile.SIZE, 4 * NumberedTile.SIZE);
        g.drawLine(0, NumberedTile.SIZE, 4 * NumberedTile.SIZE, NumberedTile.SIZE);
        g.drawLine(0, 2 * NumberedTile.SIZE, 4 * NumberedTile.SIZE, 2 * NumberedTile.SIZE);
        g.drawLine(0, 3 * NumberedTile.SIZE, 4 * NumberedTile.SIZE, 3 * NumberedTile.SIZE);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = numBoard[i][j];
                if (num != 0) {
                    NumberedTile tile = map.get(num);
                    tile.draw(g, j * NumberedTile.SIZE, i * NumberedTile.SIZE);
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

