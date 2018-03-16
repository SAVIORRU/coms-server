package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.domain.Tournament;

public class StartTournamentCommand extends Command {

    public StartTournamentCommand(Tournament tournament) {
        super(tournament);
    }

    @Override
    public Boolean execute(){
        try {
            tournament.start();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
