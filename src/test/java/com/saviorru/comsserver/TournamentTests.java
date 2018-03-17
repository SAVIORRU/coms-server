package com.saviorru.comsserver;

import com.saviorru.comsserver.domain.*;
import com.saviorru.comsserver.domain.tournaments.TennisTournament;
import javafx.util.Pair;
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
    private LocationDispatcher locationDispatcher;
    private Schedule schedule;
    private PlayerDispatcher playerDispatcher;
    private DateDispatcher dateDispatcher;
    private SchemeType schemeType;

    @Before
    public void init() throws Exception {
        playerList = new ArrayList<>();
        locationList = new ArrayList<>();
        locationList.add(new Location("table1", "1"));
        locationList.add(new Location("table2", "2"));
        locationList.add(new Location("table3", "3"));
        locationDispatcher  = new LocationDispatcher();
        playerDispatcher = new PlayerDispatcher();
        dateDispatcher = new DateDispatcher(LocalDateTime.now(),10,18,1);
        schedule = new ScheduleImpl();
        locationDispatcher.addAllLocation(locationList);
        for (int i = 0; i < countPlayers; i++) {
            playerList.add(mock(Player.class));
        }
        playerDispatcher.addPlayers(playerList);
        schemeType = SchemeType.ROUND;
        tournament = new TennisTournament(playerDispatcher, locationDispatcher,dateDispatcher,schedule,"tournament1",schemeType);
    }

    @Test(expected = NullPointerException.class)
    public void testInitNullParam() throws Exception {
        new TennisTournament(null, null, null, null, null,null);
    }

    @Test(expected = Exception.class)
    public void testInitEmptyParam() throws Exception {
        playerDispatcher = new PlayerDispatcher();
        new TennisTournament(playerDispatcher, locationDispatcher,dateDispatcher,schedule,"tournament1",schemeType);
    }

    @Test
    public void testGetName() {
        assertEquals("tournament1", tournament.getName());
    }

    @Test
    public void testGetPlayers() {
        assertTrue(playerList.containsAll(tournament.getPlayers()));
    }

    @Test(expected = Exception.class)
    public void testGetScheduleButTournamentNotStarted() throws Exception {
        tournament.getSchedule();
    }

    @Test(expected = Exception.class)
    public void testGetChampionButTournamentNotInit() throws Exception {
        tournament.getFirstPlacePrizer();
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
        Match testMatch = tournament.getNextMatch();
        testPoints.setPoints(1, 0);
        tournament.finishMatch(tournament.getNextMatch(), testPoints);
        assertFalse(testMatch == tournament.getNextMatch());
    }

    @Test()
    public void testFinishMatches() throws Exception
    {
        tournament.start();
        List<Match> testList = tournament.getSchedule().getMatchesByState(MatchState.NOTPLAYED);
        List<Points>  testListP = new ArrayList<>();
        for (int i = 0; i< testList.size(); i++)
        {
            testListP.add(new Points(1, 0));
        }
        Match testMatch = tournament.getNextMatch();
        tournament.finishMatches(testList, testListP);
        assertTrue(testList.size() == tournament.getSchedule().getMatchesByState(MatchState.PLAYED).size());
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
    @Test(expected = Exception.class)
    public void testGetNextMatchException() throws Exception
    {
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
    @Test()
    public void testGetGrid() throws Exception
    {
        List<List<Pair<Player, Player>>> grid = tournament.getPlayerGrid();
        Integer count = 0;
        for (List<Pair<Player, Player>> list: grid)
            count += list.size();
        assertEquals(count, tournament.getScheme().getMaxPairCount());
    }

}
