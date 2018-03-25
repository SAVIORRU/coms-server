package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.cly.RuntimeEnvironment;
import com.saviorru.comsserver.domain.TimeSettings;
import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.domain.schematictype.SchemeType;
import com.saviorru.comsserver.domain.tournament.TournamentSettings;
import com.saviorru.comsserver.domain.tournament.TournamentSettingsImpl;
import javafx.util.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class SetSettingTournamentCommand implements Command {

    private TournamentSettings tournamentSettings;
    private List<String> arguments;
    private TimeSettings timeSettings;
    private RuntimeEnvironment env;

    public SetSettingTournamentCommand(List<String> arguments, TimeSettings timeSettings, RuntimeEnvironment env) {
        this.arguments = arguments;
        this.timeSettings = timeSettings;
        this.env = env;
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() throws Exception {
        SchemeType schemeType = null;
        if (arguments.get(1).toLowerCase().equals("olympic"))
            schemeType = SchemeType.OLYMPIC;
        if (arguments.get(1).toLowerCase().equals("round"))
            schemeType = SchemeType.ROUND;
        List<String> stringDate = Arrays.asList(arguments.get(2).split("-"));
        LocalDateTime startDate = LocalDateTime.of(Integer.parseInt(stringDate.get(0)), Integer.parseInt(stringDate.get(1)),
                Integer.parseInt( stringDate.get(2)), Integer.parseInt(stringDate.get(3)), Integer.parseInt(stringDate.get(4)));
        tournamentSettings = new TournamentSettingsImpl(arguments.get(0), schemeType, startDate, timeSettings);
        this.env.setTournamentSettings(tournamentSettings);
        return true;
    }

    @Override
    public String nameCommand() {
        return "set setting";
    }

    @Override
    public String commandFormat() {
        return "command: tournament name, type scheme (olympic/round ...), date start (yyyy-mm-dd-hh-minmin)";
    }
}
