package Commands;

import STMS.*;
import STMSFactory.*;
import java.util.*;

public class AddPlayerCommand implements Command {
    private final Scanner sc;
    private final Vector<Team> currentTeam;
    private final PlayerFactory playerFactory;
    private final Map<Integer, String> positionDescriptions = new HashMap<>();
    private Player player;
    private Team team;
    private int position;

    public AddPlayerCommand(Scanner sc, Vector<Team> currentTeam, PlayerFactory playerFactory) {
        this.sc = sc;
        this.currentTeam = currentTeam;
        this.playerFactory = playerFactory;
        this.player = null;
        this.team = null;
        this.position = 0;
    }

    @Override
    public void execute() {
        // Consume the leftover newline character from previous input reading
        sc.nextLine();

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

        if (currentTeam.firstElement() instanceof FootballTeam) {
            System.out.print("Position (1 = goal keeper | 2 = defender | 3 = midfielder | 4 = forward ):- ");
            positionDescriptions.put(1, "goal keeper");
            positionDescriptions.put(2, "defender");
            positionDescriptions.put(3, "midfielder");
            positionDescriptions.put(4, "forward");
        } else if (currentTeam.firstElement() instanceof VolleyballTeam) {
            System.out.print("Position (1 = attacker | 2 = defender ):- ");
            positionDescriptions.put(1, "attacker");
            positionDescriptions.put(2, "defender");
        }
        position = sc.nextInt();

        player = playerFactory.createPlayer(id, name, position);
        team = currentTeam.get(0);
        currentTeam.get(0).addPlayer(player);
        System.out.println("Player is added.");
    }

    @Override
    public void undo() {
        // Update currentTeam to point to the saved team
        if (!currentTeam.isEmpty()) {
            System.out.println("Current team is changed to " + currentTeam.firstElement().getTeamID() + ".");
            currentTeam.set(0, team);
        }

        team.remove(player);  // Remove the player from the saved team
    }

    @Override
    public void redo() {
        // Update currentTeam to point to the saved team
        if (!currentTeam.isEmpty()) {
            System.out.println("Current team is changed to " + currentTeam.firstElement().getTeamID() + ".");
            currentTeam.set(0, team);
        }

        team.addPlayer(player);
    }

    public String toString() {
        String positionDescription = positionDescriptions.get(position);
        return "Add player, " + player.getPlayerID() + ", " + player.getName() + ", " + positionDescription;
    }
}
