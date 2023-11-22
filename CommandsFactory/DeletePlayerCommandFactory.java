package CommandsFactory;

import Commands.*;
import Mementos.*;
import STMS.*;
import java.util.*;

public class DeletePlayerCommandFactory implements CommandFactory {
    private final Scanner sc;
    private final Vector<Team> currentTeam;
    private final Stack<Command> commands;
    private final Stack<Command> redos;
    private final Caretaker caretaker;

    public DeletePlayerCommandFactory(Scanner sc, Vector<Team> currentTeam,
                                    Stack<Command> commands, Stack<Command> redos, Caretaker caretaker) {
        this.sc = sc;
        this.currentTeam = currentTeam;
        this.commands = commands;
        this.redos = redos;
        this.caretaker = caretaker;
    }

    @Override
    public Command createCommand() {
        Command com = new DeletePlayerCommand(sc, currentTeam, caretaker);
        commands.push(com);
        redos.clear();
        return com;
    }
}
