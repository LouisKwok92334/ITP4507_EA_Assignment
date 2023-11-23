package CommandsFactory;

import Commands.*;
import Mementos.*;
import STMS.*;
import java.util.*;

public class ModifyPlayerPositionCommandFactory implements CommandFactory {
    private final Scanner sc;
    private final Vector<Team> currentTeam;
    private final Stack<Command> commands;
    private final Stack<Command> redos;
    private final Caretaker caretaker;

    public ModifyPlayerPositionCommandFactory(Scanner sc, Vector<Team> currentTeam, Stack<Command> commands,
                                              Stack<Command> redos, Caretaker caretaker) {
        this.sc = sc;
        this.commands = commands;
        this.currentTeam = currentTeam;
        this.redos = redos;
        this.caretaker = caretaker;
    }

    @Override
    public Command createCommand() {
        Command com = new ModifyPlayerPositionCommand(sc, currentTeam, caretaker);
        commands.push(com);
        redos.clear();
        return com;
    }
}
