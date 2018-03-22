package com.saviorru.comsserver.domain.model;

import com.saviorru.comsserver.domain.MatchState;
import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.model.Player;

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
