package com.saviorru.comsserver.model;

import com.saviorru.comsserver.model.grids.RoundSchemeGrid;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static junit.framework.TestCase.*;

public class RoundGridTests {
    private PlayerGrid testSubject;

    @Before
    public void testInit() throws Exception
    {
        Meet testMeet1 = new Meet(mock(Player.class), mock(Player.class));
        Meet testMeet2 = new Meet(mock(Player.class), mock(Player.class));
        Meet testMeet3 = new Meet(mock(Player.class), mock(Player.class));
        List<Meet> testList = new ArrayList<Meet>();
        testList.add(testMeet1);
        testList.add(testMeet2);
        testList.add(testMeet3);
        testSubject = new RoundSchemeGrid(testList);
    }

    @Test
    public void gridGetUnassignTest()
    {
        assertEquals(3, testSubject.getUnassignedMeets().size());
    }
    @Test
    public void gridGetAssignTest()
    {
        assertEquals(0, testSubject.getAssignedMeets().size());
    }
    @Test
    public void gridAssignTest() throws Exception
    {
        Player testPlMock1 = mock(Player.class);
        Player testPlMock2 = mock(Player.class);
        Meet testMeet = new Meet(testPlMock1, testPlMock2);
        List<Meet> newList = testSubject.getAllMeets();
        newList.add(testMeet);
        testSubject = new RoundSchemeGrid(newList);
        testSubject.assignMeet(testPlMock1, testPlMock2);
        assertEquals(3, testSubject.getUnassignedMeets().size());
    }
    @Test(expected = Exception.class)
    public void assignFailExceptionTest() throws Exception
    {
        Player testPlMock1 = mock(Player.class);
        Player testPlMock2 = mock(Player.class);
        Meet testMeet = new Meet(testPlMock1, testPlMock2);
        List<Meet> newList = testSubject.getAllMeets();
        newList.add(testMeet);
        testSubject = new RoundSchemeGrid(newList);
        testSubject.assignMeet(testPlMock2, testPlMock1);
    }

}
