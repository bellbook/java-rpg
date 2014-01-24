package game.core;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

public interface GameState {

    void processInput();

    void update();

    void render(Graphics g, ImageObserver o);

}
