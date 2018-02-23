package com.saviorru.comsServer.model;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        ArrayList<Player> playerArrayList = new ArrayList<>();
        playerArrayList.add(new Player("a","b",18));
        playerArrayList.add(new Player("c","d",24));
        playerArrayList.add(new Player("g","h",31));
        playerArrayList.add(new Player("m","m",19));
        playerArrayList.add(new Player("g","h",25));
        playerArrayList.add(new Player("k","m",36));
        playerArrayList.add(new Player("l","h",26));
        playerArrayList.add(new Player("i","m",11));
        OlympicSchelduleGenerator gen = new OlympicSchelduleGenerator(new RandomTossUp(playerArrayList).getMatches(),new Date(),3);
    }
}
