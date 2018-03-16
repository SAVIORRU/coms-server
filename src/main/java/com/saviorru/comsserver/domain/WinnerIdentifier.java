package com.saviorru.comsserver.domain;

import java.util.List;

public interface WinnerIdentifier {
    public List<Player> identifyWinner (List<Match> finishedMatches) throws Exception;
}
