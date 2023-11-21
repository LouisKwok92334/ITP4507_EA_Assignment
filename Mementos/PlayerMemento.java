package Mementos;

import STMS.*;

public class PlayerMemento extends Memento {
    private final Player player;
    private final int position;

    public PlayerMemento(Player player) {
        this.player = player;
        this.position = player.getPosition();
    }

    @Override
    public void restore() {
        player.setPosition(position);
    }
}