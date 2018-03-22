package com.saviorru.comsserver.tournament;

import com.saviorru.comsserver.model.Match;
import com.saviorru.comsserver.model.Player;

import java.util.ArrayList;
import java.util.HashMap;

public interface TournamentRating {
    public HashMap<ArrayList<Player>, ArrayList<Integer>> generateRatingTable(HashMap<Integer, ArrayList<Player>> playersLists);
    public HashMap<ArrayList<Player>, ArrayList<Integer>> updateRatingTable(HashMap<ArrayList<Player>, ArrayList<Integer>> ratingTable,
                                                                            ArrayList<Match> matchesList);
}
