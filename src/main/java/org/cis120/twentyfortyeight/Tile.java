package org.cis120.twentyfortyeight;

import java.awt.*;

/**
 * This is an abstract class that provides basic functions, such as position setters, for tiles.
 */
public abstract class Tile {
    /*
     * Current position of the object (in terms of graphics coordinates)
     * Coordinates are given by the upper-left hand corner of the object.
     */
    private int px;
    private int py;

    /* Size of object, in pixels. */
    private int size;


    /**
     * Constructor
     */
    public Tile(
            int px, int py, int size
    ) {
        this.px = px;
        this.py = py;
        this.size = size;

    }

    /***
     * GETTERS
     **********************************************************************************/
    public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }

    public int getSize() {
        return this.size;
    }


    /**************************************************************************
     * SETTERS
     **************************************************************************/
    public void setPx(int px) {
        this.px = px;
    }

    public void setPy(int py) {
        this.py = py;
    }


    /**
     * Default draw method that provides how the object should be drawn in the
     * GUI. This method does not draw anything. Subclass should override this
     * method based on how their object should appear.
     *
     * @param g The <code>Graphics</code> context used for drawing the object.
     *          Remember graphics contexts that we used in OCaml, it gives the
     *          context in which the object should be drawn (a canvas, a frame,
     *          etc.)
     * @param offsetX the offset on the x direction of the starting position of tile
     * @param offsetY the offset on the y direction of the starting position of tile
     */
    public abstract void draw(Graphics g, int offsetX, int offsetY);
}
