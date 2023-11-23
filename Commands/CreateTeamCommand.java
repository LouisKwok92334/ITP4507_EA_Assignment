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
        this.sc = sc;  // Scanner for user input
        this.teams = teams; // Collection of all teams
        this.currentTeam = currentTeam;  // Reference to the currently selected team
        this.teamFactories = teamFactories; // Factories to create different types of teams
        this.team = null;
        this.type = null;
        this.teamID = null;
        this.teamName = null;
    }

    // Execute method that will be called when the command is run.
    @Override
    public void execute() {
        System.out.print("Enter sport type ( v = volleyball | f = football) :- ");
        type = sc.next().trim().toLowerCase(); // Add trim() to remove any extra whitespace

        // Remember the previous current team before creating a new one
        if (!currentTeam.isEmpty()) {
            previousCurrentTeam = currentTeam.get(0);
        } else {
            previousCurrentTeam = null;
        }

        // Use the team factory corresponding to the sport type
        teamFactory = teamFactories.get(type);
        if (teamFactory == null) {
            // Handle invalid sport type input
            System.out.println("Invalid input, please try again!");
            return;
        }

        // Read and trim Team ID and name inputs
        System.out.print("Team ID:- ");
        teamID = sc.next().trim();
        System.out.print("Team Name:- ");
        teamName = sc.next().trim();

        // Create a new team using the factory and update the collections accordingly
        team = teamFactory.createTeam(teamID, teamName);
        teams.add(team);

        // Set the newly created team as the current team
        currentTeam.clear();
        currentTeam.add(team);
        System.out.println("Current team is changed to " + teamID + ".");
    }

    // Undo method to revert the creation of a team.
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

    // Redo method to reapply the creation of a team after an undo.
    @Override
    public void redo() {
        if (team != null){
            teams.add(team);
            currentTeam.clear();
            currentTeam.add(team);
        }
    }

    // Method to return a string representation of the command.
    public String toString() {
        String className = teamFactory.getClass().getSimpleName();
        String sportType = className.replace("TeamFactory", "").toLowerCase();
        return "Create " + sportType + " team, " + teamID + ", " + teamName;
    }
}
