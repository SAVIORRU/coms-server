package com.saviorru.comsserver.model;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static junit.framework.TestCase.*;

public class DateDispatherTests {
    private DateDispather testSubject;

    @Test(expected = Exception.class)
    public void dispatherCreateExcTest1() throws Exception
    {
        testSubject = new DateDispather(LocalDateTime.of(1970,1,1,0,0), 0, 0, 0);
    }
    @Test(expected = Exception.class)
    public void dispatherCreateExcTest2() throws Exception
    {
        testSubject = new DateDispather(LocalDateTime.of(1970,1,1,0,0), -1, 0, 0);
    }
    @Test(expected = Exception.class)
    public void dispatherCreateExcTest3() throws Exception
    {
        testSubject = new DateDispather(null, null, null, null);
    }
    @Test()
    public void dispatherGetDateTest1() throws Exception
    {
        testSubject = new DateDispather(LocalDateTime.now(), 10, 18, 12);
        System.out.print(LocalDateTime.now());
        System.out.print('\n');
        System.out.print(testSubject.getNextDate());
        assertEquals(true, testSubject.getNextDate().isAfter(LocalDateTime.now()));
    }
}
