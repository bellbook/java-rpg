# -*- coding: utf-8 -*-

from java.lang import *
from util import *
from game.rpg.process.attribute import *
from game.rpg.process.event import *
from game.rpg.process.map import *


if __name__ == '__main__':
    # map
    map_file = Loader.getResourceAsString("map/map001/map001.tmx")
    map = MapBuilder.getInstance().parse(map_file)

    # event
    chip_file = Loader.getResourceAsString("event/trash1.png")
    event_file = Loader.getResourceAsString("map001/event001.py")
    event = Event()
    event.setName("trash")
    event.setGraphic(chip_file)
    event.setScript(event_file)
    event.setLocation(16, 120)
    map.addEvent(event)

    # character001
    chip_file = Loader.getResourceAsString("characters/doseisan.png")
    event_file = Loader.getResourceAsString("map001/event002.py")
    characters = Characters(chip_file)
    characters.setScript(event_file)
    characters.setLocation(152, 200)
    characters.setAction(Action.MOVE_RANDOMLY)
    map.addEvent(characters)

    # character002
    chip_file = Loader.getResourceAsString("characters/police_man.png")
    event_file = Loader.getResourceAsString("map001/event003.py")
    characters = Characters(chip_file).setLocation(224, 92)
    characters.setScript(event_file)
    map.addEvent(characters)

    # character003
    chip_file = Loader.getResourceAsString("characters/man001.png")
    event_file = Loader.getResourceAsString("map001/event004.py")
    characters = Characters(chip_file).setLocation(176, 80)
    characters.setScript(event_file)
    characters.setDirection(Direction.LEFT)
    map.addEvent(characters)

    # character004
    chip_file = Loader.getResourceAsString("characters/man002.png")
    event_file = Loader.getResourceAsString("map001/event005.py")
    characters = Characters(chip_file).setLocation(64, 96)
    characters.setName("man002")
    characters.setScript(event_file)
    characters.setDirection(Direction.RIGHT)
    characters.setAction(Action.WAIT)
    map.addEvent(characters)

    # character005
    chip_file = Loader.getResourceAsString("characters/venus.png")
    event_file = Loader.getResourceAsString("map001/event006.py")
    characters = Characters(chip_file).setLocation(256, 112)
    characters.setName("venus")
    characters.setScript(event_file)
    characters.setAction(Action.WAIT)
    map.addEvent(characters)

    # character006
    chip_file = Loader.getResourceAsString("characters/old_woman.png")
    event_file = Loader.getResourceAsString("map001/event007.py")
    characters = Characters(chip_file).setLocation(220, 148)
    characters.setScript(event_file)
    characters.setAction(Action.MOVE_RANDOMLY)
    map.addEvent(characters)

    # character007
    chip_file = Loader.getResourceAsString("characters/boy.png")
    event_file = Loader.getResourceAsString("map001/event008.py")
    characters = Characters(chip_file).setLocation(76, 136)
    characters.setScript(event_file)
    characters.setAction(Action.WAIT)
    map.addEvent(characters)

    # character008
    chip_file = Loader.getResourceAsString("characters/punk.png")
    event_file = Loader.getResourceAsString("map001/event009.py")
    characters = Characters(chip_file).setLocation(40, 220)
    characters.setScript(event_file)
    characters.setDirection(Direction.UP)
    characters.setAction(Action.WAIT)
    map.addEvent(characters)

    # map
    EventCommand.setMap(map)
