package game.rpg.state;

import game.core.GameState;
import game.core.input.Controller;
import game.core.input.Controller.Key;
import game.rpg.RPG;
import game.rpg.config.ConfigConst;
import game.rpg.process.window.MessageWindow;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class TalkState implements GameState {

    private static final TalkState instance = new TalkState();

    private final MessageWindow messageWindow;

    private TalkState() {
        messageWindow = new MessageWindow();
        messageWindow.setCurosr(ConfigConst.CURSOR_FILE);
    }

    public static TalkState getInstance() {
        return instance;
    }

    @Override
    public void processInput(Controller c) {
        if (c == null)
            return;

        if (c.isTyped(Key.OK)) {
            boolean hasNextPage = messageWindow.nextPage();
            if (!hasNextPage)
                RPG.getInstance().setState(MoveState.getInstance());
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g, ImageObserver o) {
        MoveState.getInstance().render(g, o);
        messageWindow.draw(g);
    }

    public void showMessage(String text) {
        messageWindow.show(text);
    }

    public void hideMessage() {
        messageWindow.hide();
    }

    public void setMessageFont(Font font) {
        messageWindow.setFont(font);
    }

}
