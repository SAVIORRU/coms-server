package com.saviorru.comsserver.model;

import java.util.List;

public interface Scheldule {
    public List<Match> getAllMatches();
    public List<Match> getMatchesByState(StateMatch state);
    public void finishMatch(Match match);
}
