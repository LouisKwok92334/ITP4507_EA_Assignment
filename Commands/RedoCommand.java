package Commands;

import java.util.*;

public class RedoCommand implements Command {

    private Stack commands;
    private Stack redos;

    public RedoCommand(Stack commands, Stack redos){
        this.commands = commands;
        this.redos = redos;
    }

    public void execute(){
        if (redos.size() > 0){
            Command com = (Command) redos.pop();
            com.redo();
            commands.push(com);
        } else {
            System.out.println("Nothing to Redo!");
        }
    }

    public void undo(){};
    public void redo(){};
}
