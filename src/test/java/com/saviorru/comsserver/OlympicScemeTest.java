package com.saviorru.comsserver;

import com.saviorru.comsserver.domain.OlympicScheme;
import com.saviorru.comsserver.domain.Player;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class OlympicScemeTest {

    private OlympicScheme olympicScheme;
    private List<OlympicScheme.Node> listPlayers;

    @Before
    public void init() {
        olympicScheme = new OlympicScheme(4);
        listPlayers = olympicScheme.getFirstRound();
        for (int i = 0; i < listPlayers.size(); i++) {
            try {
                listPlayers.get(i).data = new Player("a", "b", LocalDate.of(1990, 1, (i > 25) ? i - 25 : i+1));
            } catch (Exception e) {
              e.printStackTrace();
            }
        }
    }

    @Test
    public void testGetFirstRound() {
        for(int i = 0; i < listPlayers.size(); i++) {
            assertEquals(listPlayers.get(i).data, olympicScheme.getFirstRound().get(i).data);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTheRoundParameterNegativeValueResultException(){
        olympicScheme.getTheRound(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTheRoundParameterOutOfRangeResultException(){
        olympicScheme.getTheRound(5);
    }

    @Test
    public void testGetTheRound(){
        listPlayers = olympicScheme.getTheRound(3);
        for (int i = 0; i < listPlayers.size(); i++) {
            try {
                listPlayers.get(i).data = new Player("a", "b", LocalDate.of(1990, 1, (i > 25) ? i - 25 : i+1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < listPlayers.size(); i++) {
            assertEquals(listPlayers.get(i).data, olympicScheme.getTheRound(3).get(i).data);
        }
    }
}
