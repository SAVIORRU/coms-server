package com.saviorru.comsServer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class OlympicSchelduleGenerator implements SchelduleGenerator {

    private HashMap<Integer,Match> matchHashMap;
    private Date dateBegin;
    private Integer countMatchesInDate;
    private Tree tree;
    private Integer countPlayers;
    private ArrayList<Tree.Node> child;

    public OlympicSchelduleGenerator(HashMap<Integer,Match> matchHashMap, Date dateBegin, Integer countMatchesInDate){
        this.matchHashMap = matchHashMap;
        this.dateBegin = dateBegin;
        this.countMatchesInDate = countMatchesInDate;
        this.countPlayers = matchHashMap.size()*2;
        tree = new Tree(countTour(this.countPlayers).intValue());
        this.child = tree.getChildrens();
        addPlayers();
    }


    private Double countTour(Integer countPlayers){
        Double count = 0.0,st = 2.0,n = 1.0;
        while(count  < countPlayers){
            count = Math.pow(st,n);
            n++;
        }
        return n;
    }

    private void addPlayers(){
        for(Integer i = 0,j = 0  ;i < this.matchHashMap.size(); i++,j+= 2){
            this.child.get(j).data = this.matchHashMap.get(i+1).getFirstSide().get(0);
            this.child.get(j+1).data = this.matchHashMap.get(i+1).getSecondSide().get(0);
       }
    }
    
    @Override
    public HashMap<Integer, Match> generateScheldule(ArrayList<ArrayList<Player>> playersLists, ArrayList<Location> locationsList, Date startDate) {
        return null;
    }

    @Override
    public TypeScheme getGeneratorType() {
        return null;
    }
}
