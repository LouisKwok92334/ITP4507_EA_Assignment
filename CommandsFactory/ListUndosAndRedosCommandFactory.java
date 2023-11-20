package CommandsFactory;

import Commands.*;
import STMS.*;
import java.util.*;

public class ListUndosAndRedosCommandFactory implements CommandFactory {
    private final Stack<Command> commands;
    private final Stack<Command> redos;

    public ListUndosAndRedosCommandFactory(Stack<Command> commands, Stack<Command> redos) {
        this.commands = commands;
        this.redos = redos;
    }

    @Override
    public Command createCommand() {
        Command com = new ListUndosAndRedosCommand(commands, redos);
        return com;
    }
}
