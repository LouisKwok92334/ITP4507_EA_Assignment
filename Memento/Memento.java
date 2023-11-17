package Memento;

import STMS.Team;

public abstract class Memento {
    private Team mTeam;


    public abstract void restore();
}