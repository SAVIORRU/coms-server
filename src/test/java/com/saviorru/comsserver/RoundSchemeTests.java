package com.saviorru.comsserver;

import com.saviorru.comsserver.domain.RoundScheme;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.*;

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
    }
    @Test()
    public void schemeGetTourTest() throws Exception
    {
        assertEquals(playerCount-1, testSubject.getAllPairsInTour(0).size());
    }
    @Test()
    public void schemeGetUnplayedTest() throws Exception
    {
        Pair<Integer, Integer> pair1 = testSubject.getNextNotPlayedPair();
        assertFalse(testSubject.getNextNotPlayedPair().equals(pair1));
    }
    @Test()
    public void schemeGerLoopTest() throws Exception
    {
        while (true)
        {
            Pair<Integer, Integer> pair = testSubject.getNextNotPlayedPair();
            if (pair == null) { break;}

        }
    }

}
