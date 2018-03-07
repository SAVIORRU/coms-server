package com.saviorru.comsserver;

import com.saviorru.comsserver.model.Player;

import org.junit.Test;


import java.time.LocalDate;


import static junit.framework.TestCase.*;





public class PlayerTest {

    @Test
    public void testInitializingClassField() {
        boolean flagInit = true;
        try {
           Player player = new Player(null, null, null);
           flagInit = false;
        } catch (Exception e) {
            assertTrue(flagInit);
            return;
        }
        assertFalse(flagInit);
    }

    @Test
    public void tesGetFirstNameResultEmptyString(){
        try {
            Player player = new Player("a","b",null, LocalDate.of(1972,2,1));
            assertEquals("",player.getPatronymicName());
        } catch (Exception e) {

        }
    }
    @Test
    public void testGetFirstNameResultNoEmptyString(){
        try {
            Player player = new Player("a","b", java.util.Optional.of("c"), LocalDate.of(1972,2,1));
            assertEquals("c",player.getPatronymicName());
        } catch (Exception e) {

        }
    }
    @Test
    public void testGetAgeResult32(){
        try {
            Player player = new Player("a","b", java.util.Optional.of("c"), LocalDate.of(1986,1,1));
            assertEquals(32,player.getAge());
        } catch (Exception e) {

        }
    }
    @Test
    public void testGetAgeResult31(){
        try {
            Player player = new Player("a","b", java.util.Optional.of("c"), LocalDate.of(1986,4,1));
            assertEquals(31,player.getAge());
        } catch (Exception e) {

        }
    }


}
