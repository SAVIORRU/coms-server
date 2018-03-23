package com.saviorru.comsserver.domain.tournament;

import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.schedule.Schedule;
import com.saviorru.comsserver.domain.schematictype.PlayerGrid;
import com.saviorru.comsserver.domain.schematictype.Scheme;
import com.saviorru.comsserver.domain.schematictype.SchemeType;
import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.model.Score;
import com.saviorru.comsserver.domain.model.Player;

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
    public PlayerGrid getPlayerGrid() throws Exception;
    public Scheme getScheme();
    public TournamentSettings getTournamentSettings();
    public Tournament clone();
    TournamentReport getTournamentReport() throws Exception;
}
