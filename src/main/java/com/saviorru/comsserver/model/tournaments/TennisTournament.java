package com.saviorru.comsserver.model.tournaments;

import com.saviorru.comsserver.model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class TennisTournament implements Tournament {

    private String name;
    private HashMap<Integer, ArrayList<Player>> playersLists;
    private TournamentState state = TournamentState.OPEN;
    private SchemeType schemeType;
    private HashMap<Integer, Location> locations;
    private HashMap<Integer, Match> scheldule;

    public TennisTournament(String name, HashMap<Integer, ArrayList<Player>> playersLists, SchemeType schemeType, HashMap<Integer, Location> locations, HashMap<Integer, Match> scheldule) {
        this.name = name;
        this.playersLists = playersLists;
        this.schemeType = schemeType;
        this.locations = locations;
        this.scheldule = scheldule;
    }


    @Override
    public String getName() {
        return name;
    }

    public HashMap<Integer, ArrayList<Player>> getPlayers() {
        return playersLists;
    }

    @Override
    public TournamentState getState() {
        return state;
    }

    @Override
    public SchemeType getSchemeType() {
        return schemeType;
    }

    @Override
    public void updatePlayers(HashMap<Integer, ArrayList<Player>> playersLists) {
        this.playersLists = playersLists;
    }

    @Override
    public void updateState(TournamentState state) {
        this.state = state;

    }

    @Override
    public void updateScheldule(HashMap<Integer, Match> scheldule) {
        this.scheldule = scheldule;
    }

    @Override
    public HashMap<Integer, Location> getLocations() {
        return locations;
    }

    @Override
    public HashMap<Integer, Match> getScheldule() {
        return scheldule;
    }
}
