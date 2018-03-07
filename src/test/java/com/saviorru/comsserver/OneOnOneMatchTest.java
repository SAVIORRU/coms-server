package com.saviorru.comsserver;

import com.saviorru.comsserver.model.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class OneOnOneMatchTest {

    private Player testPlayer1, testPlayer2;
    private Match match;

    @Test(expected = NullPointerException.class)
    public void testInitialiaingMatchWithNullParametersResultNull() throws Exception {
        new OneOnOneMatch(null, null, null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testInitialiaingMatchParametersResultNull() throws Exception {
        new OneOnOneMatch(null,testPlayer2, null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testInitialiaingMatchParametersWithDateNullResultNull() throws Exception {
        new OneOnOneMatch(testPlayer1, testPlayer2, null, null);
    }

    @Before
    public void initialize() throws Exception {
        testPlayer1 = new Player("a", "b", LocalDate.now());
        testPlayer2 = new Player("c", "b", LocalDate.now());
        match = new OneOnOneMatch(testPlayer1, testPlayer2, new Location(1), LocalDate.now());
    }

    @Test
    public void testGetWinnerResultFirstSidePlayers() throws Exception {
        match.setPoints(11, 10);
        assertEquals(testPlayer1, match.getWinner());
    }

    @Test
    public void testGetWinnerResultSecondSidePlayers() throws Exception {
        match.setPoints(10, 11);
        assertEquals(testPlayer2, match.getWinner());
    }

    @Test
    public void testSetPoints() throws Exception {
        match.setPoints(10, 11);
        assertEquals(10, match.getPointsFirstSide());
        assertEquals(11, match.getPointsSecondSide());
    }

    @Test
    public void testSetPointsParametrsNegativeNumberResultDefaultValue() throws Exception {
        match.setPoints(-5, 11);
        assertEquals(0, match.getPointsFirstSide());
        assertEquals(0, match.getPointsSecondSide());
    }

    @Test
    public void testSetPointsWhenPlayedMatchResultNoChangePounts() throws Exception {
        match.setPoints(10, 11);
        match.setStateMatch(StateMatch.PLAYED);
        match.setPoints(8, 5);
        assertEquals(10, match.getPointsFirstSide());
        assertEquals(11, match.getPointsSecondSide());
    }

    @Test
    public void testIsPlayedResultTrue() throws Exception {
        match.setPoints(10, 11);
        match.setStateMatch(StateMatch.PLAYED);
        assertTrue(match.isPlayed());
    }

    @Test
    public void testIsPlayedResultFalse() throws Exception {
        match.setPoints(10, 11);
        assertFalse(match.isPlayed());
    }
}
