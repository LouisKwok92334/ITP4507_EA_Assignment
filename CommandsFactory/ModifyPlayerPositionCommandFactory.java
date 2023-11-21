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
        Map<Integer, String> positionDescriptions = new HashMap<>();
        StringBuilder positionDescription = new StringBuilder("Position (");

        if (currentTeam.firstElement() instanceof FootballTeam) {
            positionDescriptions.put(1, "goal keeper");
            positionDescriptions.put(2, "defender");
            positionDescriptions.put(3, "midfielder");
            positionDescriptions.put(4, "forward");
        } else if (currentTeam.firstElement() instanceof VolleyballTeam) {
            positionDescriptions.put(1, "attacker");
            positionDescriptions.put(2, "defender");
        }

        // Build the position string
        for (Map.Entry<Integer, String> entry : positionDescriptions.entrySet()) {
            positionDescription.append(String.format("%d = %s | ", entry.getKey(), entry.getValue()));
        }
        positionDescription.setLength(positionDescription.length() - 3);
        positionDescription.append("):- ");

        // Print the positions
        System.out.println(positionDescription);

        Command com = new ModifyPlayerPositionCommand(sc, currentTeam, positionDescriptions, caretaker);
        commands.push(com);
        redos.clear();
        return com;
    }
}
