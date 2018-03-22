package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.domain.tournament.Tournament;

public abstract class Command {

    protected Tournament tournament;
    protected Tournament backup;

    public Command(Tournament tournament){
        if(tournament == null) throw new NullPointerException();
        this.tournament = tournament;
    }

    protected void backup() {
       backup = tournament.clone();
    }

    public abstract Boolean execute();
    public abstract String nameCommand();
    public abstract String commandFormat();
}
