# -*- coding: utf-8 -*-

from java.awt import Font
from game.rpg.process.event import *
from game.rpg.util import *


if __name__ == '__main__':
    # font
    font_file = Loader.getResourceAsFile("font/mother2.TTF")
    font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(float(13.0))
    EventCommand.setMessageFont(font)

    # text
    EventCommand.showText(u"いったい　いつになったら\nマザー3　は\nはつばい　されるんじゃ！\fえ?\nもうされたの？\f")
