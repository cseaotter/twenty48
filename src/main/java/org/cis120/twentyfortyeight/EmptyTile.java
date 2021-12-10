package org.cis120.twentyfortyeight;

import java.awt.Color;
import java.awt.Graphics;

public class EmptyTile extends Tile {
    public static final int SIZE = 150;
    public static final int INIT_POS_X = 0;
    public static final int INIT_POS_Y = 0;

    public EmptyTile() {
        super(INIT_POS_X, INIT_POS_Y, SIZE);
    }

    @Override
    public void draw(Graphics g, int offsetX, int offsetY) {
        setPx(offsetX);
        setPy(offsetY);
        g.setColor(Color.WHITE);
        g.fillRect(this.getPx(), this.getPy(), this.getSize(), this.getSize());
    }
}
