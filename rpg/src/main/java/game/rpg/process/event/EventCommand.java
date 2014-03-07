package game.rpg.process.event;

import java.awt.Font;

import game.core.GameState;
import game.rpg.RPG;
import game.rpg.process.map.Map;
import game.rpg.state.MoveState;
import game.rpg.state.TalkState;

public class EventCommand {

    public static void showText(String text) {
        TalkState state = TalkState.getInstance();
        RPG.getInstance().setState(state);
        state.showMessage(text);
    }

    public static void showChoice() {
    }

    public static void transfer(Map map, int x, int y) {
    }

    public static void makeSoundEffect() {
    }

    public static void setState(GameState state) {
        RPG.getInstance().setState(state);
    }

    public static Map getMap() {
        return MoveState.getInstance().getMap();
    }

    public static void setMap(Map map) {
        MoveState.getInstance().setMap(map);
    }

    public static void setPlayer(Player player) {
        MoveState.getInstance().setPlayer(player);
    }

    public static void setMessageFont(Font font) {
        TalkState.getInstance().setMessageFont(font);
    }

}
