package Mementos;

import STMS.*;
import java.util.*;

public class TeamMemento extends Memento {
    private final Vector<Team> team;
    private final String teamID;
    private final String name;
    private final Vector<Player> players;

    public TeamMemento(Vector<Team> team, String teamID, String name, Enumeration<Player> players) {
        this.team = team;
        this.teamID = teamID;
        this.name = name;
        this.players = new Vector<Player>();

        // Convert enumeration of players to a vector for the memento
        while (players.hasMoreElements()) {
            this.players.add(players.nextElement());
        }
    }

    @Override
    // Restores the saved state of the team and its players
    public void restore() {
        // Set the name of the first element in the team vector to the saved name
        team.firstElement().setName(name);

        // Retrieve the current list of players from the team to compare with memento's list
        Enumeration<Player> data = team.firstElement().getAllPlayers();
        Vector<Player> current_players = new Vector<Player>();
        while (data.hasMoreElements()) {
            current_players.add(data.nextElement());
        }

        // Remove all current players from the team to prepare for state restoration
        for (Player player : current_players) {
            team.firstElement().remove(player);
        }

        // Add all players from the memento's saved state to the team
        for (Player player : players) {
            team.firstElement().addPlayer(player);
        }
    }

    // Returns the specific team captured in this memento
    public Team getTeam() {
        return team.firstElement();
    }

    // Returns the team ID associated with the saved state
    public String getTeamID() {
        return teamID;
    }

    // Returns the name associated with the saved state
    public String getName() {
        return name;
    }
}