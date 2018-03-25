package com.saviorru.comsserver.cli.command;

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
    public Boolean execute() throws Exception {
        System.out.print(tournament.getPlayerGrid().toString());
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
