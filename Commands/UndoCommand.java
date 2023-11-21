package Commands;

import java.util.*;

public class UndoCommand implements Command{

    private final Stack<Command> commands;
    private final Stack<Command> redos;

    public UndoCommand(Stack<Command> commands, Stack<Command> redos){
        this.commands = commands;
        this.redos = redos;
    }

    public void execute(){
        if (!commands.isEmpty()){
            Command com = commands.pop();
            System.out.println("Command (" + com + ") is undone.");

            com.undo();
            redos.push(com);
        } else {
            System.out.println("Nothing to Undo!");
        }
    }

    public void undo(){}
    public void redo(){}
}
