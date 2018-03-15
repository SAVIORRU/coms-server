package com.saviorru.comsserver.domain;

import javafx.util.Pair;

import java.util.List;

public interface Scheme {

    List<Pair<Integer, Integer>> getAllPairs();
    Pair<Integer, Integer> getNextNotPlayedPair();
    List<Pair<Integer, Integer>> getAllPairsInTour(Integer tourNumber) throws Exception;
    void updateScheme(List<Integer> winnersList) throws Exception;
}
