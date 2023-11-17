package Commands;

import STMS.*;
import STMSFactory.*;
import java.util.*;

public class CreateTeamCommand implements Command {
    private final Scanner sc;
    private final Vector<Team> teams;
    private final Vector<Team> currentTeam;
    private final HashMap<String, TeamFactory> teamFactories;
    private Team team;

    public CreateTeamCommand(Scanner sc, Vector<Team> teams, Vector<Team> currentTeam, HashMap<String, TeamFactory> teamFactories) {
        this.sc = sc;
        this.teams = teams;
        this.currentTeam = currentTeam;
        this.teamFactories = teamFactories;
        this.team = null;
    }

    @Override
    public void execute() {
        System.out.print("Enter team type (v = volleyball | f = football): ");
        String teamType = sc.next().trim(); // Add trim() to remove any extra whitespace

        TeamFactory teamFactory = teamFactories.get(teamType);
        if (teamFactory == null) {
            System.out.println("Invalid input, please try again!");
            return;
        }

        System.out.print("Team ID: ");
        String teamID = sc.next().trim();
        System.out.print("Team Name: ");
        String teamName = sc.next().trim();

        team = teamFactory.createTeam(teamID, teamName);
        teams.add(team);

        if (currentTeam.size() == 0) {
            currentTeam.add(team);
            System.out.println("Current team is changed to " + teamID + ".");
        }
    }

    @Override
    public void undo() {
        if (team != null){
            teams.remove(team);
            if (currentTeam.size() == 1 && currentTeam.get(0) == team) {
                currentTeam.remove(0);
            }
        }
    }

    @Override
    public void redo() {
        if (team != null){
            teams.add(team);
            if (currentTeam.size() == 0) {
                currentTeam.add(team);
            }
        }
    }
}
