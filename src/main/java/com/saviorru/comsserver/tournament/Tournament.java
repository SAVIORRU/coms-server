package com.saviorru.comsserver.tournament;

import com.saviorru.comsserver.model.Match;
import com.saviorru.comsserver.schedule.Schedule;
import com.saviorru.comsserver.schematictype.Scheme;
import com.saviorru.comsserver.schematictype.SchemeType;
import com.saviorru.comsserver.model.Location;
import com.saviorru.comsserver.model.Score;
import com.saviorru.comsserver.model.Player;

import java.time.LocalDateTime;
import java.util.List;

public interface Tournament {
    public String getName();
    public List<Player> getPlayers();
    public Schedule getSchedule() throws Exception;
    public List<Location> getLocations() throws Exception;
    public SchemeType getSchemeType();
    public void start() throws Exception;
    public void finish() throws Exception;
    public Match getNextMatch() throws Exception;
    public void finishMatch(Match match,Score score) throws Exception;
    public void finishMatches(List<Match> matches,List<Score> points) throws Exception;
    public boolean isStart();
    public Player getThePrizePlace(int count) throws Exception;
    public LocalDateTime getStartDate();
    public LocalDateTime getEndDate();
    public List<List<Integer>> getPlayerGrid() throws Exception;
    public Scheme getScheme();
    public Tournament clone();
}
