package org.cis120.twentyfortyeight;

import java.awt.*;

/**
 * NumberedTile class is a ubtype of Tile class. Responsible for tiles that actually have numbers.
 * It adds numbers and colors, and draws the squares with corresponding colors and numbers in the
 * middle.
 */
public class NumberedTile extends Tile {
    public static final int SIZE = 150;
    public static final int INIT_POS_X = 0;
    public static final int INIT_POS_Y = 0;

    private Color color;
    private int number;

    /**
     * The NumberedTile constructor that specifies color and number of the tile.
     * @param color color of the tile (corresponds to their numbers)
     * @param number number of the tile
     */
    public NumberedTile(Color color, int number) {
        super(INIT_POS_X, INIT_POS_Y, SIZE);
        this.color = color;
        this.number = number;
    }

    /**
     * Draws each numbered tile on the board
     * @param g The <code>Graphics</code> context used for drawing the object.
     *          Remember graphics contexts that we used in OCaml, it gives the
     *          context in which the object should be drawn (a canvas, a frame,
     * @param offsetX the offset on the x direction of the starting position of tile
     * @param offsetY the offset on the y direction of the starting position of tile
     */
    @Override
    public void draw(Graphics g, int offsetX, int offsetY) {
        setPx(offsetX);
        setPy(offsetY);
        g.setColor(this.color);
        g.fillRect(this.getPx(), this.getPy(), this.getSize(), this.getSize());
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 27));
        FontMetrics metrics = g.getFontMetrics();
        int width = metrics.stringWidth(String.valueOf(number));
        int height = metrics.getHeight();
        String text = String.valueOf(number);
        g.drawString(text, getPx() + (SIZE - width) / 2, getPy() + (SIZE + height) / 2);
    }
}
