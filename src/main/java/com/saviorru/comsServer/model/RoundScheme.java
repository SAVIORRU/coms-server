package com.saviorru.comsServer.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RoundScheme implements ITourScheme {

    private SchemeType type = SchemeType.ROUND;
    static final Integer defaultMatchDuration = 60; //in minutes
    static final Integer allowedTimeStart = 10; //24-system hour
    static final Integer allowedTimeEnd = 18; //24-system hour

    @Override
    public MatchesScheldule generateScheldule(IRepositoryInteractor repository, ArrayList<Integer> playersIdList, ArrayList<Integer> tablesIdList, GregorianCalendar startDate) {
        MatchesScheldule scheldule = new MatchesScheldule(repository.getNextMatchesSchelduleId());
        ArrayList<ArrayList<Integer>> meetingsList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < playersIdList.size() -1; i++)
        {
            for (int j = i; j < playersIdList.size(); j++)
            {
                if (i == j) continue;
                ArrayList<Integer> meet = new ArrayList<Integer>();
                meet.add(i);
                meet.add(j);
                meetingsList.add(meet);
            }
        }
        GregorianCalendar currentDate =  (GregorianCalendar)startDate.clone();
        Integer tableIndex = 0;
        ArrayList<Match> matchesList = new ArrayList<Match>();
        for (ArrayList<Integer> meet: meetingsList)
        {
            if (tableIndex > tablesIdList.size()-1)
            {
                currentDate.add(Calendar.MINUTE, defaultMatchDuration);
            }
            if (currentDate.get(Calendar.HOUR_OF_DAY) > allowedTimeEnd)
            {
                currentDate.add(Calendar.DAY_OF_MONTH, 1);
                currentDate.set(Calendar.HOUR_OF_DAY, allowedTimeStart);
                currentDate.set(Calendar.MINUTE, 0);
            }
            Match match = new Match(repository.getNextMatchId());
            match.setFirstPlayerId(meet.get(0));
            match.setSecondPlayerId(meet.get(1));
            match.setTableId(tablesIdList.get(tableIndex));
            match.setMatchDate(currentDate);
            matchesList.add(match);
            tableIndex += 1;
        }
        return null;
    }
}
