package Commands;

import STMS.*;
import java.util.*;

public class SetCurrentTeamCommand implements Command {
    private Vector teams;
    private Vector currentTeam;
    private String teamID;
    private Team previousTeam;

    public SetCurrentTeamCommand(Vector teams, Vector currentTeam, String teamID) {
        this.teams = teams;
        this.currentTeam = currentTeam;
        this.teamID = teamID;
    }

    @Override
    public void execute() {
        if (!currentTeam.isEmpty()) {
            previousTeam = (Team) currentTeam.elementAt(0);
        }
        for (int i = 0; i < teams.size(); i++) {
            Team team = (Team) teams.elementAt(i);
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
        if (!currentTeam.isEmpty()) {
            currentTeam.clear();
        }
        if (previousTeam != null) {
            currentTeam.add(previousTeam);
        }
    }

    @Override
    public void redo() {
        for (int i = 0; i < teams.size(); i++) {
            Team team = (Team) teams.elementAt(i);
            if (team.getTeamID().equals(teamID)) {
                currentTeam.clear();
                currentTeam.add(team);
                System.out.println("Current team is changed to " + teamID + ".");
                return;
            }
        }
    }
}