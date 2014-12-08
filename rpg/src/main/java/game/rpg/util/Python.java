package game.rpg.util;

import org.python.util.PythonInterpreter;

public class Python {

    private static final PythonInterpreter python = new PythonInterpreter();

    public static void execfile(String filename) {
        if (filename == null)
            throw new NullPointerException("filename must not be null");
        if (filename.isEmpty())
            throw new IllegalArgumentException("filename must not be empty");

        python.execfile(filename);
    }

}
