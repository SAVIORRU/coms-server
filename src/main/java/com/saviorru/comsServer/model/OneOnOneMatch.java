package com.saviorru.comsServer.model;

import java.util.Date;

public class OneOnOneMatch implements Match {

    private Date date;
    private Double resultFirstPlayer,resultSecondPlayer;
    private Player firstPlayer, secondPlayer;
    private StateMatch stateMatch = StateMatch.NOTPLAYED;

    OneOnOneMatch(Player firstPlayer,Player secondPlayer,Date date){
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.date = date;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    @Override
    public Double getResultFirstPlayer() {
        return this.resultFirstPlayer;
    }

    @Override
    public Double getResultSecondPlayer() {
        return this.resultSecondPlayer;
    }

    @Override
    public void setResultFirstPlayer(Double resultFirstPlayer) {
        this.resultFirstPlayer = resultFirstPlayer;
    }

    @Override
    public void setResultSecondPlayer(Double resultSecondPlayer) {
        this.resultSecondPlayer = resultSecondPlayer;
    }

    @Override
    public Player getWinner() {
        if(stateMatch == StateMatch.NOTPLAYED) throw new Error("Match wasn't play");
        return identifyWinner();
    }

    @Override
    public void setStateMatch(StateMatch stateMatch) {
        this.stateMatch = stateMatch;
    }

    @Override
    public StateMatch getStateMatch() {
        return this.stateMatch;
    }

    private Player identifyWinner(){
        return (getResultFirstPlayer() > getResultSecondPlayer())? this.firstPlayer:this.secondPlayer;
    }
}
