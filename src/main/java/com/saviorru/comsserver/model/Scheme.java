package com.saviorru.comsserver.model;

import java.util.List;

public interface Scheme {

    Player getContestant(Player player);
    List<Player> getCurrentTour();
    List<Player> getTheTour(int tourNumber);
    int getCountTour();

}
