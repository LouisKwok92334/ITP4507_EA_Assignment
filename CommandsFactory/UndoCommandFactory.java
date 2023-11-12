package CommandsFactory;

import java.util.*;
import Commands.*;

public class UndoCommandFactory implements CommandFactory{

    private Stack commands;
    private Stack redos;

    public UndoCommandFactory(Stack commands, Stack redos){
        this.commands = commands;
        this.redos = redos;
    }

    public Command createCommand(){
        Command com = new UndoCommand(commands, redos);
        return com;
    };

}
