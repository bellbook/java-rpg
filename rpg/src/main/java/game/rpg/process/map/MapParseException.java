package game.rpg.process.map;

@SuppressWarnings("serial")
public class MapParseException extends Exception {

    public MapParseException() {
        super();
    }

    public MapParseException(String message) {
        super(message);
    }

    public MapParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapParseException(Throwable cause) {
        super(cause);
    }

}
