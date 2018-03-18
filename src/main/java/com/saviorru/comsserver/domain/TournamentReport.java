package com.saviorru.comsserver.domain;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class TournamentReport {
    private List<Player> contestersList;
    private List<Pair<Player, Integer>> playerScoresTable;
    private List<Match> matchesHistory;
    private SchemeType schemeType;
    private List<Player> prizeWinners;
    private LocalDateTime startDate, endDate, reportDate;
    private String tournamentName;


    public TournamentReport(Tournament tournament) throws Exception {
        if (tournament == null) throw new NullPointerException();
        this.contestersList = tournament.getPlayers();
        this.matchesHistory = tournament.getSchedule().getAllMatches();
        this.schemeType = tournament.getSchemeType();
        this.startDate = tournament.getStartDate();
        this.endDate = tournament.getEndDate();
        this.reportDate = LocalDateTime.now();
        this.tournamentName = tournament.getName();
        this.prizeWinners = new ArrayList<>();
        this.prizeWinners.add(tournament.getFirstPlacePrizer());
        this.prizeWinners.add(tournament.getSecondPlacePrizer());
        this.prizeWinners.add(tournament.getThirdPlacePrizer());
        this.playerScoresTable = calcScores(this.contestersList, this.matchesHistory);

    }



    private List<Pair<Player, Integer>> calcScores( List<Player> contestersList, List<Match> matchesHistory) throws Exception
    {
        Map<Player, Integer> playerScores = new HashMap<>();
        for (Match match : matchesHistory)
        {
            if (!(playerScores.containsKey(match.getFirstSide())))
                playerScores.put(match.getFirstSide(), 0);
            if (!(playerScores.containsKey(match.getSecondSide())))
                playerScores.put(match.getSecondSide(), 0);
        }
        for (Match match : matchesHistory) {
            Player winner = match.getWinner();
            Integer score = playerScores.get(winner);
            score +=  1;
            playerScores.replace(winner, score);
        }
        List<Pair<Player, Integer>> result = new ArrayList<>();
        Stream<Map.Entry<Player,Integer>> st = playerScores.entrySet().stream();

        st.sorted(Comparator.comparing(e -> e.getValue()))
                .forEach(e ->result.add(new Pair<Player, Integer> (e.getKey(), e.getValue())));
        Collections.reverse(result);
        return result;
    }

    public List<Player> getContestersList() {
        return contestersList;
    }

    public List<Pair<Player, Integer>> getPlayerScoresTable() {
        return playerScoresTable;
    }

    public List<Match> getMatchesHistory() {
        return matchesHistory;
    }

    public SchemeType getSchemeType() {
        return schemeType;
    }

    public List<Player> getPrizeWinners() {
        return prizeWinners;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public String getTournamentName() {
        return tournamentName;
    }
}
