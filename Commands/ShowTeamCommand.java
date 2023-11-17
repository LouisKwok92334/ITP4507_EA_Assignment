package Commands;

import STMS.*;
import java.util.*;

public class ShowTeamCommand implements Command {
    private final Vector<Team> currentTeam;

    public ShowTeamCommand(Vector<Team> currentTeam) {
        this.currentTeam = currentTeam;
    }

    @Override
    public void execute() {
        currentTeam.get(0).displayTeam();
    }

    @Override
    public void undo() {}

    @Override
    public void redo() {}
}
