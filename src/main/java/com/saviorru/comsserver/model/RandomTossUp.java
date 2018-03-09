package com.saviorru.comsserver.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class RandomTossUp implements TossUp {

    private ArrayList<Player> players, firstSide, secondSide;
    private Match match;
    private ArrayList<Location> locationArrayList;
    private Date startDate;

    RandomTossUp(ArrayList<Player> players, ArrayList<Location> locationsList, Date startDate) {
        this.players = players;
        this.firstSide = new ArrayList<>();
        this.secondSide = new ArrayList<>();
        this.locationArrayList = locationsList;
        this.startDate = startDate;
    }

    private HashMap<Integer, Match> generationMathes() {
        HashMap<Integer, Match> matches = new HashMap<>();

        Random generation = new Random();
        int element1, element2;

        int table = 0;

        int size = this.players.size();

        while (size > 1) {
            element1 = generation.nextInt(size);
            element2 = generation.nextInt(size);
            if (element1 != element2) {
                ArrayList<Player> a = new ArrayList<Player>();
                a.add(this.players.get(element1));
                ArrayList<Player> b = new ArrayList<Player>();
                b.add(this.players.get(element2));
                // match = new OneOnOneMatch(a,b,locationArrayList.get(table),startDate);
                matches.put(table, match);
                table++;
                size -= 2;
                this.players.remove(element1);
                this.players.remove((element2 - 1 >= 0) ? element2 - 1 : 0);
            }
        }
        if (this.players.size() == 1) {
            ArrayList<Player> firstSide = new ArrayList<Player>();
            firstSide.add(this.players.get(0));
            // match = new OneOnOneMatch(firstSide,null,this.locationArrayList.get(this.locationArrayList.size()-1),startDate);
        }
        return matches;
    }

    @Override
    public HashMap<Integer, Match> getMatches() {
        return generationMathes();
    }
}
