package com.saviorru.comsserver.domain;

public interface PrizeWinningPlace {

    Player getFirstPrizePlace();
    Player getSecondPrizePlace();
    Player getThridPrizePlace();
    Player getPlayerThePrizePlace(int PlacePrize);
    int getCountPrizePlace();

}
