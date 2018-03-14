package com.saviorru.comsserver.domain;

import javafx.util.Pair;

import java.util.List;

public interface Scheme {

    Integer getNumberContestantCurrentTour(Integer playerNumber);
    List<Integer> getAllContestant(Integer playerNumber);
    List<Pair<Integer,Integer>> getPlayersNumbersCurrentTour();
    List<Pair<Integer,Integer>> getPlayersNumbersTheTour(int tourNumber);
    int getCountTour();

}
