package STMS;

import java.util.*;

public class VolleyballTeam extends Team{
    private final int ATTACKER_POSITION = 1;
    private final int DEFENDER_POSITION = 2;

    public VolleyballTeam(String teamID) {
        super(teamID);
    }

    @Override
    public void updatePlayerPosition() {
        Enumeration<Player> enumeration = getAllPlayers();
        while (enumeration.hasMoreElements()) {
            Player player = enumeration.nextElement();
            if (player.getPosition() == ATTACKER_POSITION) {
                player.setPosition(DEFENDER_POSITION);
            } else if (player.getPosition() == DEFENDER_POSITION) {
                player.setPosition(ATTACKER_POSITION);
            }
        }
    }

    @Override
    public void displayTeam() {
        System.out.println("Volleyball Team " + getTeamID() + " (" + getName() + ")");

        System.out.println("Attackers:");
        Enumeration<Player> attackers = getAllPlayers();
        while (attackers.hasMoreElements()) {
            Player player = attackers.nextElement();
            if (player.getPosition() == ATTACKER_POSITION) {
                System.out.println(player.getPlayerID() + ", " + player.getName());
            }
        }

        System.out.println("Defenders:");
        Enumeration<Player> defenders = getAllPlayers();
        while (defenders.hasMoreElements()) {
            Player player = defenders.nextElement();
            if (player.getPosition() == DEFENDER_POSITION) {
                System.out.println(player.getPlayerID() + ", " + player.getName());
            }
        }
    }
}
