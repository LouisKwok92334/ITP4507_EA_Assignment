package STMS;

import java.util.*;

public class FootballTeam extends Team{
    private final int GOALKEEPER_POSITION = 1;
    private final int DEFENDER_POSITION = 2;
    private final int MIDFIELDER_POSITION = 3;
    private final int FORWARD_POSITION = 4;

    public FootballTeam(String teamID) {
        super(teamID);
    }

    @Override
    public void updatePlayerPosition() {
        Enumeration<Player> enumeration = getAllPlayers();
        while (enumeration.hasMoreElements()) {
            Player player = enumeration.nextElement();
            switch (player.getPosition()) {
                case GOALKEEPER_POSITION:
                    player.setPosition(DEFENDER_POSITION);
                    break;
                case DEFENDER_POSITION:
                    player.setPosition(MIDFIELDER_POSITION);
                    break;
                case MIDFIELDER_POSITION:
                    player.setPosition(FORWARD_POSITION);
                    break;
                case FORWARD_POSITION:
                    player.setPosition(GOALKEEPER_POSITION);
                    break;
            }
        }
    }

    @Override
    public void displayTeam() {
        System.out.println("Football Team " + getTeamID() + " (" + getName() + ")");

        System.out.println("Goal keeper:");
        Enumeration<Player> enumeration = getAllPlayers();
        while (enumeration.hasMoreElements()) {
            Player player = enumeration.nextElement();
            if (player.getPosition() == GOALKEEPER_POSITION) {
                System.out.println(player.getPlayerID() + ", " + player.getName());
            }
        }

        System.out.println("Defender:");
        enumeration = getAllPlayers();
        while (enumeration.hasMoreElements()) {
            Player player = enumeration.nextElement();
            if (player.getPosition() == DEFENDER_POSITION) {
                System.out.println(player.getPlayerID() + ", " + player.getName());
            }
        }

        System.out.println("Midfielder:");
        enumeration = getAllPlayers();
        while (enumeration.hasMoreElements()) {
            Player player = enumeration.nextElement();
            if (player.getPosition() == MIDFIELDER_POSITION) {
                System.out.println(player.getPlayerID() + ", " + player.getName());
            }
        }

        System.out.println("Forward:");
        enumeration = getAllPlayers();
        while (enumeration.hasMoreElements()) {
            Player player = enumeration.nextElement();
            if (player.getPosition() == FORWARD_POSITION) {
                System.out.println(player.getPlayerID() + ", " + player.getName());
            }
        }
    }
}
