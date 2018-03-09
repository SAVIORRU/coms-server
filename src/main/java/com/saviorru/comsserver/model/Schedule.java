package com.saviorru.comsserver.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface Schedule {
    public List<Match> getAllMatches();
    public void addMatch(Match match) throws Exception;
    public List<Match> getMatchesByState(MatchState state) throws Exception;
    public List<Match> getMatchesByPlayer(Player player);
    public List<Match> getMatchesByDate(LocalDateTime date);
    public List<Match> getMatchesByLocation(Location location);
    public void finishMatch(Match match, Points result) throws Exception;
}
