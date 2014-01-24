# -*- coding: utf-8 -*-

from java.awt import Font
from util import *
from game.rpg.process.attribute import *
from game.rpg.process.event import *


if __name__ == '__main__':
    # font
    font_file = Loader.getResourceAsFile("font/mother2.TTF")
    font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(float(13.0))
    EventCommand.setMessageFont(font)

    # text
    EventCommand.showText(u"じてんしゃの　かぎ　を\nおとしたんだ\fどこにいったんだろう")

    # event
    map = EventCommand.getMap()
    event = map.getEvent("man002")

    if event != None:
        if isinstance(event, Characters):
            event.setDirection(Direction.RIGHT)
