package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.tournament.Tournament;

public class ShowGridCommand extends Command {

    public ShowGridCommand(Tournament tournament) {
        super(tournament);
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
