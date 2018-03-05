package com.saviorru.comsServer.model;

import java.util.ArrayList;
import java.util.Date;

public class OneOnOneMatch implements Match {

    private Date date;
    private Points points;
    private Player firstSide, secondSide;
    private StateMatch stateMatch = StateMatch.NOTPLAYED;
    private Location location;

    public OneOnOneMatch(Player firstSide, Player secondSide, Location location, Date date) {
        if (firstSide == null || secondSide == null || location == null) throw new NullPointerException();
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.location = location;
        this.points = new Points();
    }

    @Override
    public Date getDate() {
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
    public Player getFirstPlayer() {
        return this.firstSide;
    }

    @Override
    public Player getSecondPlayer() {
        return this.secondSide;
    }

    @Override
    public int getPointsFirstSide() {
        return this.points.getPointsFirstSide();
    }

    @Override
    public int getPointsSecondSide() {
        return this.getPointsSecondSide();
    }

    @Override
    public void setPoints(int pointsFirstSide, int pointsSecondSide) {
        try {
            this.points.setPoints(pointsFirstSide, pointsSecondSide);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
