package game.rpg.process.map;

import game.rpg.process.attribute.Direction;
import game.rpg.process.event.Characters;
import game.rpg.process.event.Event;
import game.rpg.process.event.Player;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Map {

    // Constant
    private static final float COLLISION_RATE = 0.75f; // tile の 下から 3/4 の領域を当たり判定にする
    private static final EventComparator eventComparator = new EventComparator();

    // Parameter
    private int width, height;
    private final int tileSize;
    private Image tileSet;
    private int tileSetColumn;
    private int[][] background, middleground, foreground, collision;

    // Collision detection
    private final Rectangle collisionArea = new Rectangle();

    // Relationship
    private Player player;
    private final List<Event> events;

    Map(int tileSize) {
        this.tileSize = tileSize;
        events = new ArrayList<Event>();
        collisionArea.setSize(tileSize, (int) (tileSize * COLLISION_RATE));
    }

    public void draw(Graphics g, ImageObserver o) {
        // background
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                drawMapChip(background, x, y, g, o);

        // middleground
        if (events.isEmpty()) {
            for (int y = 0; y < height; y++)
                for (int x = 0; x < width; x++)
                    drawMapChip(middleground, x, y, g, o);
        } else {
            drawEvent(g, o);
        }

        // foreground
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                drawMapChip(foreground, x, y, g, o);
    }

    private void drawMapChip(int[][] layer, int x, int y,
            Graphics g, ImageObserver o) {
        int val = layer[y][x] - 1;

        int dx1 = x * tileSize;
        int dy1 = y * tileSize;
        int sx1 = val % tileSetColumn * tileSize;
        int sy1 = val / tileSetColumn * tileSize;
        g.drawImage(
                tileSet,
                dx1,
                dy1,
                dx1 + tileSize,
                dy1 + tileSize,
                sx1,
                sy1,
                sx1 + tileSize,
                sy1 + tileSize,
                o
        );
    }

    private void drawEvent(Graphics g, ImageObserver o) {
        Collections.sort(events, eventComparator);
        for (Event event : events)
            event.setMapY(convertEventCoordToMap(event.getY()));

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++)
                if (collision[y][x] != 0)
                    drawMapChip(middleground, x, y, g, o);
            // event
            for (Event event : events)
                if (event.getMapY() == y)
                    event.draw(g, o);
        }

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                if (collision[y][x] == 0)
                    drawMapChip(middleground, x, y, g, o);
    }

    private static class EventComparator implements Comparator<Event> {

        @Override
        public int compare(Event e1, Event e2) {
            int y1 = e1.getY();
            int y2 = e2.getY();
            if (y1 > y2)
                return 1;
            else if (y1 == y2)
                return 0;
            else
                return -1;
        }

    }

    /**
     * Collision detection.
     * Calculate in the world coordinate system.
     *
     * @param rect
     * @return
     */
    public boolean isHit(Rectangle rect) {
        return isHitMap(rect) | isHitEvent(rect);
    }

    private boolean isHitMap(Rectangle rect) {
        // out of range
        if (rect.getMinX() < 0 || rect.getMinY() < 0)
            return true;

        // out of range
        if (rect.getMaxX() > tileSize * width
                || rect.getMaxY() > tileSize * height)
            return true;

        int mapMinX = (int) (rect.getMinX() / tileSize);
        int mapMinY = (int) (rect.getMinY() / tileSize);
        int mapMaxX = (int) (rect.getMaxX() / tileSize);
        int mapMaxY = (int) (rect.getMaxY() / tileSize);
        for (int y = mapMinY; y <= mapMaxY; y++) {
            for (int x = mapMinX; x <= mapMaxX; x++) {
                if (x >= width || y >= height)
                    continue;
                if (collision[y][x] != 0 ) {
                    int worldX = x * tileSize;
                    int worldY = y * tileSize;
                    int hitY = (int) (worldY + (1 - COLLISION_RATE) * tileSize);
                    collisionArea.setLocation(worldX, hitY);
                    if (collisionArea.intersects(rect))
                        return true;
                }
            }
        }

        return false;
    }

    private boolean isHitEvent(Rectangle rect) {
        for (Event event : events) {
            // ignore if the same object.
            if (rect == event.getCollisionArea())
                continue;
            if (rect.intersects(event.getCollisionArea()))
                return true;
        }
        return false;
    }

    public void checkEvent(Rectangle rect, Direction direction) {
        Event event = existsEvent(rect);
        if (event == null)
            return;

        if (event instanceof Characters) {
            switch (direction) {
            case UP:
                ((Characters) event).setDirection(Direction.DOWN);
                break;
            case DOWN:
                ((Characters) event).setDirection(Direction.UP);
                break;
            case LEFT:
                ((Characters) event).setDirection(Direction.RIGHT);
                break;
            case RIGHT:
                ((Characters) event).setDirection(Direction.LEFT);
                break;
            default:
                break;
            }
        }
        event.exec();
    }

    /**
     * Check whether there is an event.
     * If not exist, null is returned.
     *
     * @param rect
     * @return
     */
    private Event existsEvent(Rectangle rect) {
        for (Event event : events) {
            // ignore if the same object.
            if (rect == event.getCollisionArea())
                continue;
            if (rect.intersects(event.getCollisionArea()))
                return event;
        }
        return null;
    }

    /**
     * Convert event coord to map coord.
     *
     * @param x event coordinate
     * @return map coordinate
     */
    private int convertEventCoordToMap(int x) {
        return (x - tileSize) / tileSize;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        events.remove(this.player);
        events.add(player);
        this.player = player;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public Event getEvent(String eventName) {
        for (Event event : events)
            if (eventName.equals(event.getName()))
                return event;
        return null;
    }

    public List<Event> getEvents() {
        return events;
    }

    Map setWidth(int width) {
        this.width = width;
        return this;
    }

    Map setHeight(int height) {
        this.height = height;
        return this;
    }

    Map setTileSet(Image tileSet) {
        this.tileSet = tileSet;
        return this;
    }

    Map setTileSetColumn(int tileSetColumn) {
        this.tileSetColumn = tileSetColumn;
        return this;
    }

    Map setBackground(int[][] background) {
        this.background = background;
        return this;
    }

    Map setMiddleground(int[][] middleground) {
        this.middleground = middleground;
        return this;
    }

    Map setForeground(int[][] foreground) {
        this.foreground = foreground;
        return this;
    }

    Map setCollision(int[][] collision) {
        this.collision = collision;
        return this;
    }

}
