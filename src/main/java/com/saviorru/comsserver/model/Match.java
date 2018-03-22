package com.saviorru.comsserver.model;

import com.saviorru.comsserver.MatchState;

import java.time.LocalDateTime;

public interface Match {

    LocalDateTime getDate();
    Player getWinner() throws Exception;
    void setMatchState(MatchState matchState);
    boolean isPlayed();
    Player getFirstSide();
    Player getSecondSide();
    int getPointsFirstSide();
    int getPointsSecondSide();
    void setPoints(int pointsFirstSide,int pointsSecondSide);
    Location getLocation();
    String toString();
}
