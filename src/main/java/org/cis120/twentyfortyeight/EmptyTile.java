package org.cis120.twentyfortyeight;

import java.awt.Color;
import java.awt.Graphics;

/**
 * EmptyTile is a subtype of Tile class. EmptyTile are responsible for empty tiles and draw the
 * empty tiles with white color on the position according to their offset values.
 */
public class EmptyTile extends Tile {
    public static final int SIZE = 150;
    public static final int INIT_POS_X = 0;
    public static final int INIT_POS_Y = 0;

    /**
     * Constructor of empty tiles. Maintain the characteristics of its superclass.
     */
    public EmptyTile() {
        super(INIT_POS_X, INIT_POS_Y, SIZE);
    }

    /**
     * Draws each tile on the board
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
        g.setColor(Color.WHITE);
        g.fillRect(this.getPx(), this.getPy(), this.getSize(), this.getSize());
    }
}
