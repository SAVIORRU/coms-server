package com.saviorru.comsServer.model;

import java.util.ArrayList;
import java.util.Date;

public interface Match {

    Date getDate();
    Double getResultFirstSide();
    Double getResultSecondSide();
    void setResultFirstSide(Double resultFirstSide);
    void setResultSecondSide(Double resultSecondSide);
    ArrayList<Player> getWinner() ;
    void setStateMatch(StateMatch stateMatch);
    StateMatch getStateMatch();
    ArrayList<Player> getFirstSide();
    ArrayList<Player> getSecondSide();
}
