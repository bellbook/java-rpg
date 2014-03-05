package game.core;

import game.core.input.Controller;
import game.core.output.GameScreen;

import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private static final long DELAY  = 0;
    private static final long PERIOD = 50;

    private final Timer timer;
    private final GameLoop loop;

    private GameState state = new NullState();
    private final Controller controller;
    private final GameScreen screen;

    public Game(Controller controller, GameScreen screen) {
        this.controller = controller;
        this.screen = screen;
        this.screen.addKeyListener(controller);
        timer = new Timer();
        loop = new GameLoop();
    }

    public void start() {
        timer.schedule(loop, DELAY, PERIOD);
    }

    public void stop() {
        timer.cancel();
    }

    public Game setState(GameState state) {
        this.state = state;
        screen.setState(state);
        return this;
    }

    private class GameLoop extends TimerTask {

        @Override
        public void run() {
            state.processInput(controller);
            state.update();
            screen.repaint();
        }

    }

}
