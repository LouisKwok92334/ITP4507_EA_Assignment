package CommandsFactory;

import java.util.*;
import Commands.*;

public class RedoCommandFactory implements CommandFactory {

    private final Stack<Command> commands;
    private final Stack<Command> redos;

    public RedoCommandFactory(Stack<Command> commands, Stack<Command> redos){
        this.commands = commands;
        this.redos = redos;
    }

    public Command createCommand(){
        return new RedoCommand(commands, redos);
    }
}

