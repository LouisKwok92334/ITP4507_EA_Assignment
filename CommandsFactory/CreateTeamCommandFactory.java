package CommandsFactory;

import Commands.*;
import STMS.Team;
import STMSFactory.*;
import java.util.*;

public class CreateTeamCommandFactory implements CommandFactory {
    private final Scanner sc;
    private final Vector<Team> teams;
    private final Vector<Team> currentTeam;
    private final Stack<Command> commands;
    private final Stack<Command> redos;
    private final HashMap<String, TeamFactory> teamFactories = new HashMap<>();

    public CreateTeamCommandFactory(Scanner sc, Vector<Team> teams, Vector<Team> currentTeam,
                                    Stack<Command> commands, Stack<Command> redos) {
        this.sc = sc;
        this.teams = teams;
        this.currentTeam = currentTeam;
        this.commands = commands;
        this.redos = redos;

        teamFactories.put("f", new FootballTeamFactory());
        teamFactories.put("v", new VolleyballTeamFactory());
    }

    public Command createCommand() {
        Command com = new CreateTeamCommand(sc, teams, currentTeam, teamFactories);
        commands.push(com);
        redos.clear();
        return com;
    }
}