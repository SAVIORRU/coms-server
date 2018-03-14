package com.saviorru.comsserver.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface Schedule {
    public List<Match> getAllMatches();
    public void addMatch(Match match) throws Exception;
    public void addMatches(List<Match> matches) throws Exception;
    public List<Match> getMatchesByState(MatchState state) throws Exception;
    public List<Match> getMatchesByPlayer(Player player);
    public List<Match> getMatchesByDate(LocalDateTime date);
    public List<Match> getMatchesByLocation(Location location);
}
