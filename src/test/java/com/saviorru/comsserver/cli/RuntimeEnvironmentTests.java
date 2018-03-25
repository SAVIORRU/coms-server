package com.saviorru.comsserver.cli;
import com.saviorru.comsserver.cli.command.CommandInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.*;



public class RuntimeEnvironmentTests {
    private RuntimeEnvironment testSubject;
    private Map<String, CommandInfo> commandsMap;


    @Before()
    public void initTest() throws Exception
    {
        commandsMap = new HashMap<>();
        commandsMap.put("undo", new CommandInfo("undo", new ArrayList<>(), "command"));
        commandsMap.put("start", new CommandInfo("start", new ArrayList<>(), "command"));
        commandsMap.put("finish", new CommandInfo("finish", new ArrayList<>(), "command"));
        commandsMap.put("show grid", new CommandInfo("show grid", new ArrayList<>(), "command"));
        commandsMap.put("show schedule", new CommandInfo("show schedule", new ArrayList<>(), "command"));
        commandsMap.put("show players", new CommandInfo("show players", new ArrayList<>(), "command"));
        commandsMap.put("show locations", new CommandInfo("show locations", new ArrayList<>(), "command"));
        commandsMap.put("set match result", new CommandInfo("set match result", Arrays.asList(ArgumentType.DIGIT, ArgumentType.DIGIT, ArgumentType.DIGIT), "command: matchNumber, number, number"));
        commandsMap.put("set player", new CommandInfo("set player", Arrays.asList(ArgumentType.ALPHA_DIGIT, ArgumentType.ALPHA_DIGIT, ArgumentType.DATE), "command: first name, second name, yyyy-mm-dd"));
        commandsMap.put("set location", new CommandInfo("set location", Arrays.asList(ArgumentType.ALPHA_DIGIT, ArgumentType.ALPHA_DIGIT), "command: name location, description"));
        commandsMap.put("set setting", new CommandInfo("set setting",Arrays.asList(ArgumentType.ALPHA_DIGIT, ArgumentType.ALPHA, ArgumentType.DATE_TIME),"command: tournament name, type scheme (olympic/round ...), date start (yyyy-mm-dd-hh-mm"));
        commandsMap.put("create tournament", new CommandInfo("create tournament",new ArrayList<>(),"command"));
        commandsMap.put("help", new CommandInfo("help",new ArrayList<>(),"command"));
        commandsMap.put("report", new CommandInfo("report",new ArrayList<>(),"command"));
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
