package Mementos;

import STMS.*;

import java.util.Vector;

public class TeamMemento extends Memento {
    private final Vector<Team> team;
    private final String name;

    public TeamMemento(Vector<Team> team) {
        this.team = team;
        this.name = team.firstElement().getName();
    }

    @Override
    public void restore() {
        team.firstElement().setName(name);
    }
}
