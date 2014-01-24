package game.rpg.process.attribute;

public enum Direction {

    DOWN  (0),
    UP    (1),
    LEFT  (2),
    RIGHT (3),

    LOWER_LEFT  (4),
    LOWER_RIGHT (5),
    UPPER_LEFT  (6),
    UPPER_RIGHT (7),
    ;

    private static final Direction[] directions = Direction.values();

    private int id;

    private Direction(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Direction toDirection(int id) {
        for (Direction direction : directions)
            if (id == direction.id)
                return direction;
        return null;
    }

}
