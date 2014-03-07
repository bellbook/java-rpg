package game.rpg;

import game.core.Game;
import game.core.GameState;
import game.core.input.Controller;
import game.core.output.GameFrame;
import game.core.output.GameScreen;
import game.rpg.config.ConfigConst;
import game.rpg.state.MoveState;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;

import util.Loader;
import util.Python;

public class RPG {

    private static final Logger log = Logger.getLogger(RPG.class);
    private static final String SCRIPT_FILE_NAME = "init/init.py";
    private static final RPG instance = new RPG();

    private final Game game;
    private final GameFrame frame;

    private RPG() {
        // validate
        try {
            ConfigConst.validate();
        } catch (Throwable e) {
            log.debug("validate error", e);
            throw new RuntimeException(e);
        }

        // configure process
        String scriptFileName;
        try {
            scriptFileName = Loader.getResourceAsString(SCRIPT_FILE_NAME);
        } catch (FileNotFoundException e) {
            log.debug(SCRIPT_FILE_NAME, e);
            throw new RuntimeException(e);
        }
        try {
            Python.execfile(scriptFileName);
        } catch (Exception e) {
            log.error("Failed to execute the file by Jython: " + scriptFileName, e);
            System.exit(-1);
        }

        // configure input
        Controller controller = new Controller()
                .setKeyUp    (ConfigConst.KEY_UP)
                .setKeyDown  (ConfigConst.KEY_DOWN)
                .setKeyLeft  (ConfigConst.KEY_LEFT)
                .setKeyRight (ConfigConst.KEY_RIGHT)
                .setKeyOK    (ConfigConst.KEY_OK)
                .setKeyDown  (ConfigConst.KEY_DOWN);

        // configure output
        GameScreen screen = new GameScreen(ConfigConst.GUI_WIDTH, ConfigConst.GUI_HEIGHT);
        frame = new GameFrame(screen);

        // configure state
        // - move state
        MoveState state = MoveState.getInstance()
                .setDebug(ConfigConst.DEBUG);

        game = new Game(controller, screen)
                .setState(state);
    }

    public static RPG getInstance() {
        return instance;
    }

    public void start() {
        game.start();
    }

    public void stop() {
        game.stop();
    }

    public void setState(GameState state) {
        game.setState(state);
    }

    public void show() {
        frame.setVisible(true);
    }

}
