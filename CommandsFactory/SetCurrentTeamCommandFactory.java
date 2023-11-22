package CommandsFactory;

import Commands.*;
import STMS.Team;

import java.util.*;

public class SetCurrentTeamCommandFactory implements CommandFactory {
    private final Scanner sc;
    private final Vector<Team> teams;
    private final Vector<Team> currentTeam;
    private final Stack<Command> commands;
    private final Stack<Command> redos;

    public SetCurrentTeamCommandFactory(Scanner sc, Vector<Team> teams, Vector<Team> currentTeam,
                                        Stack<Command> commands, Stack<Command> redos) {
        this.sc = sc;
        this.teams = teams;
        this.commands = commands;
        this.currentTeam = currentTeam;
        this.redos = redos;
    }

    @Override
    public Command createCommand() {
        return new SetCurrentTeamCommand(sc, teams, currentTeam);
    }
}
