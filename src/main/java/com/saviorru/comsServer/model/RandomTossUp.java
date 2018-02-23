package com.saviorru.comsServer.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class RandomTossUp implements TossUp {

    private ArrayList<Player> players,firstSide,secondSide;
    private Match match;

    RandomTossUp(ArrayList<Player> players){
         this.players = players;
         this.firstSide = new ArrayList<>();
         this.secondSide = new ArrayList<>();
    }

    private HashMap<Integer,Match> generationMathes(){
        HashMap<Integer,Match> matches = new HashMap<>();

        Random generation = new Random();
        int element1,element2;

        int table = 1;

        int size = this.players.size();

        while(size > 0){
            element1 = generation.nextInt(this.players.size());
            element2 = generation.nextInt(this.players.size());
            if(element1 != element2){
                ArrayList<Player> a = new ArrayList<Player>();
                a.add(this.players.get(element1));
                ArrayList<Player> b = new ArrayList<Player>();
                b.add(this.players.get(element2));
                match = new OneOnOneMatch(a,b, new Location(table),new Date());
                matches.put(table,match);
                table++;
                size -= 2 ;
                this.players.remove(element1);
                this.players.remove((element2-1 >= 0)?element2-1:0);
            }
        }

        return matches;
    }

    @Override
    public HashMap<Integer, Match> getMatches() {
        return generationMathes();
    }
}
