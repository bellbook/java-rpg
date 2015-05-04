package game.rpg.config;

import game.rpg.util.Loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Config {

    public enum Key {
        // Key
        KEY_UP     ("38"),
        KEY_DOWN   ("40"),
        KEY_LEFT   ("37"),
        KEY_RIGHT  ("39"),
        KEY_OK     ("90"),
        KEY_CANCEL ("91"),

        // Graphic
        CHIP_SIZE ("16"),

        // GUI
        GUI_WIDTH  ("320"),
        GUI_HEIGHT ("240"),

        // File
        CURSOR_FILE ("window/cursor_down.gif"),

        // Debug
        DEBUG ("false"),
        ;

        private final String name;
        private final String defaultValue;

        private Key(String defaultValue) {
            name = name().toLowerCase().replaceAll("_", ".");
            this.defaultValue = defaultValue;
        }

        @Override
        public String toString() {
            return name;
        }

        public String getDefaultValue() {
            return defaultValue;
        }
    }

    private static final Log log = LogFactory.getLog(Config.class);
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties props = new Properties();

    static {
        try {
            load(CONFIG_FILE);
        } catch (IOException e) {
            log.warn("", e);
        }
    }

    private Config() {
    }

    public static String get(Key key) {
        return props.getProperty(key.toString(), key.getDefaultValue());
    }

    private static void load(String filename) throws IOException {
        File file = Loader.getResourceAsFile(filename);
        InputStream in = new FileInputStream(file);
        props.load(in);
    }

}
