package STMS;

public class TeamManager {
    private Team currentTeam;

    public TeamManager() {
        currentTeam = null;
    }

    public void setCurrentTeam(Team team) {
        currentTeam = team;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }
}