import Commands.*;
import java.util.*;

public class STMS {
    public static Scanner sc = new Scanner(System.in);
    public static Command com;

    public static void main(String[] args) {
        Vector shapes = new Vector(); // a vector to store all shapes
        Stack commands = new Stack(); // a stack to store the executed commands (for undo)

        String command;
        while (true) {
            System.out.println("Please enter command [ c | g | a | m | d | s | p | t | u | r | l | x ]");
            command = sc.next();

            switch (command) {
                case "x":
                    com = new ExitCommand();
                    com.execute();
                    break;
            }
        }
    }
}
