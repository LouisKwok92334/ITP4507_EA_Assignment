package STMS;

import java.util.*;

public class VolleyballTeam extends Team {
    private final int ATTACKER_POSITION = 1;
    private final int DEFENDER_POSITION = 2;

    public VolleyballTeam(String teamID) {
        super(teamID);
    }

    public void updatePlayerPosition() {
        Scanner sc = new Scanner(System.in);
        String input;
        Enumeration<Player> players = getAllPlayers();
        Player player = null;

        System.out.print("Please input player ID: ");
        input = sc.nextLine();

        while (players.hasMoreElements()) {
            player = players.nextElement();
            int position_index;

            if (player.getPlayerID().equals(input)) {
                String positions = "ATTACKER_POSITION = 1, DEFENDER_POSITION = 2";

                while (true) {
                    System.out.print(positions + ", please input position index: ");
                    input = sc.nextLine();

                    try {
                        position_index = Integer.parseInt(input);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid input, please try again!");
                    }
                }
                player.setPosition(position_index - 1);
                System.out.println("Updated Successfully!");

                break;
            }
        }
        if (player == null) {
            System.out.println("Player not found!");
        }
    }

    public void displayTeam() {
        String[] positions = { "Attackers", "Defenders" };
        StringBuilder[] players = new StringBuilder[positions.length];
        for (int i = 0; i < players.length; i++) {
            players[i] = new StringBuilder();
        }

        Enumeration<Player> allPlayers = getAllPlayers();
        Player player;

        while (allPlayers.hasMoreElements()) {
            player = allPlayers.nextElement();
            if (players[player.getPosition() - 1].length() > 0) {
                players[player.getPosition() - 1].append("\n");
            }
            players[player.getPosition() - 1].append(player.getPlayerID()).append(", ").append(player.getName());
        }

        System.out.println("Volleyball Team " + getName() + " (" + getTeamID() + ")");

        for (int i = 0; i < positions.length; i++) {
            if (players[i].length() == 0) {
                players[i] = new StringBuilder("NIL");
            }
            System.out.println(positions[i] + ":");
            System.out.println(players[i]);
        }
    }
}
