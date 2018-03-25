package com.saviorru.comsserver.domain;

import com.saviorru.comsserver.domain.dispatcher.DateDispatcher;
import com.saviorru.comsserver.domain.dispatcher.LocationDispatcher;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.schedule.Schedule;
import com.saviorru.comsserver.domain.schedule.ScheduleGenerator;
import com.saviorru.comsserver.domain.schedule.ScheduleGeneratorImpl;
import com.saviorru.comsserver.domain.schematictype.OlympicScheme;
import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.domain.schematictype.RoundScheme;
import com.saviorru.comsserver.domain.schematictype.Scheme;
import org.junit.Before;
import static org.mockito.Mockito.*;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.*;

public class ScheduleGeneratorTests {
    private ScheduleGenerator testSubject;
    private PlayerDispatcher playerDispatcher;
    private LocationDispatcher locationDispatcher;
    private DateDispatcher dateDispatcher;
    private Scheme schemeType;
    private Location loc1;
    private Location loc2;
    private Location loc3;
    @Before
    public void initTest() throws Exception
    {
        playerDispatcher = new PlayerDispatcher();
        playerDispatcher.addPlayer(mock(Player.class));
        playerDispatcher.addPlayer(mock(Player.class));
        playerDispatcher.addPlayer(mock(Player.class));
        playerDispatcher.addPlayer(mock(Player.class));
        playerDispatcher.addPlayer(mock(Player.class));
        playerDispatcher.addPlayer(mock(Player.class));
        playerDispatcher.addPlayer(mock(Player.class));
        playerDispatcher.addPlayer(mock(Player.class));
        locationDispatcher = new LocationDispatcher();
        loc1 = new Location("1", "");
        loc2 = new Location("2", "");
        loc3 = new Location("3", "");
        locationDispatcher.addLocation(loc1);
        locationDispatcher.addLocation(loc2);
        locationDispatcher.addLocation(loc3);
        dateDispatcher = new DateDispatcher(LocalDateTime.now(), new TimeSettings(10, 18, 12));


    }

    //round scheme tests

    @Test()
    public void genRoundGenerateTest() throws Exception
    {
        schemeType = new RoundScheme(playerDispatcher.getAllPlayers().size());
        testSubject = new ScheduleGeneratorImpl(playerDispatcher, locationDispatcher, dateDispatcher, schemeType);
       Schedule schedule =  testSubject.generateSchedule();
       assertEquals(3, schedule.getAllMatches().size());
    }
    @Test()
    public void genRoundUpdateTest() throws Exception
    {
        schemeType = new RoundScheme(playerDispatcher.getAllPlayers().size());
        testSubject = new ScheduleGeneratorImpl(playerDispatcher, locationDispatcher, dateDispatcher, schemeType);
        Schedule schedule =  testSubject.generateSchedule();
        Match match1 = schedule.getMatchesByState(MatchState.NOTPLAYED).get(0);
        Match match2 = schedule.getMatchesByState(MatchState.NOTPLAYED).get(1);
        match1.setPoints(1,0);
        match2.setPoints(1,0);
        match1.setMatchState(MatchState.PLAYED);
        match2.setMatchState(MatchState.PLAYED);
        locationDispatcher.freeLocation(loc1);
        schedule = testSubject.updateSchedule(match1, schedule);
        locationDispatcher.freeLocation(loc2);
        schedule = testSubject.updateSchedule(match2, schedule);
        assertEquals(5, schedule.getAllMatches().size());
    }
    @Test()
    public void genRoundUpdateLoopTest() throws Exception
    {
        schemeType = new RoundScheme(playerDispatcher.getAllPlayers().size());
        testSubject = new ScheduleGeneratorImpl(playerDispatcher, locationDispatcher, dateDispatcher, schemeType);
        Schedule schedule =  testSubject.generateSchedule();
        while (schedule.getAllMatches().size() < 10) {
            Match match1 = schedule.getMatchesByState(MatchState.NOTPLAYED).get(0);
            match1.setPoints(1, 0);
            match1.setMatchState(MatchState.PLAYED);
            locationDispatcher.freeLocation(loc1);
            schedule = testSubject.updateSchedule(match1, schedule);
        }
        assertEquals(10, schedule.getAllMatches().size());
    }

    //olympic scheme tests
    @Test()
    public void genOlympGenerateTest() throws Exception
    {
        schemeType = new OlympicScheme(playerDispatcher.getAllPlayers().size());
        testSubject = new ScheduleGeneratorImpl(playerDispatcher, locationDispatcher, dateDispatcher, schemeType);
        Schedule schedule =  testSubject.generateSchedule();
        assertEquals(3, schedule.getAllMatches().size());
    }
}
