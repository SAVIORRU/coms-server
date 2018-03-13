package com.saviorru.comsserver.domain;

import java.util.List;

public interface Scheme {

    Player getContestant(Player player);
    List<Player> getCurrentTour();
    List<Player> getTheTour(int tourNumber);
    int getCountTour();

}
