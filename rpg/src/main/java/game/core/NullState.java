package game.core;

import game.core.input.Controller;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class NullState implements GameState {

    @Override
    public void processInput(Controller c) {
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g, ImageObserver observer) {
    }

}
