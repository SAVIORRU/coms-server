package com.saviorru.comsserver.domain;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface Tournament {
    public String getName();
    public List<Player> getPlayers();
    public Schedule getSchedule() throws Exception;
    public List<Location> getLocations() throws Exception;
    public SchemeType getSchemeType();
    public void start() throws Exception;
    public void finish() throws Exception;
    public Match getNextMatch() throws Exception;
    public void finishMatch(Match match,Points points) throws Exception;
    public void finishMatches(List<Match> matches,List<Points> points) throws Exception;
    public boolean isStart();
    public Player getFirstPlacePrizer() throws Exception;
    public Player getSecondPlacePrizer() throws Exception;
    public Player getThirdPlacePrizer() throws Exception;
    public LocalDateTime getStartDate();
    public LocalDateTime getEndDate();
    public List<List<Pair<Player, Player>>> getPlayerGrid() throws Exception;
    public Scheme getScheme();
}
