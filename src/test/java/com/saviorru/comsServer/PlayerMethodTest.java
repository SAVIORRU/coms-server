package com.saviorru.comsServer;

import com.saviorru.comsServer.model.Player;
import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;

public class PlayerMethodTest {

    @Test
    public void testMethodGetFirstNameResultEmptyString(){
        try {
            Player player = new Player("a","b",null, LocalDate.of(1972,2,1));
            assertEquals("",player.getPatronymicName());
        } catch (Exception e) {

        }
    }
    @Test
    public void testMethodGetFirstNameResultNoEmptyString(){
        try {
            Player player = new Player("a","b", java.util.Optional.of("c"), LocalDate.of(1972,2,1));
            assertEquals("c",player.getPatronymicName());
        } catch (Exception e) {

        }
    }
    @Test
    public void testMethodGetAgeResult32(){
        try {
            Player player = new Player("a","b", java.util.Optional.of("c"), LocalDate.of(1986,1,1));
            assertEquals(32,player.getAge());
        } catch (Exception e) {

        }
    }
    @Test
    public void testMethodGetAgeResult31(){
        try {
            Player player = new Player("a","b", java.util.Optional.of("c"), LocalDate.of(1986,4,1));
            assertEquals(31,player.getAge());
        } catch (Exception e) {

        }
    }
}
