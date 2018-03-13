package com.saviorru.comsserver.domain;

import java.util.List;

public interface Scheme {

    Player getCurrentTourContestant(Player player);
    List<Player> getAllContestant(Player player);
    List<Player> getPlayersCurrentTour();
    List<Player> getPlayersTheTour(int tourNumber);
    int getCountTour();

}
