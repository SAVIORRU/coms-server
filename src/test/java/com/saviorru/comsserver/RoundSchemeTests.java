package com.saviorru.comsserver;

import com.saviorru.comsserver.domain.Player;
import com.saviorru.comsserver.domain.RoundScheme;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class RoundSchemeTests {
    private RoundScheme testSubject;
    private Integer playerCount;

    @Before
    public void testInit() throws Exception
    {
        playerCount = 5;
        testSubject = new RoundScheme(playerCount);
    }

    @Test()
    public void schemeBuildTest()
    {
        assertEquals((playerCount*(playerCount-1)/2), testSubject.getAllPairs().size());
        System.out.print(testSubject.getAllPairs());
    }
    @Test()
    public void schemeGetTourTest() throws Exception
    {
        assertEquals(playerCount-1, testSubject.getAllPairsInTour(0).size());
    }
    @Test()
    public void schemeGetUnplayedTest() throws Exception
    {
        Pair<Integer, Integer> pair1 = testSubject.getNextUnplayedPair();
        assertFalse(testSubject.getNextUnplayedPair().equals(pair1));
    }

}
