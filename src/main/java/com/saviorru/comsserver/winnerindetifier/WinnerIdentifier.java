package com.saviorru.comsserver.winnerindetifier;

import com.saviorru.comsserver.model.Match;
import com.saviorru.comsserver.model.Player;

import java.util.List;

public interface WinnerIdentifier {
    public List<Player> identifyWinners (List<Match> finishedMatches) throws Exception;
}
