package com.saviorru.comsserver.model;

import java.time.LocalDate;

public interface Match {

    LocalDate getDate();
    Player getWinner() throws Exception;
    void setStateMatch(StateMatch stateMatch);
    boolean isPlayed();
    Player getFirstSide();
    Player getSecondSide();
    int getPointsFirstSide();
    int getPointsSecondSide();
    void setPoints(int pointsFirstSide,int pointsSecondSide);
    Location getLocation();
}
