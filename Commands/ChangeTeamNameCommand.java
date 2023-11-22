package Commands;

import Mementos.Caretaker;
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
        System.out.print("Please input new name of the current team:- ");
        name = sc.next();

        // Save the state before modification
        caretaker.saveTeam(currentTeam, currentTeam.firstElement().getTeamID(), currentTeam.firstElement().getName(),
                currentTeam.firstElement().getAllPlayers());

        // Modify the team's name
        currentTeam.firstElement().setName(name);
        caretaker.clearRedoStack();

        System.out.println("Team’s name is updated.");
    }

    @Override
    public void undo() {
        if (caretaker.getUndoListSize() > 0) {
            caretaker.saveTeam_redo(currentTeam, currentTeam.firstElement().getTeamID(), currentTeam.firstElement().getName(),
                    currentTeam.firstElement().getAllPlayers());
            caretaker.undo();
        }
    }

    @Override
    public void redo() {
        if (caretaker.getRedoListSize() > 0) {
            caretaker.saveTeam(currentTeam, currentTeam.firstElement().getTeamID(), currentTeam.firstElement().getName(),
                    currentTeam.firstElement().getAllPlayers());
            caretaker.redo();
        }
    }

    public String toString() {
        return "Change team's name, " + currentTeam.firstElement().getTeamID() + ", " + currentTeam.firstElement().getName();
    }
}
