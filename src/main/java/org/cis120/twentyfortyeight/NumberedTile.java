package org.cis120.twentyfortyeight;

import java.awt.*;

public class NumberedTile extends Tile {
    public static final int SIZE = 150;
    public static final int INIT_POS_X = 0;
    public static final int INIT_POS_Y = 0;

    private Color color;
    private int number;

    public NumberedTile(Color color, int number) {
        super(INIT_POS_X, INIT_POS_Y, SIZE);
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
        FontMetrics metrics = g.getFontMetrics();
        int width = metrics.stringWidth(String.valueOf(number));
        int height = metrics.getHeight();
        String text = String.valueOf(number);
        g.drawString(text, getPx() + (SIZE - width) / 2, getPy() + (SIZE + height) / 2);
    }
}
