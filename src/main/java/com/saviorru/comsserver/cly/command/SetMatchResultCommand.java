package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.Score;
import com.saviorru.comsserver.domain.tournament.Tournament;

public class SetMatchResultCommand implements Command {

    private Score score;
    private Match match;
    private Integer matchNumber;
    private Tournament tournament;

    public SetMatchResultCommand(Tournament tournament, Integer matchNumber, Integer firstScore, Integer secondScore) throws Exception {
        if (firstScore < 0 || secondScore < 0 || tournament == null) throw new NullPointerException();
        this.tournament = tournament;
        this.score = new Score(firstScore, secondScore);
        this.matchNumber = matchNumber;
    }

    @Override
    public Boolean execute() {
        try {
            this.match = tournament.getSchedule().getAllMatches().get(matchNumber);
            tournament.finishMatch(match, score);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
