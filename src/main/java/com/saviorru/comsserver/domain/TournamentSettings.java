package com.saviorru.comsserver.domain;

import java.time.LocalDateTime;

public interface TournamentSettings {

    public DateDispatcher getDateDispatcher() throws Exception;
    public Scheme getScheme(Integer playersCount) throws Exception;
    public WinnerIdentifier getWinnerIdentifier();
    public SchemeType getSchemeType();
    public LocalDateTime getStartDate();
    public TimeSettings getTimeSettings();
}
