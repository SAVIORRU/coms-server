package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.domain.tournament.Tournament;
import com.saviorru.comsserver.domain.tournament.TournamentReport;

public class ReportCommand implements Command {
    private Tournament tournament;

    public ReportCommand(Tournament tournament) {
        this.tournament = tournament;
    }


    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() throws Exception {
        TournamentReport report = new TournamentReport(tournament);
        System.out.print(report);
        return true;
    }

    @Override
    public String nameCommand() {
        return "report";
    }

    @Override
    public String commandFormat() {
        return "command";
    }
}
