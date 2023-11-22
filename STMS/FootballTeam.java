package STMS;

import java.util.*;

public class FootballTeam extends Team {
    private final int GOALKEEPER_POSITION = 1;
    private final int DEFENDER_POSITION = 2;
    private final int MIDFIELDER_POSITION = 3;
    private final int FORWARD_POSITION = 4;

    public FootballTeam(String teamID) {
        super(teamID);
    }

    public void updatePlayerPosition() {}

    public void displayTeam() {
        String[] positionNames = {"Goalkeeper", "Defender", "Midfielder", "Forward"};
        StringBuilder[] positionBuilders = {new StringBuilder(), new StringBuilder(), new StringBuilder(), new StringBuilder()};

        Enumeration<Player> allPlayers = getAllPlayers();
        while (allPlayers.hasMoreElements()) {
            Player player = allPlayers.nextElement();
            int positionIndex = player.getPosition() - 1;
            positionBuilders[positionIndex].append("\n").append(player.getPlayerID()).append(", ").append(player.getName());
        }

        System.out.println("Football Team: " + getName() + " (" + getTeamID() + ")");

        for (int i = 0; i < positionBuilders.length; i++) {
            StringBuilder players = positionBuilders[i];
            System.out.println(positionNames[i] + ":" + (players.isEmpty() ? "\nNIL" : players));
        }
    }
}