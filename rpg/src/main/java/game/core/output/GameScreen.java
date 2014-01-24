package game.core.output;

import java.awt.Graphics;

import game.core.GameState;
import game.core.NullState;

@SuppressWarnings("serial")
public class GameScreen extends DoubleBufferdPanel {

    private GameState state = new NullState();

    public GameScreen(int width, int height) {
        super(width, height);
    }

    @Override
    public void draw(Graphics g) {
        state.render(g, this);
    }

    public void setState(GameState state) {
        this.state = state;
    }

}
