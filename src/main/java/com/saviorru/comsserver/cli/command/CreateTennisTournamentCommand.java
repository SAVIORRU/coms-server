package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.RuntimeEnvironment;
import com.saviorru.comsserver.domain.tournament.TennisTournament;
import com.saviorru.comsserver.domain.tournament.Tournament;

public class CreateTennisTournamentCommand implements Command {

    private RuntimeEnvironment env;

    public CreateTennisTournamentCommand(RuntimeEnvironment env) {
        this.env = env;
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() throws Exception {
        Tournament tournament = new TennisTournament(env.getPlayerDispatcher(), env.getLocationDispatcher(), env.getTournamentSettings(), env.getSchedule());
        env.setTournament(tournament);
        return true;
    }

    @Override
    public String nameCommand() {
        return "create tournament";
    }

    @Override
    public String commandFormat() {
        return "command: type tournament";
    }
}
