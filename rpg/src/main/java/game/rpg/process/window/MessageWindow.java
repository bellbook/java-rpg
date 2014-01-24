package game.rpg.process.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import org.apache.log4j.helpers.Loader;

public class MessageWindow extends SystemWindow {

    private static final int LINE_SPACE = 16;

    private static final int MAX_CHARS_PER_LINE = 15;
    private static final int MAX_LINES_PER_PAGE = 3;
    private static final int MAX_CHARS_PER_PAGE = MAX_CHARS_PER_LINE * MAX_LINES_PER_PAGE;

    private static final long DELAY  = 0;
    private static final long PERIOD = 50;

    private boolean isVisible;

    private char[] text;
    private int position; // the position of total characters
    private int currentPage;
    private boolean isDrawingMessage;

    private final int x, y; // top left point

    private DrawMessageTask task;

    private Image cursor;
    private Font font;

    public MessageWindow() {
        super(160, 10, 150, 65);
        x = innerX + 13;
        y = innerY + 18;
    }

    public void show(String msg) {
        text = parse(msg);
        task = new DrawMessageTask();
        new Timer().schedule(task, DELAY, PERIOD);
        isDrawingMessage = true;
        isVisible = true;
    }

    public void hide() {
        isVisible = false;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setCurosr(String cursorFileName) {
        URL url = Loader.getResource(cursorFileName);
        if (url != null)
            cursor = new ImageIcon(url).getImage();
    }

    @Override
    public void draw(Graphics g) {
        if (!isVisible)
            return;

        super.draw(g);
        g.setColor(Color.WHITE);
        g.setFont(font);

        // show text until position
        int textX = x;
        int textY;
        for (int i = currentPage * MAX_CHARS_PER_PAGE; i <= position; i++) {
            char c = text[i];

            if (getCharPositionInLine(i) == 0)
                textX = x;
            else
                textX += g.getFontMetrics().charWidth(c);
            textY = y + LINE_SPACE * getLinePositionInPage(i);

            g.drawString(String.valueOf(c), textX, textY);
        }

        // show cursor
        if (cursor != null && isEndOfPage(position) && !isLastPage(currentPage) && !isDrawingMessage) {
            int cursorX = innerX + innerWidth - cursor.getWidth(null) - 3;
            int cursorY = innerY + innerHeight - cursor.getHeight(null);
            g.drawImage(cursor, cursorX, cursorY, null);
        }
    }

    public boolean nextPage() {
        if (!hasNextChar(position)) {
            task.cancel();
            task = null;

            position = 0;
            currentPage = 0;

            hide();
            return false;
        }
        if (!isDrawingMessage) {
            currentPage++;
            isDrawingMessage = true;
        }
        return true;
    }

    private class DrawMessageTask extends TimerTask {

        @Override
        public void run() {

            if (isDrawingMessage) {
                position++;
                while (hasNextChar(position) && !isEndOfPage(position)) {
                    if (text[position] == '　')
                        position++;
                    else
                        break;
                }
                if (!hasNextChar(position) || isEndOfPage(position))
                    isDrawingMessage = false;
            }
        }
    }

    private int getLinePosition(int position) {
        return position / MAX_CHARS_PER_LINE;
    }

    private int getPagePosition(int position) {
        return position / MAX_CHARS_PER_PAGE;
    }

    private int getLastPagePosition() {
        return (text.length - 1) / MAX_CHARS_PER_PAGE;
    }

    private int getCharPositionInLine(int position) {
        return position % MAX_CHARS_PER_LINE;
    }

    private int getLinePositionInPage(int position) {
        return getLinePosition(position) - getPagePosition(position) * MAX_LINES_PER_PAGE;
    }

    private boolean hasNextChar(int position) {
        return position < text.length - 1;
    }

    private boolean isEndOfPage(int position) {
        return (position + 1) % MAX_CHARS_PER_PAGE == 0;
    }

    private boolean isLastPage(int page) {
        return page == getLastPagePosition();
    }

    private char[] parse(String str) {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '\n')
                count = getCharPositionOfLine(count) + MAX_CHARS_PER_LINE;
            else if (c == '\f')
                count = getCharPositionOfPage(count) + MAX_CHARS_PER_PAGE;
            else
                count++;
        }

        char[] text = new char[count];

        // initialize
        for (int i = 0; i < text.length; i++)
            text[i] = '　';

        int position = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '\n')
                position = getCharPositionOfLine(position) + MAX_CHARS_PER_LINE;
            else if (c == '\f')
                position = getCharPositionOfPage(position) + MAX_CHARS_PER_PAGE;
            else
                text[position++] = c;
        }

        return text;
    }

    private int getCharPositionOfLine(int position) {
        return position / MAX_CHARS_PER_LINE * MAX_CHARS_PER_LINE;
    }

    private int getCharPositionOfPage(int position) {
        return position / MAX_CHARS_PER_PAGE * MAX_CHARS_PER_PAGE;
    }

}
