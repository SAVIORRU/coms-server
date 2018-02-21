package com.saviorru.comsServer.model;

import java.util.Date;

public interface Match {

    Date getDate();
    Double getResultFirstPlayer();
    Double getResultSecondPlayer();
    void setResultFirstPlayer(Double resultFirstPlayer);
    void setResultSecondPlayer(Double resultSecondPlayer);
    Player getWinner() ;
    void setStateMatch(StateMatch stateMatch);
    StateMatch getStateMatch();
}
