package com.saviorru.comsserver.model;

import com.saviorru.comsserver.model.Location;
import com.saviorru.comsserver.model.LocationDispather;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static junit.framework.TestCase.*;

public class LocationDispatherTests {
    private LocationDispather testObject;

    @Before
    public void testInit()
    {
        testObject = new LocationDispather();
    }


    @Test()
    public void dispatherAddTest()
    {
        Location testLockMock = mock(Location.class);
        try {
            testObject.addLocation(testLockMock);
            assertEquals(1, testObject.getAllLocations().size());
        }
        catch (Exception e) {}
    }
    @Test(expected = Exception.class)
    public void dispatherAddExceptionTest() throws Exception
    {
        Location testLockMock = mock(Location.class);
        testObject.addLocation(testLockMock);
        testObject.addLocation(testLockMock);
    }
    @Test(expected = Exception.class)
    public void dispatherAddExceptionByPlaceTest() throws Exception
    {
        Location testLoc1 = new Location("1", "");
        Location testLoc2 = new Location("1", "");
        testObject.addLocation(testLoc1);
        testObject.addLocation(testLoc2);
    }
    @Test
    public void dispatherRemoveTest() throws Exception
    {
        Location testMock = mock(Location.class);
        testObject.addLocation(testMock);
        testObject.addLocation(mock(Location.class));
        testObject.removeLocation(testMock);
        assertEquals(1, testObject.getAllLocations().size());
    }
    @Test(expected = Exception.class)
    public void dispatherRemoveExceptionTest() throws Exception
    {
        testObject.addLocation(mock(Location.class));
        Location mock = mock(Location.class);
        testObject.removeLocation(mock);
    }
    //@Test(expected = Exception.class)
}
