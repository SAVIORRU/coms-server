package com.saviorru.comsserver.domain;

import java.util.List;

public interface ScheduleGenerator {

    List<Match> generateSchedule(List<Player> playersLists,LocationDispatcher locationDispatcher,
                                         DateDispatcher dateDispatcher);

    List<Match> updateSchedule(List<Match> matches);

}
