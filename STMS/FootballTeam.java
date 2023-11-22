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
                String positions = "GOALKEEPER_POSITION = 1, DEFENDER_POSITION = 2, MIDFIELDER_POSITION = 3, FORWARD_POSITION = 4";

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
                player.setPosition(position_index);
                System.out.println("Updated Successfully!");

                break;
            }
        }
        if (player == null) {
            System.out.println("Player not found!");
        }
    }

    public void displayTeam() {
        String[] positions = { "Goal keeper", "Defender", "Midfielder", "Forward" };
        String[] players = new String[positions.length];
        Arrays.fill(players, "");

        Enumeration<Player> allPlayers = getAllPlayers();
        Player player;

        while (allPlayers.hasMoreElements()) {
            player = allPlayers.nextElement();
            players[player.getPosition() - 1] += "\n" + player.getPlayerID() + ", " + player.getName();
        }

        System.out.println("Football Team " + getName() + " (" + getTeamID() + ")");

        for (int i = 0; i < positions.length; i++) {
            if (players[i].isEmpty()) {
                players[i] = "NIL";
            }
            System.out.println("\n" + positions[i] + ":" + players[i]);
        }
    }
}