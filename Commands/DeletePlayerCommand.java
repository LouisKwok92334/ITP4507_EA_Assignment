package Commands;

import Mementos.*;
import STMS.*;
import java.util.*;

public class DeletePlayerCommand implements Command {
    private final Scanner sc;
    private final Vector<Team> currentTeam; // The current team
    private Player player; // The player to be deleted
    private final Caretaker caretaker; // Manages saving and restoring state (for undo/redo)

    public DeletePlayerCommand(Scanner sc, Vector<Team> currentTeam, Caretaker caretaker) {
        this.sc = sc;
        this.currentTeam = currentTeam;
        this.caretaker = caretaker;
        this.player = null;
    }

    @Override
    public void execute() {
        System.out.print("Please input player id:- ");
        String id = sc.next(); // Read player ID from user input

        // Iterate through players to find the player with given ID
        Enumeration<Player> players = currentTeam.get(0).getAllPlayers();
        while (players.hasMoreElements()) {
            Player p = players.nextElement();
            if (p.getPlayerID().equals(id)) {
                player = p;
                break;
            }
        }

        // If player is not found, print message and exit method
        if (player == null) {
            System.out.println("Player not found!");
            return;
        }

        // Save current team state for undo before deleting player
        caretaker.saveTeam(currentTeam, currentTeam.get(0).getTeamID(), currentTeam.get(0).getName(),
                currentTeam.get(0).getAllPlayers());
        currentTeam.get(0).remove(player); // Remove player from the team
        System.out.println("Player is deleted.");
    }

    @Override
    public void undo() {
        // Check if there are states to revert to
        if (caretaker.getUndoListSize() > 0) {
            // Save current state for potential redo before performing undo
            caretaker.saveTeam_redo(currentTeam, currentTeam.get(0).getTeamID(), currentTeam.get(0).getName(),
                    currentTeam.get(0).getAllPlayers());

            caretaker.undo(); // Restore previous state
        }
    }

    @Override
    public void redo() {
        // Check if there are states to revert to
        if (caretaker.getRedoListSize() > 0) {
            // Save current state for potential undo before performing redo
            caretaker.saveTeam(currentTeam, currentTeam.get(0).getTeamID(), currentTeam.get(0).getName(),
                    currentTeam.get(0).getAllPlayers());

            caretaker.redo(); // Restore next state
        }
    }

    // Method to return a string representation of the command.
    public String toString() {
        return "Delete player, " + player.getPlayerID();
    }
}
