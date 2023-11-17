package STMSFactory;

import STMS.*;

public class VolleyballTeamFactory extends TeamFactory {
    @Override
    public Team createTeam(String teamID, String teamName) {
        System.out.println("Volleyball team is created. ");

        Team team = new VolleyballTeam(teamID);
        team.setName(teamName);
        return team;
    }
}