package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.tournament.TournamentSettings;
import com.saviorru.comsserver.domain.tournament.TournamentSettingsImpl;
import javafx.util.Pair;

import java.util.List;

public class SetSettingTournamentCommand implements Command {

    private TournamentSettings tournamentSettings;
    private Pair<String,List<String>> arguments;

    public SetSettingTournamentCommand(TournamentSettings tournamentSettings, Pair<String,List<String>> arguments) {
        this.tournamentSettings = tournamentSettings;
        this.arguments = arguments;
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() throws Exception {
        if(!arguments.getKey().equals("set setting")) return false;
       // tournamentSettings = new TournamentSettingsImpl(arguments.getValue().get(0),arguments.getValue().get(1),arguments.getValue().get(2));
        return true;
    }

    @Override
    public String nameCommand() {
        return "set setting";
    }

    @Override
    public String commandFormat() {
        return "command: tournament name, type scheme (Olympic/Round ...), date start (yyyy-mm-dd)";
    }
}
