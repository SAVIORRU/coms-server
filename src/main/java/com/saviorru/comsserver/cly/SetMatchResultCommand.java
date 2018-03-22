package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.domain.Match;
import com.saviorru.comsserver.domain.Score;
import com.saviorru.comsserver.domain.Tournament;

public class SetMatchResultCommand extends Command {

    private Score score;
    private Match match;

    public SetMatchResultCommand(Tournament tournament, Match match, Score score) {
        super(tournament);
        if(match == null || score == null) throw new NullPointerException();
        this.score = score;
        this.match = match;
    }

    @Override
    public Boolean execute(){
        try {
            tournament.finishMatch(match, score);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
