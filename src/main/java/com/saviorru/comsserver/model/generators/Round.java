package com.saviorru.comsserver.model.generators;
<<<<<<< HEAD:src/main/java/com/saviorru/comsserver/model/generators/Round.java
<<<<<<< HEAD:src/main/java/com/saviorru/comsserver/model/generators/Round.java
=======

import com.saviorru.comsserver.model.*;
>>>>>>> feature/refactor_match_player:src/main/java/com/saviorru/comsserver/model/generators/Round.java
=======
>>>>>>> feature/location:src/main/java/com/saviorru/comsserver/model/generators/Round.java

import com.saviorru.comsserver.model.*;

import java.util.*;

public class Round implements SchelduleGenerator {

    private TypeScheme type = TypeScheme.ROUND;
    private Integer matchDuration = 30; //in minutes
    private Integer allowedTimeStart = 10; //24-hour
    private Integer allowedTimeEnd = 18; //24-hour

    public Round(Integer matchDuration, Integer allowedTimeStart, Integer allowedTimeEnd) {
        this.matchDuration = matchDuration;
        this.allowedTimeStart = allowedTimeStart;
        this.allowedTimeEnd = allowedTimeEnd;
    }

    @Override
    public HashMap<Integer, Match> generateScheldule(ArrayList<ArrayList<Player>> playersLists, ArrayList<Location> locationsList, Date startDate) {
        HashMap<Integer, Match> scheldule = new HashMap<Integer, Match>();
        ArrayList<ArrayList<Integer>> meetingsList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < playersLists.size() -1; i++)
        {
            for (int j = i; j < playersLists.size(); j++)
            {
                if (i == j) continue;
                ArrayList<Integer> meet = new ArrayList<Integer>();
                meet.add(i);
                meet.add(j);
                meetingsList.add(meet);
            }
        }
        Calendar currentDate = new GregorianCalendar();
        currentDate.setTime(startDate);
        Integer locationIndex = 0;
        ArrayList<Match> matchesList = new ArrayList<Match>();
        for (ArrayList<Integer> meet: meetingsList)
        {
            if (locationIndex > locationsList.size()-1)
            {
                currentDate.add(Calendar.MINUTE, matchDuration);
            }
            if (currentDate.get(Calendar.HOUR_OF_DAY) > allowedTimeEnd)
            {
                currentDate.add(Calendar.DAY_OF_MONTH, 1);
                currentDate.set(Calendar.HOUR_OF_DAY, allowedTimeStart);
                currentDate.set(Calendar.MINUTE, 0);
            }
//            Match match = new OneOnOneMatch(playersLists.get(meet.get(0)), playersLists.get(meet.get(1)),
//                    locationsList.get(locationIndex), currentDate.getTime());
           // matchesList.add(match);
            locationIndex += 1;
        }
        for (int i = 0; i < matchesList.size(); i++)
        {
            scheldule.put(i, matchesList.get(i));
        }
        return scheldule;
    }

    @Override
    public HashMap<Integer, Match> updateScheldule(HashMap<Integer, Match> scheldule) {
        return scheldule;
    }

    @Override
    public TypeScheme getGeneratorType() {
        return this.type;
    }
}
