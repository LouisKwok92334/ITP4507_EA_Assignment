package Commands;

import Mementos.*;
import STMS.*;
import java.util.*;

public class DeletePlayerCommand implements Command {
    private final Scanner sc;
    private final Vector<Team> currentTeam;
    private Player player;
    private final Caretaker caretaker;

    public DeletePlayerCommand(Scanner sc, Vector<Team> currentTeam, Caretaker caretaker) {
        this.sc = sc;
        this.currentTeam = currentTeam;
        this.caretaker = caretaker;
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
        caretaker.saveTeam(currentTeam, currentTeam.get(0).getTeamID(), currentTeam.get(0).getName(),
                currentTeam.get(0).getAllPlayers());
        currentTeam.get(0).remove(player);
        System.out.println("Player is deleted.");
    }

    @Override
    public void undo() {
        if (caretaker.getUndoListSize() > 0) {
            caretaker.saveTeam_redo(currentTeam, currentTeam.get(0).getTeamID(), currentTeam.get(0).getName(),
                    currentTeam.get(0).getAllPlayers());
            caretaker.undo();
        }
    }

    @Override
    public void redo() {
        if (caretaker.getRedoListSize() > 0) {
            caretaker.saveTeam(currentTeam, currentTeam.get(0).getTeamID(), currentTeam.get(0).getName(),
                    currentTeam.get(0).getAllPlayers());
            caretaker.redo();
        }
    }

    public String toString() {
        return "Delete player, " + player.getPlayerID();
    }
}
