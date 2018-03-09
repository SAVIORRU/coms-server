package com.saviorru.comsserver.model;
import static org.mockito.Mockito.*;
import static junit.framework.TestCase.*;

import org.junit.Test;

public class MeetTests {
    @Test(expected = Exception.class)
    public void meetExceptionTest() throws Exception
    {
        Meet testMeet = new Meet(null, null);
    }
    @Test(expected = Exception.class)
    public void meetDuplicateExceptionTest() throws Exception
    {
        Player testPlMock = mock(Player.class);
        Meet testMeet = new Meet(testPlMock, testPlMock);
    }
    @Test(expected = Exception.class)
    public void meetAssignExceptionTest() throws Exception
    {
        Meet testMeet = new Meet(mock(Player.class), mock(Player.class));
        testMeet.assign();
        testMeet.assign();
    }
}
