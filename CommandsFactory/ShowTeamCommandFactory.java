package CommandsFactory;

import Commands.*;
import STMS.*;
import java.util.*;

public class ShowTeamCommandFactory implements CommandFactory {
    private final Vector<Team> currentTeam;

    public ShowTeamCommandFactory(Vector<Team> currentTeam) {
        this.currentTeam = currentTeam;
    }

    @Override
    public Command createCommand() {
        return new ShowTeamCommand(currentTeam);
    }
}
