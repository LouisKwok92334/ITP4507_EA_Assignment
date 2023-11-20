import Commands.*;
import CommandsFactory.*;
import STMS.*;

import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);

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
        commandFactories.put("c", new CreateTeamCommandFactory(sc, teams, currentTeam, commands, redos));
        commandFactories.put("g", new SetCurrentTeamCommandFactory(sc, teams, currentTeam, commands, redos));
        commandFactories.put("a", new AddPlayerCommandFactory(sc, currentTeam, commands, redos));
        commandFactories.put("d", new DeletePlayerCommandFactory(sc, currentTeam, commands, redos));
        //commandFactories.put("l", new ListUndosAndRedosCommandFactory(commands, redos));
        commandFactories.put("s", new ShowTeamCommandFactory(currentTeam));
        commandFactories.put("p", new DisplayAllTeamsFactory(teams));
        //m, t

        String command;
        Command com;

        while (true) {
            System.out.println("Sport Teams Management System (STMS)");
            System.out.println("""
                    c = create team, g = set current team, a = add player, m = modify player’s
                    position, d = delete player, s = show team, p = display all teams, t = change
                    team’s name, u = undo, r = redo, l = list undo/redo, x = exit system""");
            if (!currentTeam.isEmpty()) {
                System.out.println("The current team is " + currentTeam.get(0).getTeamID() + " " + currentTeam.get(0).getName() + ".");
            }
            System.out.print("Please enter command [ c | g | a | m | d | s | p | t | u | r | l | x ] :-");
            command = sc.next();

            try {
                com = commandFactories.get(command).createCommand();
                com.execute();
            } catch (Exception e) {
                System.out.println("Invalid input, please try again!");
            }
        }
    }
}
