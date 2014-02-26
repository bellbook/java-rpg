package game.rpg.state;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Random;

import game.core.GameState;
import game.core.input.Controller;
import game.core.input.Controller.Key;
import game.rpg.process.attribute.Direction;
import game.rpg.process.event.Characters;
import game.rpg.process.event.Event;
import game.rpg.process.event.Player;
import game.rpg.process.map.Map;

public class MoveState implements GameState {

    private static final float MOVEMENT_PROBABILITY = 0.02f;
    private static final float FRAME_TIME = 0.5f;

    private static final MoveState instance = new MoveState();

    private boolean debug;

    private Controller controller;
    private Player player;
    private Map map;

    private MoveState() {
    }

    public static MoveState getInstance() {
        return instance;
    }

    @Override
    public void processInput() {
        if (controller == null || player == null)
            return;

        if (controller.isPressing()) {
            if (controller.isTyped(Key.OK))
                player.check(map);
            else
                move(player, map);
        } else {
            player.idle();
        }
    }

    @Override
    public void update() {
        if (map == null)
            return;

        // キャラクターを自動的に動かす
        for (Event event : map.getEvents()) {
            if (event instanceof Characters) {
                Characters character = (Characters) event;
                switch (character.getAction()) {
                case STAND:
                    break;
                case WAIT:
                    character.marchInPlace(map, FRAME_TIME);
                    break;
                case MOVE_RANDOMLY:
                    if (new Random().nextFloat() < MOVEMENT_PROBABILITY)
                        character.moveRandomly(map);
                    break;
                default:
                    break;
                }
            }
        }

    }

    @Override
    public void render(Graphics g, ImageObserver o) {
        if (map == null)
            return;

        map.draw(g, o);
        if (debug)
            drawDebugInfo(g);
    }

    private void move(Player player, Map map) {
        if (controller.isPressing(Key.UP)
                && controller.isPressing(Key.LEFT))
            player.move(map, Direction.UPPER_LEFT);
        else if (controller.isPressing(Key.UP)
                && controller.isPressing(Key.RIGHT))
            player.move(map, Direction.UPPER_RIGHT);
        else if (controller.isPressing(Key.DOWN)
                && controller.isPressing(Key.LEFT))
            player.move(map, Direction.LOWER_LEFT);
        else if (controller.isPressing(Key.DOWN)
                && controller.isPressing(Key.RIGHT))
            player.move(map, Direction.LOWER_RIGHT);
        else if (controller.isPressing(Key.UP))
            player.move(map, Direction.UP);
        else if (controller.isPressing(Key.DOWN))
            player.move(map, Direction.DOWN);
        else if (controller.isPressing(Key.LEFT))
            player.move(map, Direction.LEFT);
        else if (controller.isPressing(Key.RIGHT))
            player.move(map, Direction.RIGHT);
        else
            player.idle();
    }

    private void drawDebugInfo(Graphics g) {
        Player player = map.getPlayer();
        if (player != null) {
            String info = " PLAYER (" + player.getX() + ", " + player.getY() + ")";
            g.drawString(info, 0, 16);
        }
    }

    public Controller getController() {
        return controller;
    }

    public MoveState setController(Controller controller) {
        this.controller = controller;
        return this;
    }

    public Player getPlayer() {
        return player;
    }

    public MoveState setPlayer(Player player) {
        this.player = player;
        if (map != null)
            map.setPlayer(player);
        return this;
    }

    public Map getMap() {
        return map;
    }

    public MoveState setMap(Map map) {
        this.map = map;
        map.setPlayer(player);
        return this;
    }

    public MoveState setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

}
