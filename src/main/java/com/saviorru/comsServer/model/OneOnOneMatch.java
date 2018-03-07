package com.saviorru.comsserver.model;

import java.util.ArrayList;
import java.util.Date;

public class OneOnOneMatch implements Match {

    private Date date;
    private Double resultFirstSide,resultSecondSide;
    private ArrayList<Player> firstSide, secondSide;
    private StateMatch stateMatch = StateMatch.NOTPLAYED;
    private Location location;

    public OneOnOneMatch(ArrayList<Player> firstSide,ArrayList<Player> secondSide, Location location, Date date){
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.location = location;

        this.resultSecondSide = 0.0;
        this.resultFirstSide = 0.0;

    }

    @Override
    public Date getDate() {
        return this.date;
    }

    @Override
    public Double getResultFirstSide() {
        return this.resultFirstSide;
    }

    @Override
    public Double getResultSecondSide() {
        return this.resultSecondSide;
    }

    @Override
    public void setResultFirstSide(Double resultFirstSide) {
        this.resultFirstSide = resultFirstSide;
    }

    @Override
    public void setResultSecondSide(Double resultSecondSide) {
        this.resultSecondSide = resultSecondSide;
    }


    @Override
    public ArrayList<Player> getWinner() {
        if(stateMatch == StateMatch.NOTPLAYED) throw new Error("Match wasn't played");
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

    @Override
    public ArrayList<Player> getFirstSide() {
        return this.firstSide;
    }

    @Override
    public ArrayList<Player> getSecondSide() {
        return this.secondSide;
    }

    private ArrayList<Player> identifyWinner(){
        return (getResultFirstSide() > getResultSecondSide())? this.firstSide:this.secondSide;
    }

    public Location getLocation() {
        return location;
    }
}
