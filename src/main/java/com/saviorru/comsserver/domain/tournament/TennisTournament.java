package com.saviorru.comsserver.domain.tournament;

import com.saviorru.comsserver.domain.*;
import com.saviorru.comsserver.domain.dispatcher.DateDispatcher;
import com.saviorru.comsserver.domain.dispatcher.LocationDispatcher;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.schedule.Schedule;
import com.saviorru.comsserver.domain.schedule.ScheduleGenerator;
import com.saviorru.comsserver.domain.schedule.ScheduleGeneratorImpl;
import com.saviorru.comsserver.domain.schematictype.OlympicScheme;
import com.saviorru.comsserver.domain.model.*;
import com.saviorru.comsserver.domain.schematictype.*;
import com.saviorru.comsserver.domain.winnerindetifier.OlympicWinnerIndentifier;
import com.saviorru.comsserver.domain.winnerindetifier.RoundWinnerIdentifier;
import com.saviorru.comsserver.domain.winnerindetifier.WinnerIdentifier;
import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TennisTournament implements Tournament {


    private LocationDispatcher locationDispatcher;
    private Schedule schedule;
    private PlayerDispatcher playerDispatcher;
    private DateDispatcher dateDispatcher;
    private boolean isStart;
    private SchemeType schemeType;
    private Scheme scheme;
    private String tournamentName;
    private List<PrizePlace> prizePlaces;
    private ScheduleGenerator scheduleGenerator;
    private WinnerIdentifier winnerIdentifier;

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
        this.prizePlaces = new ArrayList<>();
        generationSchedule(schemeType);
    }

    private TennisTournament(TennisTournament target) throws Exception {
        if (target != null) {
            this.playerDispatcher = target.getPlayerDispatcher();
            this.schedule = target.getScheduleObject();
            this.locationDispatcher = target.getLocationDispatcher();
            this.dateDispatcher = target.getDateDispatcher();
            this.isStart = target.isStart();
            this.schemeType = target.getSchemeType();
            this.tournamentName = target.getTournamentName();
            this.winnerIdentifier = target.getWinnerIdentifier();
            this.tournamentName = target.getTournamentName();
            this.prizePlaces = target.getPrizePlaces();
            this.scheduleGenerator = target.getScheduleGenerator();
        }
    }

    private void generationSchedule(SchemeType schemeType) throws Exception {
        if (schemeType == SchemeType.ROUND) {
            this.winnerIdentifier = new RoundWinnerIdentifier();
            generate(new RoundScheme(this.playerDispatcher.getAllPlayers().size()));
        }
        if (schemeType == SchemeType.OLYMPIC) {
            this.winnerIdentifier = new OlympicWinnerIndentifier();
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
            if (schedule.getMatchesByState(MatchState.PLAYED).size() == 0) throw new Exception("Matches didn't played");
            if (this.schedule.getAllMatches().size() != this.scheme.getMaxPairCount())
                throw new Exception("All pair are not not played yet");
            List<Player> winners = this.winnerIdentifier.identifyWinners(schedule.getAllMatches());
            for (int i = 0; i < winners.size(); i++) {
                if (i == 0) {
                    this.prizePlaces.add(new PrizePlaceThePlayer(winners.get(i), i + 1));
                }
                if (i == 1) {
                    this.prizePlaces.add(new PrizePlaceThePlayer(winners.get(i), i + 1));
                }
                if (i == 2) {
                    this.prizePlaces.add(new PrizePlaceThePlayer(winners.get(i), i + 1));
                }
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
        if (prizePlace < 0 || prizePlace > playerDispatcher.getAllPlayers().size())
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
        return dateDispatcher.getStartDate();
    }

    @Override
    public List<List<Integer>> getPlayerGrid() throws Exception {
        List<List<Integer>> playerGrid = new ArrayList<>();
        for (int i = 0; i < scheme.getToursCount(); i++) {
            playerGrid.add(new ArrayList<>());
            for (Pair<Integer, Integer> pair : scheme.getAllPairsInTour(i + 1)) {
                playerGrid.get(i).add(pair.getKey());
                playerGrid.get(i).add(pair.getValue());
            }
        }
        return playerGrid;
    }

    @Override
    public Scheme getScheme() {
        return scheme;
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


    private String getTournamentName() {
        return tournamentName;
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
}
