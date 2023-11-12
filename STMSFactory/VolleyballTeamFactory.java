package STMSFactory;

import STMS.*;

public class VolleyballTeamFactory extends TeamFactory {
    @Override
    public Team createTeam(String teamID, String teamName) {
        VolleyballTeam team = new VolleyballTeam(teamID);
        team.setName(teamName);
        return team;
    }
}