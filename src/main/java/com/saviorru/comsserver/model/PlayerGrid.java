package com.saviorru.comsserver.model;

import javafx.util.Pair;

import java.util.List;

public interface PlayerGrid {
    public List<Pair<Player, Player>> getAllMeets();
    public List<Pair<Player, Player>> getUnassignedMeets();
    public List<Pair<Player, Player>> getAssignedMeets();
    public void assignMeet(Player firstPlayer, Player secondPlayer) throws Exception;
}
