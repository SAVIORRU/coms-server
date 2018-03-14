package com.saviorru.comsserver;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        PlayerTest.class,
        OneOnOneMatchTest.class,
        LocationTests.class,
        LocationDispatherTests.class,
        OlympicScemeTest.class,
        RoundSchemeTests.class,
        ScheduleTests.class,
        DateDispatherTests.class,
        OlympicSchelduleGeneratorTest.class,
        //TournamentTests.class
})
public class TestManager {
}
