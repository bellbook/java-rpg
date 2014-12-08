# -*- coding: utf-8 -*-

from java.awt import Font
from game.rpg.process.event import *
from game.rpg.process.map import *
from game.rpg.util import *


if __name__ == '__main__':
    map = EventCommand.getMap()
    event = map.getEvent("trash")

    if event != None:
        chip_file = Loader.getResourceAsString("event/trash2.png")
        event.setGraphic(chip_file)

        # font
        font_file = Loader.getResourceAsFile("font/mother2.TTF")
        font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(float(13.0))
        EventCommand.setMessageFont(font)

        EventCommand.showText(u"ごみばこをしらべた。\fジャン!!\nハンバーガーが\nあった。\fハンバーガーを\nてにいれた。")
