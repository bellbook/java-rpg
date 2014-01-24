package game.core.output;

import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ZoomMenu extends Menu {

    private final GameFrame frame;
    private final DoubleBufferdPanel screen;
    private final Dimension screenSize;

    public ZoomMenu(GameFrame frame) {
        super("Zoom");

        this.frame = frame;
        this.screen = frame.getScreen();
        this.screenSize = screen.getPreferredSize();

        MenuEventHandler menuEventHandler = new MenuEventHandler();
        MenuItem menuItem1 = new MenuItem("100%");
        menuItem1.addActionListener(menuEventHandler);
        this.add(menuItem1);
        MenuItem menuItem2 = new MenuItem("200%");
        menuItem2.addActionListener(menuEventHandler);
        this.add(menuItem2);
    }

    private class MenuEventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("100%")) {
                screen.setPreferredSize(screenSize);
                frame.pack();
            } else if (ae.getActionCommand().equals("200%")) {
                Dimension d = new Dimension(screenSize.width * 2, screenSize.height * 2);
                screen.setPreferredSize(d);
                frame.pack();
            } else {
            }
        }

    }

}
