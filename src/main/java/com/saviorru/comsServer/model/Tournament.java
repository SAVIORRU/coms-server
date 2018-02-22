package com.saviorru.comsServer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public interface Tournament {
    public HashMap<Integer, ArrayList<Player>> getPlayers();
    public TournamentState getState();
    public HashMap<Integer, Match> getScheldule();
    public HashMap<Integer, Location> getLocations();
    public TypeScheme getSchemeType();
    public void updatePlayers(HashMap<Integer, ArrayList<Player>> playersLists);
    public void updateState(TournamentState state);
    public void updateScheldule(HashMap<Integer, ArrayList<Player>> playersLists, Date startDate);
}
