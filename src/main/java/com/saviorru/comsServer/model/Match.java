package com.saviorru.comsServer.model;

import java.util.Calendar;

enum MatchState {PLAYED, NOT_PLAYED}

public class Match {

    private Integer id;
    private Integer firstPlayerId;
    private Integer secondPlayerId;
    private Integer firstPlayerScore;
    private Integer secondPlayerScore;
    private Calendar matchDate;
    private MatchState state;
    private TennisTable table;

    public Match(int id) {
        this.id = id;
        this.state = MatchState.NOT_PLAYED;
        this.firstPlayerScore = 0;
        this.secondPlayerScore = 0;
    }

    public Match(int id, int firstPlayerId, int secondPlayerId, Calendar matchDate, TennisTable table) {
        this.id = id;
        this.firstPlayerId = firstPlayerId;
        this.secondPlayerId = secondPlayerId;
        this.matchDate = matchDate;
        this.table = table;
        this.state = MatchState.NOT_PLAYED;
        this.firstPlayerScore = 0;
        this.secondPlayerScore = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirstPlayerId() {
        return firstPlayerId;
    }

    public void setFirstPlayerId(int firstPlayerId) {
        this.firstPlayerId = firstPlayerId;
    }

    public int getSecondPlayerId() {
        return secondPlayerId;
    }

    public void setSecondPlayerId(int secondPlayerId) {
        this.secondPlayerId = secondPlayerId;
    }

    public int getFirstPlayerScore() {
        return firstPlayerScore;
    }

    public void setFirstPlayerScore(int firstPlayerScore) {
        this.firstPlayerScore = firstPlayerScore;
    }

    public int getSecondPlayerScore() {
        return secondPlayerScore;
    }

    public void setSecondPlayerScore(int secondPlayerScore) {
        this.secondPlayerScore = secondPlayerScore;
    }

    public Calendar getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Calendar matchDate) {
        this.matchDate = matchDate;
    }

    public TennisTable getTable() {
        return table;
    }

    public void setTable(TennisTable table) {
        this.table = table;
    }

    public MatchState getState() {
        return state;
    }

    public void setState(MatchState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        return getId() == match.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", firstPlayerId=" + firstPlayerId +
                ", secondPlayerId=" + secondPlayerId +
                ", matchDate=" + matchDate +
                ", table=" + table +
                '}';
    }
}