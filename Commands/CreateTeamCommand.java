package Commands;

import STMS.*;
import STMSFactory.*;
import java.util.*;

public class CreateTeamCommand implements Command {
    private String teamID;
    private String teamName;
    private String teamType;
    private Vector teams;
    private Team team;
    private TeamManager teamManager;
    private TeamFactory teamFactory;

    public CreateTeamCommand(TeamFactory teamFactory, String teamType, Vector teams, String teamID, String teamName, TeamManager teamManager) {
        this.teamFactory = teamFactory;
        this.teamType = teamType;
        this.teams = teams;
        this.teamID = teamID;
        this.teamName = teamName;
        this.teamManager = teamManager;
        this.team = null;
    }

    @Override
    public void execute() {
        Team team = teamFactory.createTeam(teamID, teamName);
        teamManager.setCurrentTeam(team);
        System.out.println(teamType + " team is created.");
        System.out.println("Current team is changed to " + teamID + ".");
        this.team = team;
        teams.add(team);
    }

    @Override
    public void undo() {
        if (team != null){
            teams.remove(team);
        }
    }

    @Override
    public void redo() {
        if (team != null){
            teams.add(team);
        }
    }
}
