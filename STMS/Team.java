package STMS;

import java.util.*;

public abstract class Team {
    private String teamID;
    private String name;
    private Vector<Player> players;

    public Team(String teamID) {
        this.teamID = teamID;
        players = new Vector<>();
    }

    public String getTeamID() {
        return teamID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void remove(Player player) {
        players.remove(player);
    }

    public Enumeration<Player> getAllPlayers() {
        return players.elements();
    }

    public abstract void updatePlayerPosition();

    public abstract void displayTeam();
}
