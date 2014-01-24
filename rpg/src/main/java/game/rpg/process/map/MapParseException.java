package game.rpg.process.map;

public class MapParseException extends Exception {

    private static final long serialVersionUID = 8014831423501539905L;

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
