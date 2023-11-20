package Commands;

import java.util.*;

public class ListUndosAndRedosCommand implements Command {
    private final Stack<Command> commands;
    private final Stack<Command> redos;

    public ListUndosAndRedosCommand(Stack<Command> commands, Stack<Command> redos) {
        this.commands = commands;
        this.redos = redos;
    }

    @Override
    public void execute() {
        System.out.println("Undo List");

        System.out.println("-- End of undo list --");
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
