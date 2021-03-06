package game.core.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EnumSet;

public class Controller implements KeyListener {

    public enum Key {
        NONE, // not pressed

        UP,
        DOWN,
        LEFT,
        RIGHT,

        OK,
        CANCEL,
    }

    private final EnumSet<Key> pressingKey = EnumSet.noneOf(Key.class);

    private int keyUp     = 38; // ↑
    private int keyDown   = 40; // ↓
    private int keyLeft   = 37; // ←
    private int keyRight  = 39; // →
    private int keyOK     = 65; // A
    private int keyCancel = 66; // B

    public boolean isPressing() {
        return !pressingKey.isEmpty();
    }

    public boolean isPressing(Key key) {
        return pressingKey.contains(key);
    }

    public boolean isPressing(Key key1, Key key2) {
        return isPressing(key1) && isPressing(key2);
    }

    public boolean clear(Key key) {
        return pressingKey.remove(key);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == keyUp)
            pressingKey.add(Key.UP);
        else if (keyCode == keyDown)
            pressingKey.add(Key.DOWN);
        else if (keyCode == keyLeft)
            pressingKey.add(Key.LEFT);
        else if (keyCode == keyRight)
            pressingKey.add(Key.RIGHT);
        else if (keyCode == keyOK)
            pressingKey.add(Key.OK);
        else if (keyCode == keyCancel)
            pressingKey.add(Key.CANCEL);
        else
            ;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == keyUp)
            pressingKey.remove(Key.UP);
        else if (keyCode == keyDown)
            pressingKey.remove(Key.DOWN);
        else if (keyCode == keyLeft)
            pressingKey.remove(Key.LEFT);
        else if (keyCode == keyRight)
            pressingKey.remove(Key.RIGHT);
        else if (keyCode == keyOK)
            pressingKey.remove(Key.OK);
        else if (keyCode == keyCancel)
            pressingKey.remove(Key.CANCEL);
        else
            ;
    }

    public Controller setKeyUp(int keyUp) {
        this.keyUp = keyUp;
        return this;
    }

    public Controller setKeyDown(int keyDown) {
        this.keyDown = keyDown;
        return this;
    }

    public Controller setKeyLeft(int keyLeft) {
        this.keyLeft = keyLeft;
        return this;
    }

    public Controller setKeyRight(int keyRight) {
        this.keyRight = keyRight;
        return this;
    }

    public Controller setKeyOK(int keyOK) {
        this.keyOK = keyOK;
        return this;
    }

    public Controller setKeyCancel(int keyCancel) {
        this.keyCancel = keyCancel;
        return this;
    }

}
