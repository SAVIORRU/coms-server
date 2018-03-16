package com.saviorru.comsserver;
import com.saviorru.comsserver.domain.*;
import org.junit.Before;
import static org.mockito.Mockito.*;
import org.junit.Test;


import static junit.framework.TestCase.*;

import java.util.ArrayList;
import java.util.List;

public class RoundWinnerIdentifierTests {

    private RoundWinnerIdentifier testSubject;
    private List<Player> playersList;
    private List<Match> matchesList;

    @Before
    public void initTest() throws Exception
    {
        testSubject = new RoundWinnerIdentifier();
        playersList = new ArrayList<>();
        matchesList = new ArrayList<>();
       for (int i=0; i<4; i++)
       {
           playersList.add(mock(Player.class));
       }

        for (int i = 0; i < playersList.size() - 1; i++) {
            for (int j = i; j < playersList.size(); j++) {
                if (i == j) continue;
                Match match = mock(Match.class);
                when(match.getWinner()).thenReturn(playersList.get(i));
                matchesList.add(match);
            }
        }
    }
    @Test()
    public void identTest() throws Exception
    {
        testSubject.identifyWinner(matchesList);
        assertEquals(1, testSubject.identifyWinner(matchesList).size());
        assertEquals(playersList.get(0), testSubject.identifyWinner(matchesList).get(0));
    }
    @Test()
    public void identNotOneTest() throws Exception
    {
        Match match = mock(Match.class);
        when(match.getWinner()).thenReturn(playersList.get(1));
        matchesList.add(match);
        testSubject.identifyWinner(matchesList);
        assertEquals(2, testSubject.identifyWinner(matchesList).size());
    }
}
