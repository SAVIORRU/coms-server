package com.saviorru.comsserver;

import com.saviorru.comsserver.cly.ArgumentType;
import com.saviorru.comsserver.cly.CommandParser;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static junit.framework.TestCase.*;

public class CommandParserTest {
    private CommandParser testSubject;


    @Before
    public void initTest() throws Exception
    {
        testSubject = new CommandParser();
        testSubject.addParsingRule("help", new ArrayList<ArgumentType>());
        ArgumentType[] rule = {ArgumentType.ALPHA, ArgumentType.ALPHA, ArgumentType.DATE};
        testSubject.addParsingRule("addplayer", Arrays.asList(rule));
    }

    @Test()
    public void parserValidTest() throws Exception
    {
        Pair<String, List<String>> pair = testSubject.parse("help");
        assertEquals("help", pair.getKey());
    }
    @Test()
    public void parserValidComplexCommandTest() throws Exception
    {
        Pair<String, List<String>> pair = testSubject.parse("addplayer: Igor, Savochkin, 1978-10-01");
        assertEquals("addplayer", pair.getKey());
    }
    @Test(expected = Exception.class)
    public void parserInvalidTest() throws Exception
    {
        Pair<String, List<String>> pair = testSubject.parse("find: Igor, Savochkin, 1978-10-01");
        assertEquals("addplayer", pair.getKey());
    }

}
