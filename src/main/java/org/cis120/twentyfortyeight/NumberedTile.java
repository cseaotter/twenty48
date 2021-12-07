package org.cis120.twentyfortyeight;

import java.awt.*;

public class NumberedTile extends Tile {
    public static final int SIZE = 100;
    public static final int INIT_POS_X = 0;
    public static final int INIT_POS_Y = 0;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;

    private Color color;
    private int number;

    public NumberedTile (int courtWidth, int courtHeight, Color color, int number) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, courtWidth, courtHeight);
        this.color = color;
        this.number = number;
    }

    @Override
    public void draw(Graphics g, int offsetX, int offsetY) {
        setPx(offsetX);
        setPy(offsetY);
        g.setColor(this.color);
        g.fillRect(this.getPx(), this.getPy(), this.getSize(), this.getSize());
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 27));
        g.drawString(String.valueOf(number), getPx() + SIZE / 2, getPy() + SIZE / 2);

    }
}
