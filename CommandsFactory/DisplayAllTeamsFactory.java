package CommandsFactory;

import Commands.*;
import STMS.*;
import java.util.*;

public class DisplayAllTeamsFactory implements CommandFactory{
    private final Vector<Team> teams;

    public DisplayAllTeamsFactory(Vector<Team> teams) {
        this.teams = teams;
    }

    @Override
    public Command createCommand() {
        return new DisplayAllTeamsCommand(teams);
    }
}
