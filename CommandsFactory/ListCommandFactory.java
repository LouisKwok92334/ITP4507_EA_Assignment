package CommandsFactory;

import Commands.*;

import java.util.*;

public class ListCommandFactory implements CommandFactory {
    private final Stack<Command> commands;
    private final Stack<Command> redos;

    public ListCommandFactory(Stack<Command> commands, Stack<Command> redos) {
        this.commands = commands;
        this.redos = redos;
    }

    @Override
    public Command createCommand() {
        Command com = new ListCommand(commands, redos);
        return com;
    }
}
