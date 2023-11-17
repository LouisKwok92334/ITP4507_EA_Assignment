package CommandsFactory;

import Commands.*;
import STMS.*;
import java.util.*;

public class DeletePlayerCommandFactory implements CommandFactory {
    private final Scanner sc;
    private final Vector<Team> currentTeam;
    private final Stack<Command> commands;
    private final Stack<Command> redos;

    public DeletePlayerCommandFactory(Scanner sc, Vector<Team> currentTeam,
                                    Stack<Command> commands, Stack<Command> redos) {
        this.sc = sc;
        this.currentTeam = currentTeam;
        this.commands = commands;
        this.redos = redos;
    }

    @Override
    public Command createCommand() {
        Command com = new DeletePlayerCommand(sc, currentTeam);
        commands.push(com);
        redos.clear();
        return com;
    }
}
