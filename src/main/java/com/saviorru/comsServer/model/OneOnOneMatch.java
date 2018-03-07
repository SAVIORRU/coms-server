package com.saviorru.comsServer.model;

import java.time.LocalDate;

public class OneOnOneMatch implements Match {

    private LocalDate date;
    private Points points;
    private Player firstSide, secondSide;
    private StateMatch stateMatch = StateMatch.NOTPLAYED;
    private Location location;

    public OneOnOneMatch(Player firstSide, Player secondSide, Location location, LocalDate date) {
        if (firstSide == null || secondSide == null || location == null || date == null)
            throw new NullPointerException();
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.location = location;
        this.points = new Points();
        this.date = date;
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public Player getWinner() throws Exception {
        if (isPlayed()) throw new Exception("Match didn't played");
        return (this.points.getPointsFirstSide() > this.points.getPointsSecondSide()) ? this.firstSide : this.secondSide;
    }

    @Override
    public void setStateMatch(StateMatch stateMatch) {
        this.stateMatch = stateMatch;
    }

    @Override
    public boolean isPlayed() {
        return (StateMatch.PLAYED == this.stateMatch) ? true : false;
    }

    @Override
    public Player getFirstSide() {
        return this.firstSide;
    }

    @Override
    public Player getSecondSide() {
        return this.secondSide;
    }

    @Override
    public int getPointsFirstSide() {
        return this.points.getPointsFirstSide();
    }

    @Override
    public int getPointsSecondSide() {
        return this.points.getPointsSecondSide();
    }

    @Override
    public void setPoints(int pointsFirstSide, int pointsSecondSide) {
        try {
            if (!isPlayed()) this.points.setPoints(pointsFirstSide, pointsSecondSide);
        } catch (Exception e) {

        }
    }

}
