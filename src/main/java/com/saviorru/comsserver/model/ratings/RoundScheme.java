package com.saviorru.comsserver.model.ratings;

import com.saviorru.comsserver.model.Match;
import com.saviorru.comsserver.model.Player;
import com.saviorru.comsserver.model.SchemeType;
import com.saviorru.comsserver.model.TournamentRating;

import java.util.ArrayList;
import java.util.HashMap;

public class RoundScheme implements TournamentRating {
    private SchemeType type = SchemeType.ROUND;
    static final Integer ratingValuesCount = 1;

    public RoundScheme() {

    }

    public SchemeType getType() {
        return type;
    }

    @Override
    public HashMap<ArrayList<Player>, ArrayList<Integer>> generateRatingTable(HashMap<Integer, ArrayList<Player>> playersLists) {
        HashMap<ArrayList<Player>, ArrayList<Integer>> ratingTable = new HashMap<>();
        for (ArrayList<Player> playersList : playersLists.values()) {
            ArrayList<Integer> rating = new ArrayList<>();
            for (int i = 0; i < ratingValuesCount; i++ ) {
                rating.add(0);
            }
            ratingTable.put(playersList, rating);

        }
        return ratingTable;
    }

    @Override
    public HashMap<ArrayList<Player>, ArrayList<Integer>> updateRatingTable(HashMap<ArrayList<Player>, ArrayList<Integer>> ratingTable,
                                                                            ArrayList<Match> matchesList) {
        for (Match match: matchesList)
        {
            if (match.isPlayed()) {
                ArrayList<Player> playersList = null;
                try {
                    //playersList = match.getWinner();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ArrayList<Integer> rating = ratingTable.get(playersList);
                for (int i = 0; i < ratingValuesCount; i++ )
                {
                    if (i == 0)
                    {
                        Integer ratingValue = rating.get(0);
                        rating.remove(0);
                        ratingValue++;
                        rating.add(0, ratingValue);
                    }
                }
            }

        }
        return ratingTable;
    }
}
