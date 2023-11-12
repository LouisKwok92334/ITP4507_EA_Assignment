package Commands;

import java.util.*;

public class UndoCommand implements Command{

    private Stack commands;
    private Stack redos;

    public UndoCommand(Stack commands, Stack redos){
        this.commands = commands;
        this.redos = redos;
    }

    public void execute(){
        if (commands.size() > 0){
            Command com = (Command) commands.pop();
            com.undo();
            redos.push(com);
        } else {
            System.out.println("Nothing to Undo");
        }
    }

    public void undo(){};
    public void redo(){};
}
