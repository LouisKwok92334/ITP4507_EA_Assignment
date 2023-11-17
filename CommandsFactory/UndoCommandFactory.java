package CommandsFactory;

import java.util.*;
import Commands.*;

public class UndoCommandFactory implements CommandFactory{

    private final Stack<Command> commands;
    private final Stack<Command> redos;

    public UndoCommandFactory(Stack<Command> commands, Stack<Command> redos){
        this.commands = commands;
        this.redos = redos;
    }

    public Command createCommand(){
        return new UndoCommand(commands, redos);
    }
}
