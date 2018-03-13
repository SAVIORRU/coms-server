package com.saviorru.comsserver.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoundScheme
{

    private List<Meet> meetsList;

    public RoundScheme(List<Meet> meetsList) throws Exception
    {
        if (meetsList == null) throw new NullPointerException();
        for (Meet meet: meetsList)
        {
            if (meet == null)
                throw new NullPointerException();
        }
        this.meetsList = meetsList;
    }




    public List<Meet> getAllMeets() {

        return this.meetsList;
    }

    public List<Meet> getUnassignedMeets() {

        List<Meet> pairsList = new ArrayList<>();
        for (Meet meet: this.meetsList)
        {
            if (!(meet.isAssigned()))
                pairsList.add(meet);
        }

        return pairsList;
    }

    public List<Meet> getAssignedMeets() {
        List<Meet> pairsList = new ArrayList<>();
        for (Meet meet: this.meetsList)
        {
            if ((meet.isAssigned()))
                pairsList.add(meet);
        }

        return pairsList;
    }
    public Meet getNextUnassignedMeet()
    {
        for (Meet meet: this.meetsList)
        {
            if (!(meet.isAssigned()))
                return meet;
        }
        return null;
    }


    public void assignMeet(Player firstPlayer, Player secondPlayer) throws Exception {
        for (Meet meet: this.meetsList)
        {
            Set<Player> meetSet = new HashSet<>();
            meetSet.add(meet.getFirstPlayer());
            meetSet.add(meet.getSecondPlayer());
            Set<Player> paramSet = new HashSet<>();
            paramSet.add(firstPlayer);
            paramSet.add(secondPlayer);
            if (meetSet.equals(paramSet))
            {
                meet.assign();
                return;
            }

        }
        throw new Exception("Cant find specified pair");

    }
}

