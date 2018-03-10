package com.saviorru.comsserver.model.tournaments;

import com.saviorru.comsserver.model.*;
import com.saviorru.comsserver.model.generators.OlympicScheduleGenerator;
import com.saviorru.comsserver.model.generators.RoundScheduleGenerator;

import java.time.LocalDateTime;
import java.util.List;

public class TennisTournament implements Tournament {

    private LocationDispatcher locationDispatcher;
    private Schedule schedule;
    private LocalDateTime startDate, endDate;
    private List<Player> players;
    private DateDispatcher dateDispatcher;
    private boolean isStart;
    private SchemeType schemeType;
    private ScheduleGenerator scheduleGenerator;
    private String tournamentName;
    private Player champion;

    public TennisTournament(List<Player> players, List<Location> locations, SchemeType schemeType, LocalDateTime startDate, String tournamentName) throws Exception {
        if (players == null || locations == null || schemeType == null || startDate == null || tournamentName == null)
            throw new NullPointerException();
        if (players.isEmpty() || locations.isEmpty() || tournamentName.isEmpty())
            throw new Exception("Empty parameter");
        this.players = players;
        this.schedule = new ScheduleImpl();
        this.locationDispatcher = new LocationDispatcher();
        this.locationDispatcher.addAllLocation(locations);
        this.startDate = startDate;
        this.dateDispatcher = new DateDispatcher(startDate, 10, 18, 12);
        this.isStart = false;
        this.schemeType = schemeType;
        this.tournamentName = tournamentName;
        generationSchedule(schemeType);
    }

    private void generationSchedule(SchemeType schemeType) throws Exception {
        if (schemeType == SchemeType.ROUND) {
            scheduleGenerator = new RoundScheduleGenerator();
            generate(scheduleGenerator);
        }
        if (schemeType == SchemeType.OLYMPIC) {
            scheduleGenerator = new OlympicScheduleGenerator();
            generate(scheduleGenerator);
        }
    }

    private void generate(ScheduleGenerator scheduleGenerator) throws Exception {
        schedule.addMatches(scheduleGenerator.generateSchedule(players, locationDispatcher, dateDispatcher));
    }

    @Override
    public String getName() {
        return this.tournamentName;
    }

    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    @Override
    public Schedule getSchedule() throws Exception {
        if (!isStart) throw new Exception("Tournament is not started");
        return this.schedule;
    }

    @Override
    public List<Location> getLocations() throws Exception {
        if (isStart) throw new Exception("Tournament is not started");
        return locationDispatcher.getAllLocations();
    }

    @Override
    public SchemeType getSchemeType() {
        return this.schemeType;
    }

    @Override
    public void start() throws Exception {
        if (!this.isStart) {
            this.isStart = true;
            return;
        }
        throw new Exception("Tournament is started");
    }

    @Override
    public void finish() throws Exception {
        if (this.isStart) {
            this.isStart = false;
            if(schedule.getMatchesByState(MatchState.PLAYED).size() == 0) throw new Exception("Matches didn't played");
            this.champion = schedule.getMatchesByState(MatchState.PLAYED).get(schedule.getAllMatches().size() - 1).getWinner();
        }
        throw new Exception("Tournament is not started");
    }

    @Override
    public Match getNextMatch() throws Exception {
        if (!(isStart)) throw new Exception("Tournament is not started");
        List<Match> matchesByState = schedule.getMatchesByState(MatchState.NOTPLAYED);
        if (matchesByState.size() == 0) return null;
        Match nextMatch = matchesByState.get(0);
        for (int i = 0; i < matchesByState.size() - 1; i++) {
            if (matchesByState.get(i).getDate().isBefore(matchesByState.get(i + 1).getDate())) {
                nextMatch = matchesByState.get(i);
            }
        }
        return nextMatch;
    }

    @Override
    public void finishMatch(Match match, Points points) throws Exception {
        if (match == null || points == null) throw new NullPointerException();
        if (!(isStart)) throw new Exception("Tournament is not started");
        match.setPoints(points.getPointsFirstSide(), points.getPointsSecondSide());
        match.setMatchState(MatchState.PLAYED);
        this.locationDispatcher.freeLocation(match.getLocation());
        List<Match> updateMatches = this.scheduleGenerator.updateSchedule(this.schedule.getAllMatches());
        if (updateMatches.size() > 0) {
            this.schedule.addMatches(updateMatches);
        }
    }

    @Override
    public void finishMatches(List<Match> matches, List<Points> points) throws Exception {
        if (!(isStart)) throw new Exception("Tournament is not started");
        if (matches == null || points == null) throw new NullPointerException();
        for (int i = 0; i < matches.size(); i++) {
            finishMatch(matches.get(i), points.get(i));
        }
    }

    @Override
    public Meet getNextMeet() throws Exception {
        if (!(isStart)) throw new Exception("Tournament is not started");
        if (schedule.getMatchesByState(MatchState.NOTPLAYED).size() == 0) return null;
        return new Meet(schedule.getMatchesByState(MatchState.NOTPLAYED).get(0).getFirstSide(), schedule.getMatchesByState(MatchState.NOTPLAYED).get(0).getSecondSide());
    }

    @Override
    public boolean isStart() {
        return this.isStart;
    }

    @Override
    public Player getChampion() throws Exception {
        if (champion == null) throw new Exception("Tournament is not finished");
        return champion;
    }

}
