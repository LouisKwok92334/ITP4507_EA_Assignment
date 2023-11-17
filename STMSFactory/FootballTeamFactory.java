package STMSFactory;

import STMS.*;

public class FootballTeamFactory extends TeamFactory {
    @Override
    public Team createTeam(String teamID, String teamName) {
        System.out.println("Football team is created. ");

        Team team = new FootballTeam(teamID);
        team.setName(teamName);
        return team;
    }
}