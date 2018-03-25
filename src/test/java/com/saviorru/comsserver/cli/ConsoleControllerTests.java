package com.saviorru.comsserver.cli;
import org.junit.Before;
import org.junit.Test;

public class ConsoleControllerTests {

    private ConsoleController testSubject;

    @Before
    public void initTest() throws Exception
    {
        testSubject = new ConsoleController();
    }

    @Test()
    public void ccHelpCmdTest()
    {
        System.out.print(testSubject.parseAndExecute("help"));
    }

    @Test()
    public void ccBackupTest()
    {
        System.out.print(testSubject.parseAndExecute("help"));
        System.out.print(testSubject.parseAndExecute("undo"));
        System.out.print(testSubject.parseAndExecute("set player: Igor, Savochkin, 1969-10-11"));
        System.out.print('1');
    }
}
