package com.saviorru.comsserver.model;

import java.util.Date;
import java.util.List;

public interface ScheduleGenerator {

    List<Match> generateSchedule(List<Player> playersLists,LocationDispatcher locationDispatcher,
                                         Date startDate);

    List<Match> updateSchedule(List<Match> schedule);

    TypeScheme getGeneratorType();

}
