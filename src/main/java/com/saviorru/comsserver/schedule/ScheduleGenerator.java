package com.saviorru.comsserver.schedule;

import com.saviorru.comsserver.model.Match;

import java.util.List;

public interface ScheduleGenerator {

    Schedule generateSchedule() throws Exception;

    Schedule updateSchedule(List<Match> matchesList, Schedule existingSchedule) throws Exception;
    Schedule updateSchedule(Match match, Schedule existingSchedule) throws Exception;

}
