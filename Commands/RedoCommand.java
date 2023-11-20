package Commands;

import java.util.*;

public class RedoCommand implements Command {

    private final Stack<Command> commands;
    private final Stack<Command> redos;

    public RedoCommand(Stack<Command> commands, Stack<Command> redos){
        this.commands = commands;
        this.redos = redos;
    }

    public void execute(){
        if (redos.size() > 0){
            Command com = redos.pop();
            System.out.println("Command (" + com + ") is redone.");

            com.redo();
            commands.push(com);
        } else {
            System.out.println("Nothing to Redo!");
        }
    }

    public void undo(){};
    public void redo(){};
}
