package STMSFactory;

import STMS.*;
import java.util.*;

public abstract class TeamFactory {
    public abstract Team createTeam(String teamID, String teamName);
}