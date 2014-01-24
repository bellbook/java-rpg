package game.rpg.process.window;

import java.awt.Color;
import java.awt.Graphics;

public class SystemWindow {

    private static final int EDGE = 2;
    private static final int ARC_WIDTH  = 10;
    private static final int ARC_HEIGHT = 10;
    private static final int INNER_ARC_WIDTH  = 5;
    private static final int INNER_ARC_HEIGHT = 5;

    protected final int outerX, outerY;
    protected final int outerWidth, outerHeight;
    protected final int innerX, innerY;
    protected final int innerWidth, innerHeight;

    public SystemWindow(int x, int y, int width, int height) {
        this.outerX = x;
        this.outerY = y;
        this.outerWidth = width;
        this.outerHeight = height;

        innerX = x + EDGE;
        innerY = y + EDGE;

        innerWidth  = width  - EDGE * 2;
        innerHeight = height - EDGE * 2;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(outerX, outerY, outerWidth, outerHeight, ARC_WIDTH, ARC_HEIGHT);

        g.setColor(Color.BLACK);
        g.fillRoundRect(innerX, innerY, innerWidth, innerHeight, INNER_ARC_WIDTH, INNER_ARC_HEIGHT);

        g.setColor(Color.BLACK);
        g.drawRoundRect(outerX, outerY, outerWidth, outerHeight, ARC_WIDTH, ARC_HEIGHT);

        g.setColor(Color.LIGHT_GRAY);
        g.drawRoundRect(innerX, innerY, innerWidth, innerHeight, INNER_ARC_WIDTH, INNER_ARC_HEIGHT);
    }

}
