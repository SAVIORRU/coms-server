package com.saviorru.comsserver;

import com.saviorru.comsserver.domain.*;
import org.junit.Before;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static junit.framework.TestCase.*;

import java.time.LocalDateTime;

public class TournamentSettingsTests {
    private TournamentSettings testSubject;
    private SchemeType schemeType;
    private TimeSettings timeSettings;



    @Before
    public void initTest() throws Exception
    {
        schemeType = SchemeType.ROUND;
        timeSettings = new TimeSettings(11, 18, 12);
        testSubject = new TournamentSettingsImpl(schemeType, LocalDateTime.now(), timeSettings);
    }
    @Test()
    public void settingsGetDateDispatherTest() throws Exception
    {
        DateDispatcher dd = testSubject.getDateDispatcher();
        assertFalse(dd==null);
    }
    @Test()
    public void settingsGetWinnerIdentTest() throws Exception
    {
        WinnerIdentifier dd = testSubject.getWinnerIdentifier();
        assertFalse(dd==null);
    }
}
