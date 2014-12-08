# -*- coding: utf-8 -*-

from java.lang import *
from java.awt import Font
from java.io import File
from java.util import Calendar
from game.rpg.process.event import *
from game.rpg.util import *


if __name__ == '__main__':
    # font
    font_file = Loader.getResourceAsFile("font/doseisan.TTF")
    font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(float(13.0))
    EventCommand.setMessageFont(font)

    # time
    calendar = Calendar.getInstance()
    hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY))
    minute = String.valueOf(calendar.get(Calendar.MINUTE))

    # text
    EventCommand.showText(u"いまは\n" + hour + u"　じ　" + minute + u"　ふん\nだ　よ")
