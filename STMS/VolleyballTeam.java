package STMS;

import java.util.*;

public class VolleyballTeam extends Team {
    private final int ATTACKER_POSITION = 1;
    private final int DEFENDER_POSITION = 2;

    public VolleyballTeam(String teamID) {
        super(teamID);
    }

    public void updatePlayerPosition() {}

    public void displayTeam() {
        String[] positionNames = { "Attackers", "Defenders" };
        StringBuilder[] positionBuilders = {new StringBuilder(), new StringBuilder()};

        Enumeration<Player> allPlayers = getAllPlayers();
        while (allPlayers.hasMoreElements()) {
            Player player = allPlayers.nextElement();
            int positionIndex = player.getPosition() - 1;
            positionBuilders[positionIndex].append("\n").append(player.getPlayerID()).append(", ").append(player.getName());
        }

        System.out.println("Volleyball Team " + getName() + " (" + getTeamID() + ")");

        for (int i = 0; i < positionBuilders.length; i++) {
            StringBuilder players = positionBuilders[i];
            System.out.println(positionNames[i] + ":" + (players.isEmpty() ? "\nNIL" : players));
        }
    }
}
