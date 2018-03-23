package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.dispatcher.LocationDispatcher;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.schedule.Schedule;
import com.saviorru.comsserver.domain.tournament.TennisTournament;
import com.saviorru.comsserver.domain.tournament.Tournament;
import com.saviorru.comsserver.domain.tournament.TournamentSettings;

public class CreateTennisTournamentCommand implements Command {

    private Tournament tournament;
    private PlayerDispatcher playerDispatcher;
    private LocationDispatcher locationDispatcher;
    private TournamentSettings tournamentSettings;
    private Schedule schedule;

    public CreateTennisTournamentCommand(Tournament tournament, PlayerDispatcher playerDispatcher, LocationDispatcher locationDispatcher, TournamentSettings tournamentSettings, Schedule schedule) {
        this.tournament = tournament;
        this.playerDispatcher = playerDispatcher;
        this.locationDispatcher = locationDispatcher;
        this.tournamentSettings = tournamentSettings;
        this.schedule = schedule;
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() throws Exception {
        tournament = new TennisTournament(playerDispatcher,locationDispatcher,tournamentSettings,schedule);
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
