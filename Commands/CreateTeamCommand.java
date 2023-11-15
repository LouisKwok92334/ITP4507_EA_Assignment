package Commands;

import STMS.*;
import STMSFactory.*;
import java.util.*;

public class CreateTeamCommand implements Command {
    private String teamID;
    private String teamName;
    private String teamType;
    private Vector teams;
    private Vector currentTeam;
    private Team team;
    private TeamFactory teamFactory;

    public CreateTeamCommand(TeamFactory teamFactory, String teamType, Vector teams, String teamID, String teamName, Vector currentTeam) {
        this.teamFactory = teamFactory;
        this.teamType = teamType;
        this.teams = teams;
        this.teamID = teamID;
        this.teamName = teamName;
        this.currentTeam = currentTeam;
        this.team = null;
    }

    @Override
    public void execute() {
        Team team = teamFactory.createTeam(teamID, teamName);
        System.out.println(teamType + " team is created.");
        System.out.println("Current team is changed to " + teamID + ".");
        this.team = team;
        teams.add(team);
        if (currentTeam.size() == 0) {
            currentTeam.add(team);
        }
    }

    @Override
    public void undo() {
        if (team != null){
            teams.remove(team);
        }
        if (currentTeam.size() == 1 && currentTeam.get(0) == team) {
            currentTeam.remove(0);
        }
    }

    @Override
    public void redo() {
        if (team != null){
            teams.add(team);
        }
        if (currentTeam.size() == 0) {
            currentTeam.add(team);
        }
    }
}
