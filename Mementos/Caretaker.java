package Mementos;

import STMS.*;
import java.util.*;

public class Caretaker {
    private final Stack<Memento> undoStack;
    private final Stack<Memento> redoStack;

    public Caretaker(){
        undoStack = new Stack<Memento>();
        redoStack = new Stack<Memento>();
    }

    public void savePlayerPosition(Player player) {
        Memento memento = new PlayerMemento(player);
        undoStack.push(memento);
    }

    public void savePlayerPosition_redo(Player player) {
        Memento memento = new PlayerMemento(player);
        redoStack.push(memento);
    }

    public void saveTeamName(Vector<Team> team) {
        Memento memento = new TeamMemento(team);
        undoStack.push(memento);
    }

    public void saveTeamName_redo(Vector<Team> team) {
        Memento memento = new TeamMemento(team);
        redoStack.push(memento);
    }

    public void undo(){
        Memento m = undoStack.pop();
        m.restore();
    }

    public void redo() {
        Memento m = redoStack.pop();
        m.restore();
    }

    public int getUndoListSize() {
        return undoStack.size();
    }

    public int getRedoListSize() {
        return redoStack.size();
    }

    public void clearRedoStack() {
        redoStack.clear();
    }
}