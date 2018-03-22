package com.saviorru.comsserver.domain;

import com.saviorru.comsserver.model.Player;
import org.junit.Test;


import java.time.LocalDate;


import static junit.framework.TestCase.*;





public class PlayerTest {

    @Test(expected = Exception.class)
    public void testInitializingClassField()throws Exception {
           Player player = new Player(null, null, null);
    }

    @Test
    public void tesGetPatronymicNameResultEmptyString() throws Exception{
            Player player = new Player("a","b",null, LocalDate.of(1972,2,1));
            assertEquals("",player.getPatronymicName());
    }
    @Test
    public void testGetPatronymicNameResultNoEmptyString() throws Exception{
            Player player = new Player("a","b", "c", LocalDate.of(1972,2,1));
            assertEquals("c",player.getPatronymicName());
    }
    @Test
    public void testGetAgeResult32()throws Exception{
            Player player = new Player("a","b", "c", LocalDate.of(1986,1,1));
            assertEquals(32,player.getAge());
    }
    @Test
    public void testGetAgeResult31()throws Exception{
            Player player = new Player("a","b", "c", LocalDate.of(1986,4,1));
            assertEquals(31,player.getAge());
    }


}
