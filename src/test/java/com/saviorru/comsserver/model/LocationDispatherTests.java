package com.saviorru.comsserver.model;

import com.saviorru.comsserver.model.Location;
import com.saviorru.comsserver.model.LocationDispather;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static junit.framework.TestCase.*;

public class LocationDispatherTests {
    private LocationDispather testSubject;


    @Before
    public void testInit()
    {
        testSubject = new LocationDispather();
    }


    @Test()
    public void dispatherAddTest()
    {
        Location testLockMock = mock(Location.class);
        try {
            testSubject.addLocation(testLockMock);
            assertEquals(1, testSubject.getAllLocations().size());
        }
        catch (Exception e) {}
    }
    @Test(expected = Exception.class)
    public void dispatherAddExceptionTest() throws Exception
    {
        Location testLockMock = mock(Location.class);
        testSubject.addLocation(testLockMock);
        testSubject.addLocation(testLockMock);
    }
    @Test(expected = Exception.class)
    public void dispatherAddExceptionByPlaceTest() throws Exception
    {
        Location testLoc1 = new Location("1", "");
        Location testLoc2 = new Location("1", "");
        testSubject.addLocation(testLoc1);
        testSubject.addLocation(testLoc2);
    }
    @Test
    public void dispatherRemoveTest() throws Exception
    {
        Location testMock = mock(Location.class);
        testSubject.addLocation(testMock);
        testSubject.addLocation(mock(Location.class));
        testSubject.removeLocation(testMock);
        assertEquals(1, testSubject.getAllLocations().size());
    }
    @Test(expected = Exception.class)
    public void dispatherRemoveExceptionTest() throws Exception
    {
        testSubject.addLocation(mock(Location.class));
        Location mock = mock(Location.class);
        testSubject.removeLocation(mock);
    }
    @Test()
    public void dispatherRemoveByPlaceTest()
    {
        String testString = "1";
        
    }
}
