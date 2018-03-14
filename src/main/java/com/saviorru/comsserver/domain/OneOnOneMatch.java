package com.saviorru.comsserver.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class OneOnOneMatch implements Match {

    private LocalDateTime date;
    private Points points;
    private Player firstSide, secondSide;
    private MatchState matchState = MatchState.NOTPLAYED;
    private Location location;

    public OneOnOneMatch(Player firstSide, Player secondSide, Location location, LocalDateTime date) throws Exception {
        if (firstSide == null || secondSide == null || location == null || date == null)
            throw new NullPointerException();
        if(firstSide.equals(secondSide)) throw new Exception("Player can't be for 2 sides simultaneously");
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.location = location;
        this.points = new Points();
        this.date = date;
    }

    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public Player getWinner() throws Exception {
        if (!isPlayed()) throw new Exception("Match didn't played");
        return (this.points.getPointsFirstSide() > this.points.getPointsSecondSide()) ? this.firstSide : this.secondSide;
    }

    @Override
    public void setMatchState(MatchState matchState) {
        this.matchState = matchState;
    }

    @Override
    public boolean isPlayed() {
        return MatchState.PLAYED == this.matchState;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneOnOneMatch that = (OneOnOneMatch) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(firstSide, that.firstSide) &&
                Objects.equals(secondSide, that.secondSide) ||
                (Objects.equals(firstSide, that.secondSide) &&
                Objects.equals(secondSide, that.firstSide));
    }
    @Override
    public Location getLocation() {
        return location;
    }
}
