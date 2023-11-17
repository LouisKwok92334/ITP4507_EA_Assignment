package Commands;

import STMS.*;
import java.util.*;

public class SetCurrentTeamCommand implements Command {
    private final Scanner sc;
    private final Vector<Team> teams;
    private final Vector<Team> currentTeam;
    private String teamID;
    private Team previousTeam; // Used to restore the previous state during an undo operation

    public SetCurrentTeamCommand(Scanner sc, Vector<Team> teams, Vector<Team> currentTeam) {
        this.sc = sc;
        this.teams = teams;
        this.currentTeam = currentTeam;
    }

    @Override
    public void execute() {
        System.out.print("Enter team ID: ");
        teamID = sc.next().trim();

        // Store the old current team for the undo operation
        previousTeam = currentTeam.isEmpty() ? null : currentTeam.firstElement();

        setTeamByID(teamID);
    }

    // Find and set the new current team based on the given ID
    private void setTeamByID(String teamID) {
        for (Team team : teams) {
            if (team.getTeamID().equals(teamID)) {
                currentTeam.clear();
                currentTeam.add(team);
                System.out.println("Current team is changed to " + teamID + ".");
                return;
            }
        }
        System.out.println("Team ID not found.");
    }

    @Override
    public void undo() {
        currentTeam.clear();
        if (previousTeam != null) {
            currentTeam.add(previousTeam);
        }
    }

    @Override
    public void redo() {
        if (teamID != null) {
            setTeamByID(teamID);
        }
    }
}