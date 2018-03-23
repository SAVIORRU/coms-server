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
    public void backup() {

    }

    @Override
    public Boolean execute() throws Exception {
        this.match = tournament.getSchedule().getAllMatches().get(matchNumber);
        tournament.finishMatch(match, score);
        return true;
    }

    @Override
    public String nameCommand() {
        return "set match result";
    }

    @Override
    public String commandFormat() {
        return "command: matchNumber, number, number";
    }
}
