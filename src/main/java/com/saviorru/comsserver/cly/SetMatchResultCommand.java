package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.domain.Match;
import com.saviorru.comsserver.domain.Points;
import com.saviorru.comsserver.domain.Tournament;

public class SetMatchResultCommand extends Command {

    private Points points;
    private Match match;

    public SetMatchResultCommand(Tournament tournament, Match match, Points points) {
        super(tournament);
        if(match == null || points == null) throw new NullPointerException();
        this.points = points;
        this.match = match;
    }

    @Override
    public Boolean execute(){
        try {
            tournament.finishMatch(match,points);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
