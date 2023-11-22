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
    private final Map<Integer, String> positionDescriptions = new HashMap<>();

    public ModifyPlayerPositionCommand(Scanner sc, Vector<Team> currentTeam, Caretaker caretaker) {
        this.sc = sc;
        this.currentTeam = currentTeam;
        this.caretaker = caretaker;
        this.position = 0;
    }

    @Override
    public void execute() {
        if (currentTeam.isEmpty()) {
            System.out.println("Please create a team first!");
            return;
        }

        System.out.print("Please input player ID:- ");
        String playerID = sc.next();

        Enumeration<Player> players = currentTeam.firstElement().getAllPlayers();
        while (players.hasMoreElements()) {
            Player player = players.nextElement();
            if (playerID.equals(player.getPlayerID())) {
                this.player = player;
                caretaker.savePlayerPosition(player);

                if (currentTeam.firstElement() instanceof FootballTeam) {
                    positionDescriptions.put(1, "goal keeper");
                    positionDescriptions.put(2, "defender");
                    positionDescriptions.put(3, "midfielder");
                    positionDescriptions.put(4, "forward");
                } else if (currentTeam.firstElement() instanceof VolleyballTeam) {
                    positionDescriptions.put(1, "attacker");
                    positionDescriptions.put(2, "defender");
                }

                StringBuilder positionDescription = new StringBuilder("Position (");
                for (Map.Entry<Integer, String> entry : positionDescriptions.entrySet()) {
                    positionDescription.append(String.format("%d = %s | ", entry.getKey(), entry.getValue()));
                }

                positionDescription.setLength(positionDescription.length() - 3);
                positionDescription.append("):- ");
                System.out.print(positionDescription);

                position = sc.nextInt();
                player.setPosition(position);
                caretaker.clearRedoStack();
                System.out.println("Position is updated.");
                return;
            }
        }
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