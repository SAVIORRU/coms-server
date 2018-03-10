package com.saviorru.comsserver.model;

import com.saviorru.comsserver.model.tournaments.TennisTournament;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;

public class TournamentTests {

    private Tournament tournament;
    private final int countPlayers = 8;
    private List<Player> playerList;
    private List<Location> locationList;

    @Before
    public void init() throws Exception {
        playerList = new ArrayList<>();
        locationList = new ArrayList<>();
        locationList.add(new Location("table1", "1"));
        locationList.add(new Location("table2", "2"));
        locationList.add(new Location("table3", "3"));
        for (int i = 0; i < countPlayers; i++) {
            playerList.add(mock(Player.class));
        }
        tournament = new TennisTournament(playerList, locationList, SchemeType.ROUND, LocalDateTime.now(), "ten");
    }

    @Test(expected = NullPointerException.class)
    public void testInitNullParam() throws Exception {
        new TennisTournament(null, null, null, null, null);
    }

    @Test(expected = Exception.class)
    public void testInitEmptyParam() throws Exception {
        new TennisTournament(playerList, new ArrayList<Location>(), SchemeType.OLYMPIC, LocalDateTime.now(), "ten");
    }

    @Test
    public void testGetName() {
        assertEquals("ten", tournament.getName());
    }

    @Test
    public void testGetPlayers() {
        assertEquals(playerList, tournament.getPlayers());
    }

    @Test(expected = Exception.class)
    public void testGetScheduleButTournamentNotStarted() throws Exception {
        tournament.getSchedule();
    }

    @Test(expected = Exception.class)
    public void testGetChampionButTournamentNotInit() throws Exception {
        tournament.getChampion();
    }

    @Test
    public void testStartTournament() throws Exception {
        tournament.start();
    }

    @Test(expected = Exception.class)
    public void testStartTournamentResultException() throws Exception {
        tournament.start();
        tournament.start();
    }

    @Test(expected = Exception.class)
    public void testFinishTournamentMatchesNotPlayed() throws Exception {
        tournament.start();
        tournament.finish();
    }

    @Test(expected = Exception.class)
    public void testFinishTournamentResultException() throws Exception {
        tournament.finish();
    }

    @Test
    public void testIsPlayedTrue() throws Exception {
        tournament.start();
        assertTrue(tournament.isStart());
    }

    @Test
    public void testIsPlayedFalse() throws Exception {
        assertFalse(tournament.isStart());
    }

    @Test()
    public void testFinishMatch() throws Exception
    {
        tournament.start();
        Points testPoints = new Points();
        testPoints.setPoints(1, 0);
        tournament.finishMatch(tournament.getNextMatch(), testPoints);
    }

    @Test()
    public void testGetNextMatch() throws Exception
    {
        tournament.start();
        Match match = tournament.getNextMatch();
        assertFalse(match.isPlayed());
        while (tournament.getNextMatch() != null)
        {
            Points testPoints = new Points();
            testPoints.setPoints(1, 0);
            tournament.finishMatch(tournament.getNextMatch(), testPoints);
        }
        assertEquals(null, tournament.getNextMatch());
    }

}
