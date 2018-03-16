package com.saviorru.comsserver;

import com.saviorru.comsserver.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class OlympicWinnerIndentifierTest {
    private List<Player> playerList;
    private List<Location> locationList;
    private List<Match> matchList;
    private DateDispatcher dateDispatcher;
    private WinnerIdentifier winnerIdentifier;

    @Before
    public void init() throws Exception {
        playerList = new ArrayList<>();
        locationList = new ArrayList<>();
        matchList = new ArrayList<>();
        dateDispatcher = new DateDispatcher(LocalDateTime.now(), 10, 18, 1);
        for (int i = 0; i < 8; i++) {
            playerList.add(new Player("Andrey" + i, "Momp", LocalDate.of(1950 + i, 1, 1)));
            locationList.add(new Location("Table" + i, ""));
        }
        for (int i = 0,j = 0; i < 8; i += 2,j++) {
            matchList.add(new OneOnOneMatch(playerList.get(i), playerList.get(i + 1), locationList.get(0), dateDispatcher.getNextDate().plusHours(j)));
            matchList.get(j).setPoints(10,11);
            matchList.get(j).setMatchState(MatchState.PLAYED);
        }
        winnerIdentifier = new OlympicWinnerIndentifier();
    }

    @Test(expected = NullPointerException.class)
    public void testIdentifyWinners_whenNullParameter_NullPointerException() throws Exception {
        winnerIdentifier.identifyWinners(null);
    }

    @Test(expected = Exception.class)
    public void testIdentifyWinners_whenEmptyParameter_Exception() throws Exception {
        matchList.clear();
        winnerIdentifier.identifyWinners(matchList);
    }

    @Test
    public void testIdentifyWinners() throws Exception {
        assertEquals(playerList.get(playerList.size()-1),winnerIdentifier.identifyWinners(matchList).get(0));
    }
}
