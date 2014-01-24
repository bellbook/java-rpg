package game.rpg.process.event;

import game.rpg.process.attribute.Action;
import game.rpg.process.attribute.Direction;
import game.rpg.process.map.Map;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.swing.ImageIcon;

public class Characters extends Event {

    // constant
    protected static final int SPEED = 4;

    private static final int   ANIMATION_PATTERN = 2;
    private static final float FRAME_RATE = 8.0f;
    private static final float FRAME_TIME = 1 / FRAME_RATE;
    private static final float COLLISION_RATE = 0.5f; // chip の 下から 1/2 の領域を当たり判定にする

    // animation
    protected int animation = 0;
    protected float waitTime;
    protected long currentTime, pastTime;

    // parameter
    protected Direction direction = Direction.DOWN;
    protected Action action = Action.STAND;;

    public Characters(String chipFileName) {
        this.graphic = new ImageIcon(chipFileName).getImage();
        this.width = 1;
        this.height = 2;
        this.setBounds((int) (CHIP_SIZE * COLLISION_RATE));
    }

    public void move(Map map, Direction direction) {
        // control animation for frame-rate
        currentTime = System.currentTimeMillis();
        float time = (currentTime - pastTime) * 0.001f;
        pastTime = currentTime;

        waitTime += time;
        if (waitTime < FRAME_TIME)
            return;
        waitTime = 0.0f;

        // go
        go(map, direction);

        // decide motion
        animation = (animation + 1) % ANIMATION_PATTERN;
    }

    public void go(Map map, Direction direction) {
        this.direction = direction;

        int dx, dy;
        final int h = (int) collisionArea.getHeight();

        switch (direction) {
        case UP:
            dy = y - SPEED;
            collisionArea.setLocation(x, dy - h);
            if (!map.isHit(collisionArea))
                y = dy;
            break;

        case DOWN:
            dy = y + SPEED;
            collisionArea.setLocation(x, dy - h);
            if (!map.isHit(collisionArea))
                y = dy;
            break;

        case LEFT:
            dx = x - SPEED;
            collisionArea.setLocation(dx, y - h);
            if (!map.isHit(collisionArea))
                x = dx;
            break;

        case RIGHT:
            dx = x + SPEED;
            collisionArea.setLocation(dx, y - h);
            if (!map.isHit(collisionArea))
                x = dx;
            break;

        default:
            break;
        }

        collisionArea.setLocation(x, y - h);
    }

    public void moveRandomly(Map map) {
        move(map, Direction.toDirection(new Random().nextInt(4)));
    }

    public void marchInPlace(Map map, float frameTime) {
        // control animation for frame-rate
        currentTime = System.currentTimeMillis();
        float time = (currentTime - pastTime) * 0.001f;
        pastTime = currentTime;

        waitTime += time;
        if (waitTime < frameTime)
            return;
        waitTime = 0.0f;

        // decide motion
        animation = (animation + 1) % ANIMATION_PATTERN;
    }

    @Override
    public void draw(Graphics g, ImageObserver o) {
        int sx1 = CHIP_SIZE * width  * animation;
        int sy1 = CHIP_SIZE * height * direction.getId();

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

    public Direction getDirection() {
        return direction;
    }

    public Characters setDirection(Direction direction) {
        this.direction = direction;
        return this;
    }

    public Action getAction() {
        return action;
    }

    public Characters setAction(Action action) {
        this.action = action;
        return this;
    }

}
