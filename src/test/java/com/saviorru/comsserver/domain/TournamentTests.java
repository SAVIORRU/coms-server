<<<<<<< HEAD:src/test/java/com/saviorru/comsserver/TournamentTests.java
package com.saviorru.comsserver;

import com.saviorru.comsserver.domain.*;
import com.saviorru.comsserver.domain.tournament.TournamentSettings;
import com.saviorru.comsserver.domain.tournament.TournamentSettingsImpl;
import com.saviorru.comsserver.domain.tournaments.TennisTournament;
||||||| merged common ancestors
package com.saviorru.comsserver;

import com.saviorru.comsserver.domain.tournaments.TennisTournament;
=======
package com.saviorru.comsserver.domain;

import com.saviorru.comsserver.domain.dispatcher.DateDispatcher;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.dispatcher.LocationDispatcher;
import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.Score;
import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.domain.schedule.Schedule;
import com.saviorru.comsserver.domain.schematictype.SchemeType;
import com.saviorru.comsserver.domain.tournament.TennisTournament;
import com.saviorru.comsserver.domain.tournament.Tournament;
>>>>>>> feature/ConsoleManagement:src/test/java/com/saviorru/comsserver/domain/TournamentTests.java
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
    private TournamentSettings settings;
    private TimeSettings timeSettings;

    @Before
    public void init() throws Exception {
        playerList = new ArrayList<>();
        locationList = new ArrayList<>();
        locationList.add(new Location("table1", "1"));
        locationList.add(new Location("table2", "2"));
        locationList.add(new Location("table3", "3"));
        locationDispatcher = new LocationDispatcher();
        playerDispatcher = new PlayerDispatcher();
        dateDispatcher = new DateDispatcher(LocalDateTime.now(), 10, 18, 1);
        schedule = new Schedule.ScheduleImpl();
        locationDispatcher.addAllLocation(locationList);
        for (int i = 0; i < countPlayers; i++) {
            playerList.add(mock(Player.class));
        }
        playerDispatcher.addPlayers(playerList);
        timeSettings = new TimeSettings(10, 18, 1);
        schemeType = SchemeType.OLYMPIC;
        settings = new TournamentSettingsImpl("tournament1", schemeType, LocalDateTime.now(),timeSettings);
        tournament = new TennisTournament( playerDispatcher, locationDispatcher, settings, schedule);
    }

    @Test(expected = NullPointerException.class)
    public void testInitNullParam() throws Exception {
        new TennisTournament(null, null, null, null);
    }

    @Test(expected = Exception.class)
    public void testInitEmptyParam() throws Exception {
        playerDispatcher = new PlayerDispatcher();
        new TennisTournament( playerDispatcher, locationDispatcher, settings, schedule);
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

    @Test
    public void testGetChampionButTournamentNotInit() throws Exception {
        assertEquals(null,tournament.getThePrizePlace(1));
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
    public void testFinishMatch() throws Exception {
        tournament.start();
        Score testScore = new Score();
        Match testMatch = tournament.getNextMatch();
        testScore.setPoints(1, 0);
        tournament.finishMatch(tournament.getNextMatch(), testScore);
        assertFalse(testMatch == tournament.getNextMatch());
    }

    @Test()
    public void testFinishMatches() throws Exception {
        tournament.start();
        List<Match> testList = tournament.getSchedule().getMatchesByState(MatchState.NOTPLAYED);
        List<Score> testListP = new ArrayList<>();
        for (int i = 0; i < testList.size(); i++) {
            testListP.add(new Score(1, 0));
        }
        Match testMatch = tournament.getNextMatch();
        tournament.finishMatches(testList, testListP);
        assertTrue(testList.size() == tournament.getSchedule().getMatchesByState(MatchState.PLAYED).size());
    }

    @Test()
    public void testGetNextMatch() throws Exception {
        tournament.start();
        Match match = tournament.getNextMatch();
        assertFalse(match.isPlayed());
        while (tournament.getNextMatch() != null) {
            Score testScore = new Score();
            testScore.setPoints(1, 0);
            tournament.finishMatch(tournament.getNextMatch(), testScore);
        }
        assertEquals(null, tournament.getNextMatch());
    }

    @Test(expected = Exception.class)
    public void testGetNextMatchException() throws Exception {
        Match match = tournament.getNextMatch();
        assertFalse(match.isPlayed());
        while (tournament.getNextMatch() != null) {
            Score testScore = new Score();
            testScore.setPoints(1, 0);
            tournament.finishMatch(tournament.getNextMatch(), testScore);
        }
        assertEquals(null, tournament.getNextMatch());
    }

    @Test()
    public void testGetGrid() throws Exception {
        List<List<Integer>> grid = tournament.getPlayerGrid();
        Integer count = 0;
        for (List<Integer> list: grid)
            count += list.size();
        count /= 2;
        assertEquals(count, tournament.getScheme().getMaxPairCount());
    }

<<<<<<< HEAD:src/test/java/com/saviorru/comsserver/TournamentTests.java
    @Test()
    public void testGetGrid2() throws Exception {
        PlayerGrid grid = tournament.getPlayerGrid();
        System.out.print(grid.toString());
||||||| merged common ancestors
    @Test()
    public void testGetGrid2() throws Exception {
        List<List<Integer>> grid = tournament.getPlayerGrid();
        PrintTree printTree = new PrintTree();
        printTree.printTree(grid);
=======
    @Test
    public void testClone(){
        assertEquals(tournament.clone().getName(),tournament.getName());
>>>>>>> feature/ConsoleManagement:src/test/java/com/saviorru/comsserver/domain/TournamentTests.java
    }
}
