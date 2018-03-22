package com.saviorru.comsserver.domain.tournament;

import com.saviorru.comsserver.domain.MatchState;
import com.saviorru.comsserver.domain.dispatcher.DateDispatcher;
import com.saviorru.comsserver.domain.dispatcher.LocationDispatcher;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.model.*;
import com.saviorru.comsserver.domain.schedule.Schedule;
import com.saviorru.comsserver.domain.schedule.ScheduleGenerator;
import com.saviorru.comsserver.domain.schedule.ScheduleGeneratorImpl;
import com.saviorru.comsserver.domain.schematictype.*;
import com.saviorru.comsserver.domain.winnerindetifier.WinnerIdentifier;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TennisTournament implements Tournament {


    private LocationDispatcher locationDispatcher;
    private Schedule schedule;
    private PlayerDispatcher playerDispatcher;
    private DateDispatcher dateDispatcher;
    private boolean isStart;
    private List<PrizePlace> prizePlaces;
    private ScheduleGenerator scheduleGenerator;
    private WinnerIdentifier winnerIdentifier;
    private TournamentSettings tournamentSettings;


    public TennisTournament(PlayerDispatcher playerDispatcher, LocationDispatcher locationDispatcher, TournamentSettings tournamentSettings, Schedule schedule) throws Exception {
        if (playerDispatcher == null || locationDispatcher == null ||  schedule == null || tournamentSettings == null)
            throw new NullPointerException();
        if (playerDispatcher.getAllPlayers().isEmpty() || locationDispatcher.getAllLocations().isEmpty())
            throw new Exception("Empty parameter");
        this.playerDispatcher = playerDispatcher;
        this.schedule = schedule;
        this.locationDispatcher = locationDispatcher;
        this.dateDispatcher = tournamentSettings.getDateDispatcher();
        this.isStart = false;
        this.tournamentSettings = tournamentSettings;
        this.prizePlaces = new ArrayList<>();
        generationSchedule();
    }

    private TennisTournament(TennisTournament target) throws Exception {
        if (target != null) {
            this.playerDispatcher = target.getPlayerDispatcher();
            this.schedule = target.getScheduleObject();
            this.locationDispatcher = target.getLocationDispatcher();
            this.dateDispatcher = target.getDateDispatcher();
            this.isStart = target.isStart();
            this.winnerIdentifier = target.getWinnerIdentifier();
            this.prizePlaces = target.getPrizePlaces();
            this.scheduleGenerator = target.getScheduleGenerator();
            this.tournamentSettings = target.getTournamentSettings();
        }
    }

    private void generationSchedule() throws Exception {
            this.winnerIdentifier = tournamentSettings.getWinnerIdentifier();
            generate(tournamentSettings.getScheme(playerDispatcher.getAllPlayers().size()));
    }

    private void generate(Scheme scheme) throws Exception {
        this.scheduleGenerator = new ScheduleGeneratorImpl(this.playerDispatcher, this.locationDispatcher, this.dateDispatcher, scheme);
        this.schedule = this.scheduleGenerator.generateSchedule();
    }

    @Override
    public String getName() {
        return this.tournamentSettings.getTournamentName();
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
        return this.tournamentSettings.getSchemeType();
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
            if (schedule.getMatchesByState(MatchState.PLAYED).size() == 0) throw new Exception("Matches didn't played");
            if (this.schedule.getAllMatches().size() != this.scheduleGenerator.getScheme().getMaxPairCount())
                throw new Exception("All pair are not not played yet");
            List<Player> winners = this.winnerIdentifier.identifyWinners(schedule.getAllMatches());
            for (int i = 0; i < this.tournamentSettings.getPrizePlacesCount(); i++) {
                this.prizePlaces.add(new PrizePlaceThePlayer(winners.get(i), i + 1));
            }
            dateDispatcher.setEndDate(LocalDateTime.now());
        } else
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
    public void finishMatch(Match match, Score score) throws Exception {
        if (match == null || score == null) throw new NullPointerException();
        if (!(isStart)) throw new Exception("Tournament is not started");
        match.setPoints(score.getPointsFirstSide(), score.getPointsSecondSide());
        match.setMatchState(MatchState.PLAYED);
        this.locationDispatcher.freeLocation(match.getLocation());
        this.schedule = this.scheduleGenerator.updateSchedule(match, this.schedule);
    }

    @Override
    public void finishMatches(List<Match> matches, List<Score> points) throws Exception {
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
    public Player getThePrizePlace(int prizePlace) throws Exception {
        if (prizePlace < 0 || prizePlace > tournamentSettings.getPrizePlacesCount())
            throw new Exception("Not a correct prize-winning place");
        if (prizePlaces != null) {
            for (PrizePlace thePrizePlace : prizePlaces) {
                if (thePrizePlace.getPrizePlace() == prizePlace) {
                    return thePrizePlace.getPlayer();
                }
            }
        }
        return null;
    }

    @Override
    public LocalDateTime getStartDate() {

        return dateDispatcher.getStartDate();
    }

    @Override
    public LocalDateTime getEndDate() {
        return dateDispatcher.getEndDate();
    }

    @Override
    public PlayerGrid getPlayerGrid() throws Exception {
        return this.scheduleGenerator.getScheme().getPlayerGrid();
    }

    @Override
    public Scheme getScheme() {
        return this.scheduleGenerator.getScheme();
    }


    @Override
    public TournamentReport getTournamentReport() throws Exception {
        return new TournamentReport(this);
    }

    @Override
    public Tournament clone() {
        try {
            return new TennisTournament(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private LocationDispatcher getLocationDispatcher() {
        return locationDispatcher;
    }


    private PlayerDispatcher getPlayerDispatcher() {
        return playerDispatcher;
    }


    private DateDispatcher getDateDispatcher() {
        return dateDispatcher;
    }


    private ScheduleGenerator getScheduleGenerator() {
        return scheduleGenerator;
    }


    private WinnerIdentifier getWinnerIdentifier() {
        return winnerIdentifier;
    }

    private Schedule getScheduleObject(){
        return schedule;
    }

    private List<PrizePlace> getPrizePlaces() {
        return prizePlaces;
    }

    private TournamentSettings getTournamentSettings() {
        return tournamentSettings;
    }
}
