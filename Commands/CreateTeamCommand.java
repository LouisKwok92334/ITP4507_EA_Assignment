package Commands;

import STMS.*;
import STMSFactory.*;
import java.util.*;

public class CreateTeamCommand implements Command {
    private final Scanner sc;
    private final Vector<Team> teams;
    private final Vector<Team> currentTeam;
    private final HashMap<String, TeamFactory> teamFactories;
    private TeamFactory teamFactory;
    private Team team;
    private Team previousCurrentTeam;
    private String type;
    private String teamID;
    private String teamName;

    public CreateTeamCommand(Scanner sc, Vector<Team> teams, Vector<Team> currentTeam, HashMap<String, TeamFactory> teamFactories) {
        this.sc = sc;
        this.teams = teams;
        this.currentTeam = currentTeam;
        this.teamFactories = teamFactories;
        this.team = null;
        this.type = null;
        this.teamID = null;
        this.teamName = null;
    }

    @Override
    public void execute() {
        System.out.print("Enter sport type ( v = volleyball | f = football) :- ");
        type = sc.next().trim().toLowerCase(); // Add trim() to remove any extra whitespace

        if (!currentTeam.isEmpty()) {
            previousCurrentTeam = currentTeam.get(0);
        } else {
            previousCurrentTeam = null;
        }

        teamFactory = teamFactories.get(type);
        if (teamFactory == null) {
            System.out.println("Invalid input, please try again!");
            return;
        }

        System.out.print("Team ID:- ");
        teamID = sc.next().trim();
        System.out.print("Team Name:- ");
        teamName = sc.next().trim();

        team = teamFactory.createTeam(teamID, teamName);
        teams.add(team);

        currentTeam.clear();
        currentTeam.add(team);
        System.out.println("Current team is changed to " + teamID + ".");
    }

    @Override
    public void undo() {
        if (team != null){
            teams.remove(team);
            currentTeam.clear();
            if (previousCurrentTeam != null) {
                currentTeam.add(previousCurrentTeam);  // Restore the previous current team
            }
        }
    }

    @Override
    public void redo() {
        if (team != null){
            teams.add(team);
            currentTeam.clear();
            currentTeam.add(team);
        }
    }

    public String toString() {
        String className = teamFactory.getClass().getSimpleName();
        String sportType = className.replace("TeamFactory", "").toLowerCase();
        return "Create " + sportType + " team, " + teamID + ", " + teamName;
    }
}
