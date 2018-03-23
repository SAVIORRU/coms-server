package com.saviorru.comsserver.cly;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.mockito.Mockito.*;
import static junit.framework.TestCase.*;



public class RuntimeEnvironmentTests {
    private RuntimeEnvironment testSubject;
    private Map<String, List<ArgumentType>> commandsMap;


    @Before()
    public void initTest() throws Exception
    {
        commandsMap = new HashMap<>();
        commandsMap.put("test", new ArrayList<>());
        commandsMap.put("show grid", new ArrayList<>());
        commandsMap.put("set player", Arrays.asList(ArgumentType.ALPHA,ArgumentType.ALPHA,ArgumentType.DATE));
        testSubject = new RuntimeEnvironment(commandsMap);
    }

    @Test()
    public void reSetPlayerTest() throws Exception
    {
      String result =  testSubject.executeCommand("set player", Arrays.asList("Igor", "Savochkin", "1969-10-12"));
      assertEquals("DONE", result);
    }
    @Test()
    public void reTestTest() throws Exception
    {
        String result =  testSubject.executeCommand("test", new ArrayList<String>());
        assertEquals("DONE", result);
    }
    @Test()
    public void reSetPlayerExcTest() throws Exception
    {
        String result =  testSubject.executeCommand("show grid", new ArrayList<String>());
        assertFalse(result == "DONE");
    }
}
