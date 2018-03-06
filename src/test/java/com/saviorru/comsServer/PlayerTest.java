package com.saviorru.comsServer;

import com.saviorru.comsServer.model.Player;
import com.sun.org.apache.xalan.internal.lib.ExsltBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.mock;



@RunWith(Parameterized.class)
public class PlayerTest {

    private  String firstName;
    private  String lastName;
    private  LocalDate birthDate;

    public PlayerTest( String firstName,  String lastName,  LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;

    }

    @Parameterized.Parameters
    public static Collection isEmptyData() {
        return Arrays.asList(new Object[][]{
                {null, "a",LocalDate.of(1972, 2, 3)},
                {"a","b",LocalDate.of(1972, 2, 3)},
                {"a","b",null},
                {"a",null,LocalDate.of(1972, 2, 3)},
                {"","b",LocalDate.of(1972, 2, 3)},
                {"a","",LocalDate.of(1972, 2, 3)},
        });
    }

    @Test
    public void testInitializingClassField() {
        boolean flagInit = true;
        try {
           Player player = new Player(firstName, lastName, birthDate);
           flagInit = false;
        } catch (Exception e) {
            assertTrue(flagInit);
            return;
        }
        assertFalse(flagInit);
    }



}
