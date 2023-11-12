import Commands.*;
import CommandsFactory.*;
import STMS.*;

import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Vector<Team> teams = new Vector<>(); // a vector to store all Team
        Stack<Command> commands = new Stack<>(); // a stack to store the executed commands (for undo)
        Stack redos = new Stack<>(); // a stack to store the commands which were undid (for redo)
        TeamManager teamManager = new TeamManager();

        String command;

        // use a HashMap to store the command factories
        HashMap<String, CommandFactory> commandFactories = new HashMap<String, CommandFactory>();
        commandFactories.put("x", new ExitCommandFactory());
        commandFactories.put("u", new UndoCommandFactory(commands, redos));
        commandFactories.put("r", new RedoCommandFactory(commands, redos));
        commandFactories.put("c", new CreateTeamCommandFactory(sc, teams, commands, teamManager));
        //commandFactories.put("g", new SetCurrentTeamCommandFactory(sc, teams, commands));
        commandFactories.put("p", new DisplayAllTeamsFactory(teams));

        Command com;
        while (true) {
            System.out.println("Sport Teams Management System (STMS)");
            if (teamManager.getCurrentTeam() != null) {
                System.out.println("The current team is " + teamManager.getCurrentTeam().getTeamID() + " " + teamManager.getCurrentTeam().getName()+ "." );
            }
            System.out.println("""
                    c = create team, g = set current team, a = add player, m = modify player’s
                    position, d = delete player, s = show team, p = display all teams, t = change
                    team’s name, u = undo, r = redo, l = list undo/redo, x = exit system""");
            System.out.print("Please enter command [ c | g | a | m | d | s | p | t | u | r | l | x ] :-");
            command = sc.next();
            com = commandFactories.get(command).createCommand();
            com.execute();
        }
    }
}
