package game.rpg.state;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import game.core.GameState;
import game.core.input.Controller;
import game.core.input.Controller.Key;
import game.rpg.Rpg;
import game.rpg.config.ConfigConst;
import game.rpg.process.window.MessageWindow;

public class TalkState implements GameState {

    private static final TalkState instance = new TalkState();

    private Controller controller;
    private final MessageWindow messageWindow;

    private TalkState() {
        messageWindow = new MessageWindow();
        messageWindow.setCurosr(ConfigConst.CURSOR_FILE);
    }

    public static TalkState getInstance() {
        return instance;
    }

    @Override
    public void processInput() {
        if (controller == null)
            return;

        if (controller.isTyped(Key.OK)) {
            boolean hasNextPage = messageWindow.nextPage();
            if (!hasNextPage)
                Rpg.getInstance().setState(MoveState.getInstance());
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

    public TalkState setController(Controller controller) {
        this.controller = controller;
        return this;
    }

}
