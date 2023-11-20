package CommandsFactory;

import Commands.*;
import STMS.Team;
import STMSFactory.PlayerFactory;

import java.util.*;

public class AddPlayerCommandFactory implements CommandFactory {
    private final Scanner sc;
    private final Vector<Team> currentTeam;
    private final Stack<Command> commands;
    private final Stack<Command> redos;
    private final PlayerFactory playerFactory;

    public AddPlayerCommandFactory(Scanner sc, Vector<Team> currentTeam,
                                    Stack<Command> commands, Stack<Command> redos) {
        this.sc = sc;
        this.currentTeam = currentTeam;
        this.commands = commands;
        this.redos = redos;
        playerFactory = new PlayerFactory();
    }

    @Override
    public Command createCommand() {
        Command com = new AddPlayerCommand(sc, currentTeam, playerFactory);
        commands.push(com);
        redos.clear();
        return com;
    }
}
