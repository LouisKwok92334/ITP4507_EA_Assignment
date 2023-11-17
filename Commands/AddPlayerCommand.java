package Commands;

import STMS.*;
import STMSFactory.*;
import java.util.*;

public class AddPlayerCommand implements Command {
    private final Scanner sc;
    private final Vector<Team> currentTeam;
    private Player player;

    public AddPlayerCommand(Scanner sc, Vector<Team> currentTeam) {
        this.sc = sc;
        this.currentTeam = currentTeam;
        this.player = null;
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

        System.out.print("Position (1 = attacker | 2 = defender ):- ");
        int position = sc.nextInt();

        player = PlayerFactory.createPlayer(id, name, position);
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
}
