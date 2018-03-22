package com.saviorru.comsserver.domain;

import java.time.LocalDateTime;

public class TournamentSettingsImpl implements TournamentSettings {
    private SchemeType schemeType;
    private LocalDateTime startDate;
    private TimeSettings timeSettings;
    private String tournamentName;

    public TournamentSettingsImpl(String tournamentName, SchemeType schemeType, LocalDateTime startDate, TimeSettings timeSettings)
    throws Exception {
        if (schemeType == null || startDate == null || timeSettings == null) throw new NullPointerException();
        if (tournamentName.isEmpty()) throw new Exception("Empty tournament name");
        this.schemeType = schemeType;
        this.startDate = startDate;
        this.timeSettings = timeSettings;
        this.tournamentName = tournamentName;
    }


    @Override
    public DateDispatcher getDateDispatcher() throws Exception {
        return new DateDispatcher(startDate, this.timeSettings.getAllowedHourStart(), this.timeSettings.getAllowedHourEnd(),
                this.timeSettings.getDateHourOffset());
    }

    @Override
    public Scheme getScheme(Integer playersCount) throws Exception {

        if (schemeType == SchemeType.ROUND) {
            return new RoundScheme(playersCount);
        }
        if (schemeType == SchemeType.OLYMPIC) {
            return new OlympicScheme(playersCount);
        }
        return null;
    }

    @Override
    public WinnerIdentifier getWinnerIdentifier() {
        if (schemeType == SchemeType.ROUND) {
            return new RoundWinnerIdentifier();
        }
        if (schemeType == SchemeType.OLYMPIC) {
            return new OlympicWinnerIndentifier();
        }
        return null;
    }

    public SchemeType getSchemeType() {
        return schemeType;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public TimeSettings getTimeSettings() {
        return timeSettings;
    }

    @Override
    public String getTournamentName() {
        return this.tournamentName;
    }
}
