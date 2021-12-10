package org.cis120.twentyfortyeight;

import java.awt.*;

public abstract class Tile {
    /*
     * Current position of the object (in terms of graphics coordinates)
     *
     * Coordinates are given by the upper-left hand corner of the object. This
     * position should always be within bounds:
     * 0 <= px <= maxX 0 <= py <= maxY
     */
    private int px;
    private int py;

    /* Size of object, in pixels. */
    private int size;

    /* Velocity: number of pixels to move every time move() is called. */
    private int vx;
    private int vy;

    /*
     * Upper bounds of the area in which the object can be positioned. Maximum
     * permissible x, y positions for the upper-left hand corner of the object.
     */
    private int maxX;
    private int maxY;


    /**
     * Constructor
     */
    public Tile(
            int vx, int vy, int px, int py, int size, int courtWidth,
            int courtHeight
    ) {
        this.vx = vx;
        this.vy = vy;
        this.px = px;
        this.py = py;
        this.size = size;

        // take the width and height into account when setting the bounds for
        // the upper left corner of the object.
        this.maxX = courtWidth - size;
        this.maxY = courtHeight - size;

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

    public int getVx() {
        return this.vx;
    }

    public int getVy() {
        return this.vy;
    }

    public int getSize() {
        return this.size;
    }


    /**************************************************************************
     * SETTERS
     **************************************************************************/
    public void setPx(int px) {
        this.px = px;
        clip();
    }

    public void setPy(int py) {
        this.py = py;
        clip();
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    /**************************************************************************
     * UPDATES AND OTHER METHODS
     **************************************************************************/

    /**
     * Prevents the object from going outside of the bounds of the area
     * designated for the object (i.e. Object cannot go outside of the active
     * area the user defines for it).
     */
    private void clip() {
        this.px = Math.min(Math.max(this.px, 0), this.maxX);
        this.py = Math.min(Math.max(this.py, 0), this.maxY);
    }

    /**
     * Moves the object by its velocity. Ensures that the object does not go
     * outside its bounds by clipping.
     */
    public void move() {
        this.px += this.vx;
        this.py += this.vy;

        clip();
    }


    /**
     * Determine whether the game object will hit a wall in the next time step.
     * If so, return the direction of the wall in relation to this game object.
     *
     * @return Direction of impending wall, null if all clear.
     */
    public org.cis120.twentyfortyeight.Direction hitWall() {
        if (this.px + this.vx < 0) {
            return org.cis120.twentyfortyeight.Direction.LEFT; //TODO why is it left? shouldn't it be right?
        } else if (this.px + this.vx > this.maxX) {
            return org.cis120.twentyfortyeight.Direction.RIGHT;
        }

        if (this.py + this.vy < 0) {
            return org.cis120.twentyfortyeight.Direction.UP;
        } else if (this.py + this.vy > this.maxY) {
            return org.cis120.twentyfortyeight.Direction.DOWN;
        } else {
            return null;
        }
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
     */
    public abstract void draw(Graphics g, int offsetX, int offsetY);
}
