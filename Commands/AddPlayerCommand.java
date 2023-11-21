package Commands;

import STMS.*;
import STMSFactory.*;
import java.util.*;

public class AddPlayerCommand implements Command {
    private final Scanner sc;
    private final Vector<Team> currentTeam;
    private final PlayerFactory playerFactory;
    private Player player;
    private int position;

    public AddPlayerCommand(Scanner sc, Vector<Team> currentTeam, PlayerFactory playerFactory) {
        this.sc = sc;
        this.currentTeam = currentTeam;
        this.playerFactory = playerFactory;
        this.player = null;
        this.position = 0;
    }

    @Override
    public void execute() {
        if (currentTeam.isEmpty()) {
            System.out.println("Please create a team first!");
            return;
        }

        // Consume the leftover newline character from previous input reading
        if (sc.hasNextLine()) {
            sc.nextLine();
        }

        System.out.print("Please input player information (id, name):- ");
        String playerInfo = sc.nextLine();
        String[] parts = playerInfo.split(",");

        // Error checking for input format
        if (parts.length < 2) {
            System.out.println("Invalid input format. Please provide id and name separated by a comma.");
            return;
        }

        String id = parts[0].trim();
        String name = parts[1].trim();

        if (currentTeam.firstElement() instanceof VolleyballTeam) {
            System.out.print("Position (1 = attacker | 2 = defender ):- ");
        } else if (currentTeam.firstElement() instanceof FootballTeam) {
            System.out.print("Position (1 = goal keeper | 2 = defender | 3 = midfielder | 4 = forward ):- ");
        }
        position = sc.nextInt();

        player = playerFactory.createPlayer(id, name, position);
        currentTeam.get(0).addPlayer(player);
        System.out.println("Player is added.");
    }

    @Override
    public void undo() {
        currentTeam.get(0).remove(player);
    }

    @Override
    public void redo() {
        currentTeam.get(0).addPlayer(player);
    }

    public String toString() {
        return "Add player, " + player.getPlayerID() + ", " + player.getName() + ", " + position;
    }
}
