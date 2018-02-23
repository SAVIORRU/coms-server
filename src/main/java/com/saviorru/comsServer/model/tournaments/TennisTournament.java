package com.saviorru.comsServer.model.tournaments;

import com.saviorru.comsServer.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TennisTournament implements Tournament {

    private String name;
    private HashMap<Integer, ArrayList<Player>> playersLists;
    private TournamentState state = TournamentState.OPEN;
    private TypeScheme schemeType;
    private HashMap<Integer, Location> locations;
    private HashMap<Integer, Match> scheldule;

    

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
    public TypeScheme getSchemeType() {
        return schemeType;
    }

    @Override
    public void updatePlayers(HashMap<Integer, ArrayList<Player>> playersLists) {

    }

    @Override
    public void updateState(TournamentState state) {

    }

    @Override
    public void updateScheldule(HashMap<Integer, ArrayList<Player>> playersLists, Date startDate) {

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
