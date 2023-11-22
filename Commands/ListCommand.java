package Commands;

import java.util.*;

public class ListCommand implements Command {
    private final Stack<Command> commands;
    private final Stack<Command> redos;

    public ListCommand(Stack<Command> commands, Stack<Command> redos) {
        this.commands = commands;
        this.redos = redos;
    }

    @Override
    public void execute() {
        String commandlist = "Undo List \n" + printList(commands, "") + "-- End of undo list -- \n";
        commandlist += "Redo List \n" + printList(redos, "") + "-- End of redo list --";

        System.out.println(commandlist);
    }

    public String printList(Stack<Command> stack, String string) {
        if (stack.empty()) {
            return "";
        }
        Command com = stack.pop();
        string = printList(stack, string);

        stack.push(com);
        return string += com + "\n";
    }

    @Override
    public void undo() {}

    @Override
    public void redo() {}
}
