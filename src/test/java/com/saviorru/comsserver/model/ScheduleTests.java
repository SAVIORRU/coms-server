package com.saviorru.comsserver.model;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static junit.framework.TestCase.*;

public class ScheduleTests {
    private Schedule testSubject;
    private Player testPlayer;
    private Location testLocation;
    private LocalDate testDate;
    private List<Match> testList;

    @Before
    public void initTest() throws Exception
    {

        testList = new ArrayList<>();

        testList.add(new OneOnOneMatch(mock(Player.class), mock(Player.class), mock(Location.class), LocalDateTime.of(1970, 1,2,1,1)));
        testList.add(new OneOnOneMatch(mock(Player.class), mock(Player.class), mock(Location.class), LocalDateTime.of(1970, 1,3,1,1)));
        testList.add(new OneOnOneMatch(mock(Player.class), mock(Player.class), mock(Location.class), LocalDateTime.of(1970, 1,4,1,1)));
        testList.add(new OneOnOneMatch(mock(Player.class), mock(Player.class), mock(Location.class), LocalDateTime.of(1970, 1,5,1,1)));
        this.testSubject = new ScheduleImpl(testList);
        this.testPlayer = mock(Player.class);
        this.testDate =  LocalDate.of(1970, 1,1);
        this.testLocation = mock(Location.class);
    }

    @Test()
    public void schedCreateTest() throws Exception
    {
        assertEquals(4, testSubject.getAllMatches().size());
    }

    @Test(expected = Exception.class)
    public void schedCreateExceptionTest() throws Exception
    {
        Match testMatch = mock(OneOnOneMatch.class);
        List<Match> testList2 = new ArrayList<>();
        testList2.add(testMatch);
        testList2.add(testMatch);
        testSubject = new ScheduleImpl(testList2);
    }
    @Test()
    public void schedAddTest() throws Exception
    {
        testSubject.addMatch(mock(Match.class));
        assertEquals(5, testSubject.getAllMatches().size());
    }
    @Test(expected = Exception.class)
    public void schedAddExcTest() throws Exception
    {
        Match testMatch = mock(OneOnOneMatch.class);
        testSubject.addMatch(testMatch);
        testSubject.addMatch(testMatch);
    }
    @Test()
    public void schedGetByStateTest() throws Exception
    {
        Match testMatch = new OneOnOneMatch(mock(Player.class), mock(Player.class), mock(Location.class), LocalDateTime.of(1970, 1,6,1,1));
        testSubject.addMatch(testMatch);
        testMatch.setMatchState(MatchState.PLAYED);
        assertEquals(1, testSubject.getMatchesByState(MatchState.PLAYED).size());
    }
    @Test()
    public void schedFinishMatchTest() throws Exception
    {
        Match testMatch = new OneOnOneMatch(mock(Player.class), mock(Player.class), mock(Location.class), LocalDateTime.of(1970, 1,6,1,1));
        testSubject.addMatch(testMatch);
        Points testResult = new Points();
        testResult.setPoints(1, 0);
        testSubject.finishMatch(testMatch, testResult);
        assertEquals(1, testSubject.getMatchesByState(MatchState.PLAYED).size());
    }
    @Test(expected = Exception.class)
    public void schedFinishExcClashMatchTest() throws Exception
    {
        Match testMatch = new OneOnOneMatch(mock(Player.class), mock(Player.class), mock(Location.class), LocalDateTime.of(1970, 1,6,1,1));
        testSubject.addMatch(testMatch);
        Points testResult = new Points();
        testResult.setPoints(1, 0);
        testSubject.finishMatch(testMatch, testResult);
        testSubject.finishMatch(testMatch, testResult);
    }
    @Test(expected = Exception.class)
    public void schedFinishExcNotFoundMatchTest() throws Exception
    {
        Match testMatch = new OneOnOneMatch(mock(Player.class), mock(Player.class), mock(Location.class), LocalDateTime.of(1970, 1,6,1,1));
        testSubject.addMatch(testMatch);
        Points testResult = new Points();
        testResult.setPoints(1, 0);
        testSubject.finishMatch(testMatch, testResult);
        testSubject.finishMatch(mock(Match.class), testResult);
    }

    @Test(expected = NullPointerException.class)
    public void testAddMatchesWithNullParamResultNullPointerException()throws Exception{
       testSubject.addMatches(null);
    }

    @Test(expected = Exception.class)
    public void testAddMatchesParamEmptyListResultException()throws Exception{
        testSubject.addMatches(new ArrayList<>());
    }
    @Test
    public void testAddMatches()throws Exception{
        testSubject.addMatches(testList);
    }

}
