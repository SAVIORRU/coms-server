package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.TimeSettings;
import com.saviorru.comsserver.domain.schematictype.SchemeType;
import com.saviorru.comsserver.domain.tournament.TournamentSettings;
import com.saviorru.comsserver.domain.tournament.TournamentSettingsImpl;
import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.List;

public class SetSettingTournamentCommand implements Command {

    private TournamentSettings tournamentSettings;
    private List<String> arguments;
    private TimeSettings timeSettings;

    public SetSettingTournamentCommand(TournamentSettings tournamentSettings, List<String> arguments, TimeSettings timeSettings) {
        this.tournamentSettings = tournamentSettings;
        this.arguments = arguments;
        this.timeSettings = timeSettings;
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() throws Exception {
        SchemeType schemeType = (arguments.get(1).equals("Olympic")) ? SchemeType.OLYMPIC : SchemeType.ROUND;
        tournamentSettings = new TournamentSettingsImpl(arguments.get(0),
                schemeType,
                LocalDateTime.parse(arguments.get(2)),
                timeSettings);
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
