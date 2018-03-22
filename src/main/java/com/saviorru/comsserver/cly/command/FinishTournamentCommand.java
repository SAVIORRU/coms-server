package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.tournament.Tournament;

public class FinishTournamentCommand implements Command {

    private Tournament tournament;

    public FinishTournamentCommand(Tournament tournament) {
        if (tournament == null) throw new NullPointerException();
        this.tournament = tournament;
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() {
        try {
            tournament.finish();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public String nameCommand() {
        return "finish";
    }

    @Override
    public String commandFormat() {
        return "command";
    }
}
