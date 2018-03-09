package com.saviorru.comsserver.model;

import java.util.ArrayList;
import java.util.HashMap;

public interface Tournament {
    public String getName();
    public HashMap<Integer, ArrayList<Player>> getPlayers();
    public TournamentState getState();
    public HashMap<Integer, Match> getScheldule();
    public HashMap<Integer, Location> getLocations();
    public SchemeType getSchemeType();
    public void updatePlayers(HashMap<Integer, ArrayList<Player>> playersLists);
    public void updateState(TournamentState state);
    public void updateScheldule(HashMap<Integer, Match> scheldule);
}
