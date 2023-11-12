package CommandsFactory;

import Commands.*;
import STMS.*;
import STMSFactory.*;
import java.util.*;

public class CreateTeamCommandFactory implements CommandFactory {
    private Scanner sc;
    private Vector teams;
    private Stack commands;
    private TeamFactory teamFactory;
    private TeamManager teamManager;

    public CreateTeamCommandFactory(Scanner sc, Vector teams, Stack commands, TeamManager teamManager) {
        this.sc = sc;
        this.teams = teams;
        this.commands = commands;
        this.teamManager = teamManager;
    }

    public Command createCommand() {
        System.out.print("Enter team type (v = volleyball | f = football) :-");
        String teamType = sc.next();
        System.out.print("Team ID:- ");
        String teamID = sc.next();
        System.out.print("Team Name:- ");
        String teamName = sc.next();

        if (teamType.equals("v")) {
            teamFactory = new VolleyballTeamFactory();
            teamType = "Volleyball";
        } else if (teamType.equals("f")) {
            teamFactory = new FootballTeamFactory();
            teamType = "Football";
        } else {
            throw new IllegalArgumentException("Invalid team type: " + teamType);
        }

        Command com = new CreateTeamCommand(teamFactory, teamType, teams, teamID, teamName, teamManager);
        commands.push(com);
        return com;
    }
}