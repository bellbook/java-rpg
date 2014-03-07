import game.rpg.RPG;

public class Main {

    public static void main(String[] args) {
        RPG rpg = RPG.getInstance();
        rpg.start();
        rpg.show();
    }

}
