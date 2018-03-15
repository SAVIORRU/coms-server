package com.saviorru.comsserver;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        PlayerTest.class,
        OneOnOneMatchTest.class,
        LocationTests.class,
        LocationDispatherTests.class,
        OlympicSchemeTest.class,
        ScheduleTests.class,
        DateDispatherTests.class,
        //TournamentTests.class
})
public class TestManager {
}
