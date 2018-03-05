package com.saviorru.comsServer.model;

import java.util.ArrayList;
import java.util.Date;

public interface Match {

    Date getDate();
    Player getWinner() throws Exception;
    void setStateMatch(StateMatch stateMatch);
    boolean isPlayed();
    Player getFirstPlayer();
    Player getSecondPlayer();
    int getPointsFirstSide();
    int getPointsSecondSide();
    void setPoints(int pointsFirstSide,int pointsSecondSide);
}
