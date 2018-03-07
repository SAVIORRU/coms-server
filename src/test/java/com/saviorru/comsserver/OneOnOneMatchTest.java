package com.saviorru.comsserver;

import com.saviorru.comsserver.model.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class OneOnOneMatchTest {

    private Player testPlayer1,testPlayer2;
    private Match match;

    @Test(expected = NullPointerException.class)
    public void testInitialiaingMatchWithNullParametersResultNull() {
        new OneOnOneMatch(null, null, null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testInitialiaingMatchParametersResultNull() {
        try {
            new OneOnOneMatch(null, new Player("a", "b", LocalDate.now()), null, null);
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    @Test(expected = NullPointerException.class)
    public void testInitialiaingMatchParametersWithDateNullResultNull() {
        try {
            new OneOnOneMatch(new Player("a", "b", LocalDate.now()), new Player("a", "b", LocalDate.now()), null, null);
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    @Before
    public void initialize() {
        try {
        testPlayer1 = new Player("a", "b", LocalDate.now());
        testPlayer2 = new Player("c", "b", LocalDate.now());
        match = new OneOnOneMatch(testPlayer1,testPlayer2, new Location(1), LocalDate.now());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetWinnerResultFirstSidePlayers() {
        try {
            match.setPoints(11,10);
            assertEquals(testPlayer1,match.getWinner());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetWinnerResultSecondSidePlayers() {
        try {
            match.setPoints(10,11);
            assertEquals(testPlayer2,match.getWinner());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testSetPoints() {
        try {
            match.setPoints(10,11);
            assertEquals(10,match.getPointsFirstSide());
            assertEquals(11,match.getPointsSecondSide());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testSetPointsParametrsNegativeNumberResultDefaultValue() {
        try {
            match.setPoints(-5,11);
            assertEquals(0,match.getPointsFirstSide());
            assertEquals(0,match.getPointsSecondSide());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testSetPointsWhenPlayedMatchResultNoChangePounts() {
        try {
            match.setPoints(10,11);
            match.setStateMatch(StateMatch.PLAYED);
            match.setPoints(8,5);
            assertEquals(10,match.getPointsFirstSide());
            assertEquals(11,match.getPointsSecondSide());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testIsPlayedResultTrue(){
        try {
            match.setPoints(10, 11);
            match.setStateMatch(StateMatch.PLAYED);
            assertTrue(match.isPlayed());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testIsPlayedResultFalse(){
        try {
            match.setPoints(10, 11);
            assertFalse(match.isPlayed());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
