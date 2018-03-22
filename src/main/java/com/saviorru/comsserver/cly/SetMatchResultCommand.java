package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.Score;
import com.saviorru.comsserver.domain.tournament.Tournament;

public class SetMatchResultCommand extends Command {

    private Score score;
    private Match match;
private Integer matchNumber;
    public SetMatchResultCommand(Tournament tournament, Integer matchNumber,Integer firstScore,Integer secondScore) throws Exception {
        super(tournament);
        //if(match == null || firstScore = null || secondScore == null) throw new NullPointerException();
        this.score = new Score(firstScore,secondScore);
        this.matchNumber =matchNumber;
    }

    @Override
    public Boolean execute(){
        try {
            this.match = tournament.getSchedule().getAllMatches().get(matchNumber);
            tournament.finishMatch(match, score);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public String nameCommand() {
        return "setMatchResult";
    }

    @Override
    public String commandFormat() {
        return "command: matchNumber, number, number";
    }
}
