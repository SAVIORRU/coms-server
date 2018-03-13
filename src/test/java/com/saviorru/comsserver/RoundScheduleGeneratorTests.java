package com.saviorru.comsserver;

import com.saviorru.comsserver.domain.*;
import com.saviorru.comsserver.domain.generators.RoundScheduleGenerator;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static junit.framework.TestCase.*;

public class RoundScheduleGeneratorTests {

    private ScheduleGenerator testSubject;
    private LocationDispatcher locationDispatcher;
    private DateDispatcher dateDispatcher;
    private List<Player> playerList;
    private Location loc1;
    private Location loc2;
    private Location loc3;
    private LocalDateTime startDate;

    @Before
    public void initTest() throws Exception
    {
        loc1 = new Location("1", "");
        loc2 = new Location("2", "");
        loc3 = new Location("3", "");
        playerList = new ArrayList<Player>();
        for (int i =0; i < 5; i++)
        {
            playerList.add(mock(Player.class));
        }
        startDate = LocalDateTime.now();
        dateDispatcher = new DateDispatcher(startDate, 10, 18, 12);
        testSubject = new RoundScheduleGenerator();
        locationDispatcher = new LocationDispatcher();
        locationDispatcher.addLocation(loc1);
        locationDispatcher.addLocation(loc2);
        locationDispatcher.addLocation(loc3);
    }

    @Test()
    public void genSchedGenTest() throws Exception
    {
        List<Match> testList = testSubject.generateSchedule(playerList, locationDispatcher, dateDispatcher);
        assertEquals(3, testList.size());
    }
    @Test()
    public void genSchedUpdateTest() throws Exception
    {
        List<Match> testList = testSubject.generateSchedule(playerList, locationDispatcher, dateDispatcher);
        locationDispatcher.freeLocation(loc1);
        locationDispatcher.freeLocation(loc2);
        List<Match> testList2 = testSubject.updateSchedule(testList);
        testList.addAll(testList2);
        assertEquals(5, testList.size());
    }
    @Test(timeout = 1500)
    public void genSchedUpdateTimeoutTest() throws Exception
    {
        List<Match> testList = testSubject.generateSchedule(playerList, locationDispatcher, dateDispatcher);
        while(testList.size() < 10)
        {
            locationDispatcher.freeLocation(loc1);
            testList.addAll(testSubject.updateSchedule(testList));
        }
        assertEquals(10, testList.size());
    }
}
