package CommandsFactory;

import Commands.*;
import java.util.*;

public class DisplayAllTeamsFactory implements CommandFactory{

    private Vector teams;

    public DisplayAllTeamsFactory(Vector teams) {
        this.teams = teams;
    }

    @Override
    public Command createCommand() {
        Command com = new DisplayAllTeamsCommand(teams);
        return com;
    }
}
