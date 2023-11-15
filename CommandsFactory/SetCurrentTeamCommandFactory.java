package CommandsFactory;

import Commands.*;
import java.util.*;

public class SetCurrentTeamCommandFactory implements CommandFactory {
    private Scanner sc;
    private Vector teams;
    private Vector currentTeam;
    private Stack commands;

    public SetCurrentTeamCommandFactory(Scanner sc, Vector teams, Stack commands, Vector currentTeam) {
        this.sc = sc;
        this.teams = teams;
        this.commands = commands;
        this.currentTeam = currentTeam;
    }

    @Override
    public Command createCommand() {
        System.out.print("Please input team ID:- ");
        String teamID = sc.next();

        Command com = new SetCurrentTeamCommand(teams, currentTeam, teamID);
        commands.push(com);
        return com;
    }
}
