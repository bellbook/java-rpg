package game.core;

import game.core.input.Controller;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

public interface GameState {

    void processInput(Controller c);

    void update();

    void render(Graphics g, ImageObserver o);

}
