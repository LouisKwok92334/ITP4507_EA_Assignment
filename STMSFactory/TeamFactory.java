package STMSFactory;

import STMS.*;

public abstract class TeamFactory {
    public abstract Team createTeam(String teamID, String teamName);
}