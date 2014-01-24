# -*- coding: utf-8 -*-

from java.awt import Font
from util import *
from game.rpg.process.event import *


if __name__ == '__main__':
    # font
    font_file = Loader.getResourceAsFile("font/mother2.TTF")
    font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(float(13.0))
    EventCommand.setMessageFont(font)

    # text
    EventCommand.showText(u"へんじがない\fただのシカトのようだ")

    # event
    map = EventCommand.getMap()
    event = map.getEvent("venus")
    if event != None:
        if isinstance(event, Characters):
            direction = event.getDirection()
            if direction == Direction.UP:
                event.setDirection(Direction.DOWN)
            if direction == Direction.DOWN:
                event.setDirection(Direction.UP)
            if direction == Direction.LEFT:
                event.setDirection(Direction.RIGHT)
            if direction == Direction.RIGHT:
                event.setDirection(Direction.LEFT)
