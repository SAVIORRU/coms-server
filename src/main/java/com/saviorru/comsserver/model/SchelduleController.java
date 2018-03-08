package com.saviorru.comsserver.model;

import java.time.LocalDate;
import java.util.List;

public interface SchelduleController {
    public void createScheldule(List<Player> players);
    public void updateScheldule(List<Player> players);
    public void setMatchResult(Match match);
    public List<Match> getMatchesByPlayer(Player player, StateMatch state);
    public List<Match> getMatchesByDate(LocalDate date);
    public List<Match> getMatchesByLocation(Location location);
}
