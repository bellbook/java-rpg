package game.rpg.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.Loader;

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

        private String defaultValue;

        private Key(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

    }

    private static final Log log = LogFactory.getLog(Config.class);
    private static final String FILE_NAME = "config.properties";
    private static final Properties prop = new Properties();

    static {
        try {
            loadProperties(FILE_NAME);
        } catch (IOException e) {
            log.debug("", e);
        }
    }

    private Config() {
    }

    public static String get(Key key) {
        return prop.getProperty(key.name(), key.getDefaultValue());
    }

    private static void loadProperties(String filename) throws IOException {
        File file = Loader.getResourceAsFile(filename);
        InputStream in = new FileInputStream(file);
        prop.load(in);
    }

}
