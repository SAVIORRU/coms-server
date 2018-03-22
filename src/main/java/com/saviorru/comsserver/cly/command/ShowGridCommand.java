package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.tournament.Tournament;

public class ShowGridCommand implements Command {

    private Tournament tournament;

    public ShowGridCommand(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() {
        try {
            System.out.print(tournament.getPlayerGrid().toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public String nameCommand() {
        return "show grid";
    }

    @Override
    public String commandFormat() {
        return "command";
    }
}
