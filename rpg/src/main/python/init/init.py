# -*- coding: utf-8 -*-

from util import *
from game.rpg.config import *
from game.rpg.process.event import *


if __name__ == '__main__':
    # player
    chip_file = Loader.getResourceAsString("characters/player.png")
    player = Player(chip_file)
    player.setLocation(152, 88)
    EventCommand.setPlayer(player)

    # script
    script_file = Loader.getResourceAsString("map001/map001.py")
    Python.execfile(script_file)
