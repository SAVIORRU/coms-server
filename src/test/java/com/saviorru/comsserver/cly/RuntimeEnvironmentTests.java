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
        commandsMap.put("undo", new ArrayList<>());
        commandsMap.put("start", new ArrayList<>());
        commandsMap.put("finish", new ArrayList<>());
        commandsMap.put("show grid", new ArrayList<>());
        commandsMap.put("show schedule", new ArrayList<>());
        commandsMap.put("show players", new ArrayList<>());
        commandsMap.put("show locations", new ArrayList<>());
        commandsMap.put("set match result", Arrays.asList(ArgumentType.DIGIT, ArgumentType.DIGIT, ArgumentType.DIGIT));
        commandsMap.put("set players", Collections.singletonList(ArgumentType.DIGIT));
        commandsMap.put("set player", Arrays.asList(ArgumentType.ALPHA, ArgumentType.ALPHA, ArgumentType.DATE));
        commandsMap.put("set locations", Collections.singletonList(ArgumentType.DIGIT));
        commandsMap.put("set location", Arrays.asList(ArgumentType.ALPHA, ArgumentType.ALPHA));
        commandsMap.put("set setting", Arrays.asList(ArgumentType.ALPHA,ArgumentType.ALPHA,ArgumentType.DATE));
        commandsMap.put("create tournament", Collections.singletonList(ArgumentType.ALPHA));
        commandsMap.put("help", new ArrayList<>());
        testSubject = new RuntimeEnvironment(commandsMap);
    }

    @Test()
    public void reSetPlayerTest() throws Exception
    {
      String result =  testSubject.executeCommand("set player", Arrays.asList("Igor", "Savochkin", "1969-10-12"));
      assertEquals("DONE", result);
    }
    @Test()
    public void reSetLocationTest()
    {
        String result =  testSubject.executeCommand("set location", Arrays.asList("1", ""));
        System.out.print(result);
        assertEquals("DONE", result);
    }
    @Test()
    public void reStartTest()
    {
        String result = testSubject.executeCommand("set location", Arrays.asList("1", ""));
        result = testSubject.executeCommand("set player", Arrays.asList("Igor", "Savochkin", "1969-10-12"));
        result = testSubject.executeCommand("set player", Arrays.asList("Egor", "Savochkin", "1969-10-12"));
        result = testSubject.executeCommand("set setting", Arrays.asList("Trnmt1", "Olympic", "2019-10-12-12-00"));
        result = testSubject.executeCommand("create tournament", new ArrayList<String>());
        result =  testSubject.executeCommand("start", new ArrayList<String>());
        System.out.print(result);
        assertEquals("DONE", result);
    }
    @Test()
    public void reSetSettingsTest()
    {
        String result =  testSubject.executeCommand("set setting", Arrays.asList("Trnmt1", "Olympic","2019-10-12-12-00"));
        System.out.print(result);
        assertEquals("DONE", result);
    }
    @Test()
    public void reCreateTest() {
        String result = testSubject.executeCommand("set location", Arrays.asList("1", ""));
        result = testSubject.executeCommand("set player", Arrays.asList("Igor", "Savochkin", "1969-10-12"));
        result = testSubject.executeCommand("set player", Arrays.asList("Egor", "Savochkin", "1969-10-12"));
        result = testSubject.executeCommand("set setting", Arrays.asList("Trnmt1", "Olympic", "2019-10-12-12-00"));
        result = testSubject.executeCommand("create tournament", new ArrayList<String>());
        System.out.print(result);
        assertEquals("DONE", result);
    }
    @Test()
    public void reSetMatchResultTest()
    {
        String result = testSubject.executeCommand("set location", Arrays.asList("1", ""));
        result = testSubject.executeCommand("set player", Arrays.asList("Igor", "Savochkin", "1969-10-12"));
        result = testSubject.executeCommand("set player", Arrays.asList("Egor", "Savochkin", "1969-10-12"));
        result = testSubject.executeCommand("set setting", Arrays.asList("Trnmt1", "Olympic", "2019-10-12-12-00"));
        result = testSubject.executeCommand("create tournament", new ArrayList<String>());
        result =  testSubject.executeCommand("start", new ArrayList<String>());
        result =  testSubject.executeCommand("set match result", Arrays.asList("1", "2", "0"));
        System.out.print(result);
        assertEquals("DONE", result);
    }
    @Test()
    public void reFinishMatch()
    {
        String result = testSubject.executeCommand("set location", Arrays.asList("1", ""));
        result = testSubject.executeCommand("set player", Arrays.asList("Igor", "Savochkin", "1969-10-12"));
        result = testSubject.executeCommand("set player", Arrays.asList("Egor", "Savochkin", "1969-10-12"));
        result = testSubject.executeCommand("set setting", Arrays.asList("Trnmt1", "Olympic", "2019-10-12-12-00"));
        result = testSubject.executeCommand("create tournament", new ArrayList<String>());
        result =  testSubject.executeCommand("start", new ArrayList<String>());
        result =  testSubject.executeCommand("set match result", Arrays.asList("1", "2", "0"));
        System.out.print(result);
        assertEquals("DONE", result);
    }
    @Test()
    public void reShowSchedule()
    {
        String result = testSubject.executeCommand("set location", Arrays.asList("1", ""));
        result = testSubject.executeCommand("set player", Arrays.asList("Igor", "Savochkin", "1969-10-12"));
        result = testSubject.executeCommand("set player", Arrays.asList("Egor", "Savochkin", "1969-10-12"));
        result = testSubject.executeCommand("set setting", Arrays.asList("Trnmt1", "Olympic", "2019-10-12-12-00"));
        result = testSubject.executeCommand("create tournament", new ArrayList<String>());
        result =  testSubject.executeCommand("start", new ArrayList<String>());
        result =  testSubject.executeCommand("show schedule", new ArrayList<String>());
        assertEquals("DONE", result);
    }
    @Test
    public void testShowGrid(){
        String result = testSubject.executeCommand("set location", Arrays.asList("1", ""));
        result = testSubject.executeCommand("set player", Arrays.asList("Igor", "Savochkin", "1969-10-12"));
        result = testSubject.executeCommand("set player", Arrays.asList("Egor", "Savochkin", "1969-10-12"));
        result = testSubject.executeCommand("set player", Arrays.asList("Artem", "Savochkin", "1969-10-12"));
        result = testSubject.executeCommand("set setting", Arrays.asList("Trnmt1", "Olympic", "2019-10-12-12-00"));
        result = testSubject.executeCommand("create tournament", new ArrayList<String>());
        result =  testSubject.executeCommand("start", new ArrayList<String>());
        result =  testSubject.executeCommand("show grid", new ArrayList<String>());
        assertEquals("DONE", result);
    }
}
