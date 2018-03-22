package com.saviorru.comsserver.domain.schedule;

import com.saviorru.comsserver.domain.MatchState;
import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.Player;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScheduleImpl implements Schedule {
    private List<Match> matchesList;

    public ScheduleImpl(List<Match> matchesList) throws Exception
    {
        if (matchesList == null) throw new NullPointerException();
        Set<Match> checkSet = new HashSet<Match>(matchesList);

        if(checkSet.size() < matchesList.size()){
            throw new Exception("Matches list contains unallowed duplicates");
        }
        for (Match match: matchesList)
        {
            if (match == null) throw new NullPointerException();
        }
        this.matchesList = matchesList;
    }

    public ScheduleImpl() {
        matchesList = new ArrayList<>();
    }


    @Override
    public List<Match> getAllMatches() {
        return this.matchesList;
    }

    @Override
    public void addMatch(Match match) throws Exception {
        if (match == null) throw new NullPointerException();
        if (this.matchesList.contains(match)) throw new Exception("Duplicate matches is not allowed");
        this.matchesList.add(match);

    }

    @Override
    public void addMatches(List<Match> matches) throws Exception {
        if(matches == null) throw new NullPointerException();
        if(matches.isEmpty()) throw new Exception("List is empty");
        if (this.matchesList.containsAll(matches)) throw new Exception("Duplicate matches is not allowed");
        matchesList.addAll(matches);
    }

    @Override
    public List<Match> getMatchesByState(MatchState state) throws Exception {
        if (state == null) throw new NullPointerException();
        List<Match> returnList = new ArrayList<Match>();
        for (Match match: matchesList)
        {
            if (state == MatchState.PLAYED)
                if (match.isPlayed()) returnList.add(match);
            if (state == MatchState.NOTPLAYED)
                if (!(match.isPlayed())) returnList.add(match);
        }
        return returnList;
    }

    @Override
    public List<Match> getMatchesByPlayer(Player player) {
        if (player == null) throw new NullPointerException();
        List<Match> returnList = new ArrayList<Match>();
        for (Match match: matchesList)
        {
            if ((match.getFirstSide() == player) || (match.getSecondSide() == player)) returnList.add(match);
        }
        return returnList;
    }

    @Override
    public List<Match> getMatchesByDate(LocalDateTime date)  {
        if (date == null) throw new NullPointerException();
        List<Match> returnList = new ArrayList<Match>();
        for (Match match: matchesList)
        {
            if (match.getDate() == date) returnList.add(match);
        }
        return returnList;
    }

    @Override
    public List<Match> getMatchesByLocation(Location location) {
        if (location == null) throw new NullPointerException();
        List<Match> returnList = new ArrayList<Match>();
        for (Match match: matchesList)
        {
            if (match.getLocation() == location) returnList.add(match);
        }
        return returnList;
    }

}