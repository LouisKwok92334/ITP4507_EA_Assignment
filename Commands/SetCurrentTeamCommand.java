package Commands;

import STMS.*;
import java.util.*;

public class SetCurrentTeamCommand implements Command {
    private final Scanner sc;
    private final Vector<Team> teams;
    private final Vector<Team> currentTeam;

    public SetCurrentTeamCommand(Scanner sc, Vector<Team> teams, Vector<Team> currentTeam) {
        this.sc = sc;
        this.teams = teams;
        this.currentTeam = currentTeam;
    }

    @Override
    public void execute() {
        if (currentTeam.isEmpty()) {
            System.out.println("Please create a team first!");
            return;
        }

        System.out.print("Please input team ID:- ");
        String teamID = sc.next().trim();

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
    public void undo() {}

    @Override
    public void redo() {}
}