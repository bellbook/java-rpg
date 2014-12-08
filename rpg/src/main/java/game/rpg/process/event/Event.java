package game.rpg.process.event;

import game.rpg.util.Python;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Event {

    // Constant
    protected static final int CHIP_SIZE = 16;

    // Script for event
    private String scriptFileName;

    // Parameter
    protected String name;
    protected Image graphic;
    protected int x, y; // bottom left point
    protected int width, height;
    protected int mapX, mapY; // map coordinate

    // Collision detection
    protected Rectangle collisionArea = new Rectangle(); // world coordinate

    public Event() {
        this.width = 1;
        this.height = 1;
        this.collisionArea.setSize(CHIP_SIZE * width, CHIP_SIZE);
    }

    public void exec() {
        if (scriptFileName != null)
            Python.execfile(scriptFileName);
    }

    public void draw(Graphics g, ImageObserver o) {
        if (graphic == null)
            return;

        int sx1 = 0;
        int sy1 = 0;
        g.drawImage(
                graphic,
                x,
                y - CHIP_SIZE * height,
                x + CHIP_SIZE * width,
                y,
                sx1,
                sy1,
                sx1 + CHIP_SIZE * width,
                sy1 + CHIP_SIZE * height,
                o
        );
    }

    public void setScript(String scriptFileName) {
        this.scriptFileName = scriptFileName;
    }

    public String getName() {
        return name;
    }

    public Event setName(String name) {
        this.name = name;
        return this;
    }

    public Event setGraphic(String chipFileName) {
        this.graphic = new ImageIcon(chipFileName).getImage();
        return this;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Event setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        this.collisionArea.setLocation(x, (int) (y - collisionArea.getHeight()));
        return this;
    }

    public Event setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public int getMapX() {
        return mapX;
    }

    public Event setMapX(int mapX) {
        this.mapX = mapX;
        return this;
    }

    public int getMapY() {
        return mapY;
    }

    public Event setMapY(int mapY) {
        this.mapY = mapY;
        return this;
    }

    public Event setBounds(int height) {
        this.collisionArea.setSize((int) collisionArea.getWidth(), height);
        return this;
    }

    public Rectangle getCollisionArea() {
        return collisionArea;
    }

}
