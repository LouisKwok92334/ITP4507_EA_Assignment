package STMSFactory;

import STMS.*;

public class PlayerFactory {
    private static int idCounter = 1;

    public static Player createPlayer(String name) {
        Player player = new Player(Integer.toString(idCounter++), name);
        return player;
    }
}