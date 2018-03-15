package com.saviorru.comsserver;

import com.saviorru.comsserver.domain.*;
import javafx.util.Pair;
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
    private SchemeType schemeType;
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
        dateDispatcher = new DateDispatcher(LocalDateTime.now(), 10, 18, 12);


    }

    //round scheme tests

    @Test()
    public void genRoundGenerateTest() throws Exception
    {
        schemeType = SchemeType.ROUND;
        testSubject = new ScheduleGeneratorImpl(playerDispatcher, locationDispatcher, dateDispatcher, schemeType);
       Schedule schedule =  testSubject.generateSchedule();
       assertEquals(3, schedule.getAllMatches().size());
    }
    @Test()
    public void genRoundUpdateTest() throws Exception
    {
        schemeType = SchemeType.ROUND;
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
        schemeType = SchemeType.ROUND;
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
        schemeType = SchemeType.OLYMPIC;
        testSubject = new ScheduleGeneratorImpl(playerDispatcher, locationDispatcher, dateDispatcher, schemeType);
        Schedule schedule =  testSubject.generateSchedule();
        assertEquals(3, schedule.getAllMatches().size());
    }
}
