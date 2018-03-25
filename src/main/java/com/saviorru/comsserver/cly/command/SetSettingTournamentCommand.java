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
    private SchemeType schemeType;
    private TimeSettings timeSettings;
    private String nameTournament;
    private LocalDateTime startDate;

    public SetSettingTournamentCommand(TournamentSettings tournamentSettings, SchemeType schemeType, TimeSettings timeSettings, String nameTournament, LocalDateTime startDate) {
        this.tournamentSettings = tournamentSettings;
        this.schemeType = schemeType;
        this.timeSettings = timeSettings;
        this.nameTournament = nameTournament;
        this.startDate = startDate;
    }


    @Override
    public Boolean execute(){
        try {
            tournamentSettings = new TournamentSettingsImpl(nameTournament, schemeType, startDate, timeSettings);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
