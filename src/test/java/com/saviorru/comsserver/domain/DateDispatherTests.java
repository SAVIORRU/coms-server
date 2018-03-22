package com.saviorru.comsserver.domain;
import com.saviorru.comsserver.dispatcher.DateDispatcher;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.*;

public class DateDispatherTests {
    private DateDispatcher testSubject;

    @Test(expected = Exception.class)
    public void dispatherCreateExcTest1() throws Exception
    {
        testSubject = new DateDispatcher(LocalDateTime.of(1970,1,1,0,0), 0, 0, 0);
    }
    @Test(expected = Exception.class)
    public void dispatherCreateExcTest2() throws Exception
    {
        testSubject = new DateDispatcher(LocalDateTime.of(1970,1,1,0,0), -1, 0, 0);
    }
    @Test(expected = Exception.class)
    public void dispatherCreateExcTest3() throws Exception
    {
        testSubject = new DateDispatcher(null, null, null, null);
    }
    @Test()
    public void dispatherGetDateTest1() throws Exception
    {
        testSubject = new DateDispatcher(LocalDateTime.now(), 10, 18, 12);
        System.out.print(LocalDateTime.now());
        System.out.print('\n');
        System.out.print(testSubject.getNextDate());
        assertEquals(true, testSubject.getNextDate().isAfter(LocalDateTime.now()));
    }
}
