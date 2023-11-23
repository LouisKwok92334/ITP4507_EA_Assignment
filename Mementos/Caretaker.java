package Mementos;

import STMS.*;
import java.util.*;

public class Caretaker {
    // Stacks to track the history of operations for undo and redo functionality
    private final Stack<Memento> undoStack;
    private final Stack<Memento> redoStack;

    public Caretaker(){
        undoStack = new Stack<Memento>(); // Initializes the stack for undos
        redoStack = new Stack<Memento>(); // Initializes the stack for redos
    }

    // Saves the state of a player for potential undo operation
    public void savePlayerPosition(Player player) {
        Memento memento = new PlayerMemento(player);
        undoStack.push(memento); // Pushes the created memento onto the undo stack
    }

    // Saves the state of a player for potential redo operation
    public void savePlayerPosition_redo(Player player) {
        Memento memento = new PlayerMemento(player);
        redoStack.push(memento); // Pushes the created memento onto the redo stack
    }

    // Saves the state of a team for potential undo operation
    public void saveTeam(Vector<Team> team, String teamID, String name, Enumeration<Player> players) {
        Memento memento = new TeamMemento(team, teamID, name, players);
        undoStack.push(memento); // Pushes the created memento onto the undo stack
    }

    // Saves the state of a team for potential redo operation
    public void saveTeam_redo(Vector<Team> team, String teamID, String name, Enumeration<Player> players) {
        Memento memento = new TeamMemento(team, teamID, name, players);
        redoStack.push(memento); // Pushes the created memento onto the redo stack
    }

    // Undoes the last action by popping a memento from the undo stack and restoring it
    public void undo(){
        Memento m = undoStack.pop();
        m.restore(); // Restores the state of the memento
    }

    // Redoes the last action by popping a memento from the redo stack and restoring it
    public void redo() {
        Memento m = redoStack.pop();
        m.restore(); // Restores the state of the memento
    }

    // Returns the size of the undo stack (number of actions that can be undid)
    public int getUndoListSize() {
        return undoStack.size();
    }

    // Returns the size of the redo stack (number of actions that can be redone)
    public int getRedoListSize() {
        return redoStack.size();
    }

    /// Clears the redo stack, typically done after a new action is taken
    public void clearRedoStack() {
        redoStack.clear();
    }
}