package com.saviorru.comsserver.model;

import java.time.LocalDate;
import java.util.List;

public interface Schedule {
    public List<Match> getAllMatches();
    public List<Match> getMatchesByState(StateMatch state);
    public List<Match> getMatchesByPlayer(Player player);
    public List<Match> getMatchesByDate(LocalDate date);
    public List<Match> getMatchesByLocation(Location location);
    public void finishMatch(Match match, Points result);
}
