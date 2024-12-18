import Commands.*;
import CommandsFactory.*;
import Mementos.*;
import STMS.*;

import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in); // Scanner object for reading user input from the console
    public static Caretaker caretaker = new Caretaker();

    public static void main(String[] args) {
        Vector<Team> teams = new Vector<>(); // a vector to store all Team
        Vector<Team> currentTeam = new Vector<>(); // a vector to store current Team
        Stack<Command> commands = new Stack<>(); // a stack to store the executed commands (for undo)
        Stack<Command> redos = new Stack<>(); // a stack to store the commands which were undid (for redo)

        // use a HashMap to store the command factories
        HashMap<String, CommandFactory> commandFactories = new HashMap<String, CommandFactory>();
        commandFactories.put("x", new ExitCommandFactory());
        commandFactories.put("u", new UndoCommandFactory(commands, redos));
        commandFactories.put("r", new RedoCommandFactory(commands, redos));
        commandFactories.put("l", new ListCommandFactory(commands, redos));
        commandFactories.put("s", new ShowTeamCommandFactory(currentTeam));
        commandFactories.put("p", new DisplayAllTeamsFactory(teams));
        commandFactories.put("g", new SetCurrentTeamCommandFactory(sc, teams, currentTeam));
        commandFactories.put("a", new AddPlayerCommandFactory(sc, currentTeam, commands, redos));
        commandFactories.put("c", new CreateTeamCommandFactory(sc, teams, currentTeam, commands, redos));
        commandFactories.put("m", new ModifyPlayerPositionCommandFactory(sc, currentTeam, commands, redos, caretaker));
        commandFactories.put("t", new ChangeTeamNameCommandFactory(sc, currentTeam, commands, redos, caretaker));
        commandFactories.put("d", new DeletePlayerCommandFactory(sc, currentTeam, commands, redos, caretaker));

        String command; // String to hold the user's command input
        Command com; // Command object that will be created and executed

        while (true) { // Main loop to keep the application running
            // Print out the system prompt
            System.out.println("Sport Teams Management System (STMS)");
            System.out.println("""
                    c = create team, g = set current team, a = add player, m = modify player’s
                    position, d = delete player, s = show team, p = display all teams, t = change
                    team’s name, u = undo, r = redo, l = list undo/redo, x = exit system""");

            if (!currentTeam.isEmpty()) { // Display the current team if it exists
                System.out.println("The current team is " + currentTeam.get(0).getTeamID() + " " + currentTeam.get(0).getName() + ".");
            }

            // Prompt for user input and read it
            System.out.print("Please enter command [ c | g | a | m | d | s | p | t | u | r | l | x ] :-");
            command = sc.next();

            // A set of commands that are allowed without an existing current team
            Set<String> commandsNotRequiringTeam = new HashSet<>(Arrays.asList("c", "x", "r", "l", "p", "u"));

            // Check if a current team needs to be set before proceeding with certain operations
            if (currentTeam.isEmpty() && !commandsNotRequiringTeam.contains(command)) {
                System.out.println("Please create a team first!");
                continue; // Skip the rest of the loop iteration and prompt again
            }

            try {
                // Create and execute the command corresponding to the user's input
                com = commandFactories.get(command).createCommand();
                com.execute();
            } catch (Exception e) {
                // Handle any exceptions by notifying the user of an invalid input
                System.out.println("Invalid input, please try again!");
            }
        }
    }
}
