package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.domain.Tournament;

public abstract class Command {

    protected Tournament tournament;
    protected String backup;

    public Command(Tournament tournament){
        if(tournament == null) throw new NullPointerException();
        this.tournament = tournament;
    }

    protected void backup() {
//        backup = editor.textField.getText();
    }

    public abstract Boolean execute();
}
