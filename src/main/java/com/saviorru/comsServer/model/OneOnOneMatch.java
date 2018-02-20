package com.saviorru.comsServer.model;

import java.util.Date;

public class OneOnOneMatch implements Match {

    private Date date;
    private Object result;
    private String winner;
    private Player firstPlayer, secondPlayer;

    OneOnOneMatch(Player firstPlayer,Player secondPlayer,Date date){
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.date = date;
        this.result = 0;
        this.winner = "";
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    @Override
    public Object getResult() {
        return this.result;
    }

    @Override
    public String getWinner() {
        return this.winner;
    }
}
