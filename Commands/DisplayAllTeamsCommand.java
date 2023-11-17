package Commands;

import STMS.*;
import java.util.*;

public class DisplayAllTeamsCommand implements Command {
    private final Vector<Team> teams;

    public DisplayAllTeamsCommand(Vector<Team> teams) {
        this.teams = teams;
    }

    @Override
    public void execute() {
        if (teams.isEmpty()) {
            System.out.println("No teams available to display.");
            return;
        }

        for (Team team : teams) {
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
