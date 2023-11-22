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

        while (players.hasMoreElements()) {
            this.players.add(players.nextElement());
        }
    }

    @Override
    public void restore() {
        team.firstElement().setName(name);

        Enumeration<Player> data = team.firstElement().getAllPlayers();
        Vector<Player> current_players = new Vector<Player>();
        while (data.hasMoreElements()) {
            current_players.add(data.nextElement());
        }

        for (Player player : current_players) {
            team.firstElement().remove(player);
        }

        for (Player player : players) {
            team.firstElement().addPlayer(player);
        }
    }

    public Team getTeam() {
        return team.firstElement();
    }

    public String getTeamID() {
        return teamID;
    }

    public String getName() {
        return name;
    }
}