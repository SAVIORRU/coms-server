package com.saviorru.comsserver.model.grids;

import com.saviorru.comsserver.model.Meet;
import com.saviorru.comsserver.model.Player;
import com.saviorru.comsserver.model.PlayerGrid;

import java.util.ArrayList;
import java.util.List;

public class RoundSchemeGrid implements PlayerGrid
{

    private List<Meet> meetsList;

    public RoundSchemeGrid(List<Meet> meetsList) throws Exception
    {
        if (meetsList == null) throw new NullPointerException();
        for (Meet meet: meetsList)
        {
            if (meet == null)
                throw new NullPointerException();
        }
        this.meetsList = meetsList;
    }




    @Override
    public List<Meet> getAllMeets() {

        return this.meetsList;
    }

    @Override
    public List<Meet> getUnassignedMeets() {

        List<Meet> pairsList = new ArrayList<>();
        for (Meet meet: this.meetsList)
        {
            if (!(meet.isAssigned()))
                pairsList.add(meet);
        }

        return pairsList;
    }

    @Override
    public List<Meet> getAssignedMeets() {
        List<Meet> pairsList = new ArrayList<>();
        for (Meet meet: this.meetsList)
        {
            if ((meet.isAssigned()))
                pairsList.add(meet);
        }

        return pairsList;
    }

    @Override
    public void assignMeet(Player firstPlayer, Player secondPlayer) throws Exception {
        for (Meet meet: this.meetsList)
        {
            if ((meet.getFirstPlayer().equals(firstPlayer)) && (meet.getSecondPlayer().equals(secondPlayer)))
            {
                meet.assign();
                return;
            }

        }
        throw new Exception("Cant find specified pair");

    }
}

