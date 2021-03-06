package com.saviorru.comsServer;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ComsServerApplicationTests {

	@Test
	public void contextLoads() {
        ArrayList<Integer> playersIdList = new ArrayList<Integer>();
        playersIdList.add(0);
        playersIdList.add(1);
        playersIdList.add(2);
        playersIdList.add(3);
        playersIdList.add(4);
        playersIdList.add(5);
        playersIdList.add(6);
        playersIdList.add(7);
        playersIdList.add(8);
        ArrayList<Integer> tablesIdList = new ArrayList<Integer>();
        tablesIdList.add(0);
        tablesIdList.add(1);
        tablesIdList.add(2);
        IRepositoryInteractor repository = new Repository();
        final Integer allowedTimeStart = 10;
        final Integer allowedTimeEnd = 18;
        final Integer defaultMatchDuration = 60;
        ArrayList<ArrayList<Integer>> meetingsList = new ArrayList<ArrayList<Integer>>();
        GregorianCalendar startDate = new GregorianCalendar(2018, 2, 15, 12, 0);
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
                tableIndex = 0;
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
            GregorianCalendar matchDate =  (GregorianCalendar)currentDate.clone();
            match.setMatchDate(matchDate);
            repository.createMatchRecord(match);
            matchesList.add(match);
            tableIndex += 1;
        }
        System.out.print(matchesList);
    }

}
