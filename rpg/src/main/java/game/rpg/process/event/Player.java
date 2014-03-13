package game.rpg.process.event;

import game.rpg.process.attribute.Direction;
import game.rpg.process.map.Map;

public class Player extends Characters {

    public Player(String chipFileName) {
        super(chipFileName);
    }

    public void idle() {
        animation = 0;
    }

    public void check(Map map) {
        int dx, dy;
        int h = (int) collisionArea.getHeight();

        switch (direction) {
        case UP:
            dy = y - SPEED;
            collisionArea.setLocation(x, dy - h);
            map.checkEvent(collisionArea, direction);
            break;
        case DOWN:
            dy = y + SPEED;
            collisionArea.setLocation(x, dy - h);
            map.checkEvent(collisionArea, direction);
            break;
        case LEFT:
            dx = x - SPEED;
            collisionArea.setLocation(dx, y - h);
            map.checkEvent(collisionArea, direction);
            break;
        case RIGHT:
            dx = x + SPEED;
            collisionArea.setLocation(dx, y - h);
            map.checkEvent(collisionArea, direction);
            break;
        default:
            break;
        }

        collisionArea.setLocation(x, y - h);
    }

    @Override
    protected void go(Map map, Direction direction) {
        super.go(map, direction);

        int dx, dy;
        int h = (int) collisionArea.getHeight();

        switch (direction) {
        case UPPER_LEFT:
            dx = x - SPEED;
            dy = y - SPEED;
            collisionArea.setLocation(dx, dy - h);
            if (!map.isHit(collisionArea)) {
                x = dx;
                y = dy;
            }
            break;
        case UPPER_RIGHT:
            dx = x + SPEED;
            dy = y - SPEED;
            collisionArea.setLocation(dx, dy - h);
            if (!map.isHit(collisionArea)) {
                x = dx;
                y = dy;
            }
            break;
        case LOWER_LEFT:
            dx = x - SPEED;
            dy = y + SPEED;
            collisionArea.setLocation(dx, dy - h);
            if (!map.isHit(collisionArea)) {
                x = dx;
                y = dy;
            }
            break;
        case LOWER_RIGHT:
            dx = x + SPEED;
            dy = y + SPEED;
            collisionArea.setLocation(dx, dy - h);
            if (!map.isHit(collisionArea)) {
                x = dx;
                y = dy;
            }
            break;
        default:
            break;
        }

        collisionArea.setLocation(x, y - h);
    }

}
