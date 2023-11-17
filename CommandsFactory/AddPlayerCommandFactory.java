package CommandsFactory;

import Commands.*;
import STMS.Team;

import java.util.*;

public class AddPlayerCommandFactory implements CommandFactory {
    private final Scanner sc;
    private final Vector<Team> currentTeam;
    private final Stack<Command> commands;
    private final Stack<Command> redos;

    public AddPlayerCommandFactory(Scanner sc, Vector<Team> currentTeam,
                                    Stack<Command> commands, Stack<Command> redos) {
        this.sc = sc;
        this.currentTeam = currentTeam;
        this.commands = commands;
        this.redos = redos;
    }

    @Override
    public Command createCommand() {
        Command com = new AddPlayerCommand(sc, currentTeam);
        commands.push(com);
        redos.clear();
        return com;
    }
}
