package game.rpg.process.event;

import game.rpg.process.attribute.Action;
import game.rpg.process.attribute.Direction;
import game.rpg.process.map.Map;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.swing.ImageIcon;

public class Characters extends Event {

    // Constant
    protected static final int SPEED = 4;
    private static final int ANIMATION_PATTERN = 2;
    private static final int FRAME_RATE = 8;
    private static final int FRAME_TIME = 1000 / FRAME_RATE; // [ms]
    private static final float COLLISION_RATE = 0.5f; // chip の 下から 1/2 の領域を当たり判定にする

    // Animation
    protected int animation = 0;
    private long waitingTime, pastTime;

    // Parameter
    protected Direction direction = Direction.DOWN;
    protected Action action = Action.STAND;

    public Characters(String chipFileName) {
        this.graphic = new ImageIcon(chipFileName).getImage();
        this.width = 1;
        this.height = 2;
        this.setBounds((int) (CHIP_SIZE * COLLISION_RATE));
    }

    public void move(Map map, Direction direction) {
        if (canAct(FRAME_TIME)) {
            switchAnimation();
            go(map, direction);
        }
    }

    public void moveRandomly(Map map) {
        move(map, Direction.toDirection(new Random().nextInt(4)));
    }

    protected void go(Map map, Direction direction) {
        this.direction = direction;

        int dx, dy;
        int h = (int) collisionArea.getHeight();

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

    public void step(int time) {
        if (canAct(time))
            switchAnimation();
    }

    private boolean canAct(int time) {
        long currentTime = System.currentTimeMillis();
        waitingTime += currentTime - pastTime;
        pastTime = currentTime;
        if (waitingTime < time) {
            return false;
        } else {
            waitingTime = 0;
            return true;
        }
    }

    private void switchAnimation() {
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
