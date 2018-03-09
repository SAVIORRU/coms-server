package com.saviorru.comsserver.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        PlayerTest.class,
        OneOnOneMatchTest.class,
        LocationTests.class,
        LocationDispatherTests.class,
        OlympicScemeTest.class,
        RoundGridTests.class,
        MeetTests.class,
        ScheduleTests.class,
        DateDispatherTests.class,
        OlympicSchelduleGeneratorTest.class,
})
public class TestManager {
}
