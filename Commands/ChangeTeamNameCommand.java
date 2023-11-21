package Commands;

import Mementos.Caretaker;
import STMS.Player;
import STMS.Team;

import java.util.*;

public class ChangeTeamNameCommand implements Command {
    private final Scanner sc;
    private final Vector<Team> currentTeam;
    private final Caretaker caretaker;
    private String name;

    public ChangeTeamNameCommand(Scanner sc, Vector<Team> currentTeam, Caretaker caretaker) {
        this.sc = sc;
        this.currentTeam = currentTeam;
        this.caretaker = caretaker;
        this.name = null;
    }

    @Override
    public void execute() {
        if (currentTeam.isEmpty()) {
            System.out.println("Please create a team first!");
            return;
        }

        Enumeration<Player> players = currentTeam.firstElement().getAllPlayers();
        System.out.println("Please input new name of the current team:- ");
        name = sc.next();

        while (players.hasMoreElements()) {
            Player player = players.nextElement();
            if (name.equals(player.getName())) {
                // Save the state before modification
                caretaker.saveTeamName(currentTeam);

                // Modify the player's position
                currentTeam.firstElement().setName(name);
                caretaker.clearRedoStack();

                System.out.println("Team’s name is updated.");
                return; // Exit after modifying the player
            }
        }

        System.out.println("Team’s name is updated.");
    }

    @Override
    public void undo() {
        if (caretaker.getUndoListSize() > 0) {
            caretaker.saveTeamName_redo(currentTeam);
            caretaker.undo();
        }
    }

    @Override
    public void redo() {
        if (caretaker.getRedoListSize() > 0) {
            caretaker.saveTeamName(currentTeam);
            caretaker.undo();
        }
    }

    public String toString() {
        return "Change team's name, " + currentTeam.firstElement().getTeamID() + ", " + currentTeam.firstElement().getName();
    }
}
