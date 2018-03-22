package com.saviorru.comsserver.domain.schematictype;

import javafx.util.Pair;

import java.util.List;

public interface Scheme {

    List<Pair<Integer, Integer>> getAllPairs();
    Pair<Integer, Integer> getNextNotPlayedPair() throws Exception;
    List<Pair<Integer, Integer>> getAllPairsInTour(Integer tourNumber) throws Exception;
    Integer getMaxPairCount();
    Integer getToursCount();
    void updateScheme(List<Integer> winnersList) throws Exception;
    PlayerGrid getPlayerGrid() throws Exception;
}
