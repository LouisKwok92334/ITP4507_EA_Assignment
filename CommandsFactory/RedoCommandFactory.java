package CommandsFactory;

import java.util.*;
import Commands.*;

public class RedoCommandFactory implements CommandFactory {

    private Stack commands;
    private Stack redos;

    public RedoCommandFactory(Stack commands, Stack redos){
        this.commands = commands;
        this.redos = redos;
    }

    public Command createCommand(){
        Command com = new RedoCommand(commands, redos);
        return com;
    };
}

