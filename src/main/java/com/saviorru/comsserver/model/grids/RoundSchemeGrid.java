package com.saviorru.comsserver.model.grids;

import com.saviorru.comsserver.model.Player;
import com.saviorru.comsserver.model.PlayerGrid;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class RoundSchemeGrid implements PlayerGrid
{

    private List<Meet> meetsList;

    public RoundSchemeGrid(List<Pair<Player, Player>> pairsList) throws Exception
    {
        this.meetsList = new ArrayList<Meet>();
        for (Pair<Player,Player> pair: pairsList)
        {
            Meet meet = new Meet(pair);
            meetsList.add(meet);

        }
    }

    private class Meet
    {
        private Pair<Player, Player> players;
        private Boolean assigned;

        public Pair<Player, Player> getPlayers() {
            return players;
        }

        public Boolean isAssigned() {
            return assigned;
        }

        public Meet(Pair<Player, Player> players) throws Exception
        {
            if (players == null)
                throw new NullPointerException();
            if ((players.getValue() == null) || (players.getKey() == null))
                throw new NullPointerException();
            if (players.getKey().equals(players.getValue()))
                throw new Exception("Duplicate players in one pair is not allowed");
            this.players = players;
            this.assigned = false;
        }

        public Player getFirstPlayer()
        {
            return this.players.getKey();
        }

        public Player getSecondPlayer()
        {
            return this.players.getValue();
        }

        public void assign() throws Exception
        {
            if (this.isAssigned())
                throw new Exception("Meet is already assigned");
            this.assigned = true;
        }



        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Meet meet = (Meet) o;

            return getPlayers().equals(meet.getPlayers());
        }

    }


    @Override
    public List<Pair<Player, Player>> getAllMeets() {

        List<Pair<Player, Player>> pairsList = new ArrayList<Pair<Player, Player>>();
        for (Meet meet: this.meetsList)
        {
            pairsList.add(new Pair<>(meet.getFirstPlayer(), meet.getSecondPlayer()));
        }
        return pairsList;
    }

    @Override
    public List<Pair<Player, Player>> getUnassignedMeets() {

        List<Pair<Player, Player>> pairsList = new ArrayList<Pair<Player, Player>>();
        for (Meet meet: this.meetsList)
        {
            if (!(meet.isAssigned()))
                pairsList.add(new Pair<>(meet.getFirstPlayer(), meet.getSecondPlayer()));
        }

        return pairsList;
    }

    @Override
    public List<Pair<Player, Player>> getAssignedMeets() {
        List<Pair<Player, Player>> pairsList = new ArrayList<Pair<Player, Player>>();
        for (Meet meet: this.meetsList)
        {
            if (meet.isAssigned())
                pairsList.add(new Pair<>(meet.getFirstPlayer(), meet.getSecondPlayer()));
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

