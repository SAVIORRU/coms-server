package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.domain.Tournament;

public class FinishTournamentCommand extends Command{

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
}
