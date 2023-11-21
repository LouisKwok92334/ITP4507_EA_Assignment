package Commands;

import Mementos.*;
import STMS.*;
import java.util.*;

public class ModifyPlayerPositionCommand implements Command {
    private final Scanner sc;
    private final Vector<Team> currentTeam;
    private final Caretaker caretaker;
    private Player player;
    private int position;
    private final Map<Integer, String> positionDescriptions;

    public ModifyPlayerPositionCommand(Scanner sc, Vector<Team> currentTeam, Map<Integer, String> positionDescriptions, Caretaker caretaker) {
        this.sc = sc;
        this.currentTeam = currentTeam;
        this.positionDescriptions = positionDescriptions;
        this.caretaker = caretaker;
        this.position = 0;
    }

    @Override
    public void execute() {
        if (currentTeam.isEmpty()) {
            System.out.println("Please create a team first!");
            return;
        }

        Enumeration<Player> players = currentTeam.firstElement().getAllPlayers();
        System.out.print("Please input player ID:- ");
        String playerID = sc.next();

        while (players.hasMoreElements()) {
            Player player = players.nextElement();
            if (playerID.equals(player.getPlayerID())) {
                this.player = player;

                // Save the state before modification
                caretaker.savePlayerPosition(player);

                for (Map.Entry<Integer, String> entry : positionDescriptions.entrySet()) {
                    System.out.printf("%d = %s | ", entry.getKey(), entry.getValue());
                }
                System.out.println();

                position = sc.nextInt();

                // Modify the player's position
                player.setPosition(position);
                caretaker.clearRedoStack();

                System.out.println("Position is updated.");
                return; // Exit after modifying the player
            }
        }

        // If the loop completes without finding the player, we print an error message
        System.out.println("Player not found.");
    }

    @Override
    public void undo() {
        if (caretaker.getUndoListSize() > 0) {
            caretaker.savePlayerPosition_redo(player);
            caretaker.undo();
        }
    }

    @Override
    public void redo() {
        if (caretaker.getRedoListSize() > 0) {
            caretaker.savePlayerPosition(player);
            caretaker.redo();
        }
    }

    @Override
    public String toString() {
        String positionDescription = positionDescriptions.get(position);
        return "Modify player's position, " + player.getPlayerID() + ", " + positionDescription;
    }
}