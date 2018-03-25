package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.tournament.Tournament;
import com.saviorru.comsserver.domain.tournament.TournamentReport;

public class ReportCommand implements Command {
    private Tournament tournament;

    public ReportCommand(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public Boolean execute() {
        try {
            System.out.println(new TournamentReport(tournament));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
