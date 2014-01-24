package game.core.output;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
abstract class DoubleBufferdPanel extends Panel {

    private final Dimension initSize;
    private final AffineTransform affineTransform;

    // For double-buffering
    private Image    iBuffer;
    private Graphics gBuffer;

    public DoubleBufferdPanel(int width, int height) {
        initSize = new Dimension(width, height);
        super.setPreferredSize(initSize);

        affineTransform = AffineTransform.getScaleInstance(1, 1);

        iBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        gBuffer = iBuffer.getGraphics();
    }

    public abstract void draw(Graphics g);

    @Override
    public void paint(Graphics g) {
        this.clearBuffer();
        this.draw(gBuffer);

        Graphics2D g2 = (Graphics2D) g;
        g2.setTransform(affineTransform);
        g2.drawImage(iBuffer, 0, 0, this);
    }

    @Override
    public void update(Graphics g) {
        this.paint(g);
    }

    private void clearBuffer() {
        gBuffer.setColor(this.getBackground());
        gBuffer.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    @Override
    public void setPreferredSize(Dimension d) {
        super.setPreferredSize(d);

        double scaleX = (double) d.width  / initSize.width;
        double scaleY = (double) d.height / initSize.height;

        affineTransform.setToScale(scaleX, scaleY);

        iBuffer = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
        gBuffer = iBuffer.getGraphics();
    }

}
