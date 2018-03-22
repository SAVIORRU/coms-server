package com.saviorru.comsserver.domain.model;


import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.domain.model.PrizePlace;

public class PrizePlaceThePlayer implements PrizePlace {

    private int prizePlace;
    private Player player;

    public PrizePlaceThePlayer(Player player,int prizePlace){
        this.player = player;
        this.prizePlace = prizePlace;
    }

    @Override
    public int getPrizePlace() {
        return prizePlace;
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}
