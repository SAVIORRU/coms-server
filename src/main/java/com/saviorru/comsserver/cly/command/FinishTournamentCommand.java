package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.tournament.Tournament;

public class FinishTournamentCommand extends Command {

    public FinishTournamentCommand(Tournament tournament) {
        super(tournament);
    }

    @Override
    public Boolean execute(){
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
