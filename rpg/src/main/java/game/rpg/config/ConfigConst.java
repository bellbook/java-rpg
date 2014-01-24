package game.rpg.config;

import game.rpg.config.Config.Key;

public class ConfigConst {

    // Key
    public static final int KEY_UP     = Integer.parseInt(Config.get(Key.KEY_UP));
    public static final int KEY_DOWN   = Integer.parseInt(Config.get(Key.KEY_DOWN));
    public static final int KEY_LEFT   = Integer.parseInt(Config.get(Key.KEY_LEFT));
    public static final int KEY_RIGHT  = Integer.parseInt(Config.get(Key.KEY_RIGHT));
    public static final int KEY_OK     = Integer.parseInt(Config.get(Key.KEY_OK));
    public static final int KEY_CANCEL = Integer.parseInt(Config.get(Key.KEY_CANCEL));

    // Graphic
    public static final int CHIP_SIZE  = Integer.parseInt(Config.get(Key.CHIP_SIZE));

    // GUI
    public static final int GUI_WIDTH  = Integer.parseInt(Config.get(Key.GUI_WIDTH));
    public static final int GUI_HEIGHT = Integer.parseInt(Config.get(Key.GUI_HEIGHT));

    // File
    public static final String CURSOR_FILE = Config.get(Key.CURSOR_FILE);

    // Debug
    public static final boolean DEBUG = Boolean.parseBoolean(Config.get(Key.DEBUG));

    public static void validate() {
    }

}
