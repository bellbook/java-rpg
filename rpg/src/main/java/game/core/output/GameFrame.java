package game.core.output;

import java.awt.Frame;
import java.awt.MenuBar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class GameFrame extends Frame {

    private GameScreen screen;

    public GameFrame(GameScreen screen) {
        // configure screen
        this.screen = screen;
        this.add(screen);

        // configure menubar
        MenuBar menuBar = new MenuBar();
        menuBar.add(new ZoomMenu(this));
        this.setMenuBar(menuBar);

        // configure frame
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public GameScreen getScreen() {
        return screen;
    }

}
