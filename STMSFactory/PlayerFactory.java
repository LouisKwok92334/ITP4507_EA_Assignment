package STMSFactory;

import STMS.*;

public class PlayerFactory {
    public Player createPlayer(String id, String name, int position) {
        Player player = new Player(id, name);
        player.setName(name);
        player.setPosition(position);
        return player;
    }
}