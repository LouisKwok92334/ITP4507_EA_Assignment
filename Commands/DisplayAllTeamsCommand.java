package Commands;

import STMS.*;
import java.util.*;

public class DisplayAllTeamsCommand implements Command {
    private Vector teams;

    public DisplayAllTeamsCommand(Vector teams) {
        this.teams = teams;
    }

    @Override
    public void execute() {
        for (int i = 0; i < teams.size(); i++) {
            Team team = (Team) teams.elementAt(i);
            if (team instanceof FootballTeam) {
                System.out.println("Football Team " + team.getName() + " (" + team.getTeamID() + ")");
            } else if (team instanceof VolleyballTeam) {
                System.out.println("Volleyball Team " + team.getName() + " (" + team.getTeamID() + ")");
            }
        }
    }

    @Override
    public void undo() {}

    @Override
    public void redo() {}
}
