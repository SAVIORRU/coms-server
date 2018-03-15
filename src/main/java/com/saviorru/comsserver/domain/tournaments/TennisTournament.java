package com.saviorru.comsserver.domain.tournaments;

import com.saviorru.comsserver.domain.*;

import java.time.LocalDateTime;
import java.util.List;

public class TennisTournament implements Tournament {

    private LocationDispatcher locationDispatcher;
    private Schedule schedule;
    private LocalDateTime startDate, endDate;
    private PlayerDispatcher playerDispatcher;
    private DateDispatcher dateDispatcher;
    private boolean isStart;
    private SchemeType schemeType;
    private Scheme scheme;
    private String tournamentName;
    private Player champion;
    private ScheduleGenerator scheduleGenerator;

    public TennisTournament(PlayerDispatcher playerDispatcher, LocationDispatcher locationDispatcher, DateDispatcher dateDispatcher, Schedule schedule, String tournamentName, SchemeType schemeType) throws Exception {
        if (playerDispatcher == null || locationDispatcher == null || dateDispatcher == null || schedule == null || tournamentName == null)
            throw new NullPointerException();
        if (playerDispatcher.getAllPlayers().isEmpty() || locationDispatcher.getAllLocations().isEmpty() || tournamentName.isEmpty())
            throw new Exception("Empty parameter");
        this.playerDispatcher = playerDispatcher;
        this.schedule = schedule;
        this.locationDispatcher = locationDispatcher;
        this.dateDispatcher = dateDispatcher;
        this.isStart = false;
        this.schemeType = schemeType;
        this.tournamentName = tournamentName;
        generationSchedule(schemeType);
    }

    private void generationSchedule(SchemeType schemeType) throws Exception {
        if (schemeType == SchemeType.ROUND) {
            generate(new RoundScheme(this.playerDispatcher.getAllPlayers().size()));
        }
        if (schemeType == SchemeType.OLYMPIC) {
            generate(new OlympicScheme(this.playerDispatcher.getAllPlayers().size()));
        }
    }

    private void generate(Scheme scheme) throws Exception {
        this.scheme = scheme;
        this.scheduleGenerator = new ScheduleGeneratorImpl(this.playerDispatcher, this.locationDispatcher, this.dateDispatcher, scheme);
        this.schedule = this.scheduleGenerator.generateSchedule();
    }

    @Override
    public String getName() {
        return this.tournamentName;
    }

    @Override
    public List<Player> getPlayers() {
        return this.playerDispatcher.getAllPlayers();
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
            if (schedule.getMatchesByState(MatchState.PLAYED).size() == 0) throw new Exception("Matches didn't played");
            //this.champion = schedule.getMatchesByState(MatchState.PLAYED).get(schedule.getAllMatches().size() - 1).getWinner();
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
        this.schedule = this.scheduleGenerator.updateSchedule(match, this.schedule);
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
    public boolean isStart() {
        return this.isStart;
    }

    @Override
    public Player getChampion() throws Exception {
        if (champion == null) throw new Exception("Tournament is not finished");
        return champion;
    }

}