package Commands;

import STMS.*;
import java.util.*;

public class DeletePlayerCommand implements Command {
    private final Scanner sc;
    private final Vector<Team> currentTeam;
    private Player player;

    public DeletePlayerCommand(Scanner sc, Vector<Team> currentTeam) {
        this.sc = sc;
        this.currentTeam = currentTeam;
        this.player = null;
    }

    @Override
    public void execute() {
        if (currentTeam.isEmpty()) {
            System.out.println("Please create a team first!");
            return;
        }

        System.out.print("Please input player id:- ");
        String id = sc.next();

        Enumeration<Player> players = currentTeam.get(0).getAllPlayers();
        while (players.hasMoreElements()) {
            Player p = players.nextElement();
            if (p.getPlayerID().equals(id)) {
                player = p;
                break;
            }
        }

        if (player == null) {
            System.out.println("Player not found!");
            return;
        }

        currentTeam.get(0).remove(player);
        System.out.println("Player is deleted.");
    }

    @Override
    public void undo() {
        currentTeam.get(0).addPlayer(player);
    }

    @Override
    public void redo() {
        currentTeam.get(0).remove(player);
    }
}
