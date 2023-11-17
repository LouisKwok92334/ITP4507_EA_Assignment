package Memento;

import STMS.*;
import java.util.*;

public class Caretaker {
    private Stack<Memento> undoList;
    private Stack<Memento> redoList;

    public Caretaker(){
        undoList = new Stack<Memento>();
        redoList = new Stack<Memento>();
    }

    public void saveTeam(Team team){
        Memento m = new TeamMemento(team);
        undoList.push(m);
    }

    public void undo(){
        Memento m = undoList.pop();
        m.restore();
    }
}
