package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.domain.Match;
import com.saviorru.comsserver.domain.Points;
import com.saviorru.comsserver.domain.Tournament;

public class SetMatchResultCommand extends Command {

    private Points points;
    private Match match;
private Integer matchNumber;
    public SetMatchResultCommand(Tournament tournament, Integer matchNumber,Integer firstScore,Integer secondScore) throws Exception {
        super(tournament);
        //if(match == null || firstScore = null || secondScore == null) throw new NullPointerException();
        this.points = new Points(firstScore,secondScore);
        this.matchNumber =matchNumber;
    }

    @Override
    public Boolean execute(){
        try {
            this.match = tournament.getSchedule().getAllMatches().get(matchNumber);
            tournament.finishMatch(match,points);
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
