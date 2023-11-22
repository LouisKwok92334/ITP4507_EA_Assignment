package CommandsFactory;

import Commands.*;
import STMS.Team;

import java.util.*;

public class SetCurrentTeamCommandFactory implements CommandFactory {
    private final Scanner sc;
    private final Vector<Team> teams;
    private final Vector<Team> currentTeam;

    public SetCurrentTeamCommandFactory(Scanner sc, Vector<Team> teams, Vector<Team> currentTeam) {
        this.sc = sc;
        this.teams = teams;
        this.currentTeam = currentTeam;
    }

    @Override
    public Command createCommand() {
        return new SetCurrentTeamCommand(sc, teams, currentTeam);
    }
}
