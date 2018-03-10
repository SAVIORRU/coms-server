package com.saviorru.comsserver.model.generators;

import com.saviorru.comsserver.model.*;

import java.util.ArrayList;
import java.util.List;

public class RoundScheduleGenerator implements ScheduleGenerator {
    private DateDispatcher dateDispatcher;
    private LocationDispatcher locationDispatcher;
    private List<Player> playersList;
    private RoundScheme playerGrid;

    public RoundScheduleGenerator()
    {

    }

    public DateDispatcher getDateDispatcher() {
        return dateDispatcher;
    }

    public LocationDispatcher getLocationDispatcher() {
        return locationDispatcher;
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    @Override
    public List<Match> generateSchedule(List<Player> playersLists, LocationDispatcher locationDispatcher, DateDispatcher dateDispatcher)
    {
        if ((dateDispatcher == null)  || (locationDispatcher == null) || (playersLists == null)) throw new NullPointerException();
        for (Player player: playersLists)
        {
            if (player == null)
                throw new NullPointerException();
        }
        this.dateDispatcher = dateDispatcher;
        this.locationDispatcher = locationDispatcher;
        this.playersList = playersLists;
        try {
        this.playerGrid = this.buildGrid(this.playersList);
        return this.createMatches();}
        catch (Exception e){e.printStackTrace();}
        return new ArrayList<>();
    }

    @Override
    public List<Match> updateSchedule(List<Match> matches) {
        try {
            return this.createMatches();}
        catch (Exception e){e.printStackTrace();}
        return new ArrayList<Match>();
    }


    private RoundScheme buildGrid(List<Player> playerList) throws  Exception
    {
            List<Meet> meetsList = new ArrayList<Meet>();
                for (int i = 0; i < playersList.size() - 1; i++) {
                    for (int j = i; j < playersList.size(); j++) {
                        if (i == j) continue;
                        Meet meet = new Meet(playerList.get(i), playerList.get(j));
                        meetsList.add(meet);
                    }
                }
            return new RoundScheme(meetsList);
    }

    private List<Match> createMatches() throws Exception
    {
        List<Location> freeLocationsList = this.locationDispatcher.getAllFreeLocations();
        List<Meet> unassignedMeetsList = this.playerGrid.getUnassignedMeets();
        if ((freeLocationsList.size() == 0) || (unassignedMeetsList.size() == 0)) {
            return new ArrayList<Match>();
        }
        List<Match> matchesList = new ArrayList<>();
            for (Location freeLocation : freeLocationsList) {
                this.locationDispatcher.reserveLocation(freeLocation);
                Meet nextMeet = this.playerGrid.getNextUnassignedMeet();
                this.playerGrid.assignMeet(nextMeet.getFirstPlayer(), nextMeet.getSecondPlayer());
                if (nextMeet == null) break;
                Match nextMatch = new OneOnOneMatch(nextMeet.getFirstPlayer(), nextMeet.getSecondPlayer(), freeLocation,
                        this.dateDispatcher.getNextDate());
                matchesList.add(nextMatch);
            }
        return matchesList;
    }
}
