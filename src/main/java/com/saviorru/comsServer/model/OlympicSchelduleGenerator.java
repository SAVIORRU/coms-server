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
    private ArrayList<Tree.Node> child,parents = new ArrayList<>();

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
            this.child.get(j).data = this.matchHashMap.get(i).getFirstSide();
            this.child.get(j+1).data = this.matchHashMap.get(i).getSecondSide();
       }
    }


    private HashMap<Integer,Match> createSchedule(HashMap<Integer,Match> matchHashMap,ArrayList<Tree.Node> parents){
        int  countPLayedMatch = 0;
        for(int i =  matchHashMap.size()-1 ;i >= 0 ; i-- ) {
            if (matchHashMap.get(i).getStateMatch() == StateMatch.PLAYED){
                countPLayedMatch++;
                addInSceme(parents,matchHashMap.get(i).getWinner());
            }
        }
        if(countPLayedMatch == this.matchHashMap.size() && parents.size() > 1)  {
            createSchedule(this.matchHashMap,initParents(parents));
        }
        return this.matchHashMap;
    }
    private ArrayList<Tree.Node> initParents(ArrayList<Tree.Node> child){
        ArrayList<Tree.Node> arrayList = new ArrayList<>();
        for(int i = 0; i < child.size();i+=2){
            arrayList.add(child.get(i).parent);
        }
        return arrayList;
    }
    private boolean addInSceme(ArrayList<Tree.Node> child,ArrayList<Player> winner){
        Tree.Node node = new Tree.Node();
        for(int i = 0;i < child.size(); i++){
            if(child.get(i).data == winner){
                if(child.get(i).parent.data == null) {
                    child.get(i).parent.data = winner;
                    node = finedTour(child.get(i).parent);
                    if (node != null) {
                        if (!noClone(createNewMatch(node.left.data, node.right.data)))
                            this.matchHashMap.put(matchHashMap.size(), createNewMatch(node.left.data, node.right.data));
                    }
                }
                return true;
            }
        }
        return false;
    }
    private boolean noClone(Match match){
        for(int i = 0; i < this.matchHashMap.size() ; i++){
            if (match.getFirstSide() == this.matchHashMap.get(i).getFirstSide() && match.getSecondSide() == this.matchHashMap.get(i).getSecondSide()
                                                                                || match.getFirstSide() == match.getSecondSide()) return true;
            if(match.getFirstSide() ==  this.matchHashMap.get(i).getSecondSide() && match.getSecondSide() == this.matchHashMap.get(i).getFirstSide()) return true;
        }
        return false;
    }
    private Match createNewMatch(ArrayList<Player> firstSide,ArrayList<Player> secondSide){
        return new OneOnOneMatch(firstSide,secondSide,new Location<Integer>(5),new Date());
    }
    private Tree.Node finedTour(Tree.Node node){
        if(node.parent.right.data != null && node.parent.left.data != null) {
            if (node.parent.data == null) return node.parent;
        }
        return null;
    }
    @Override
    public HashMap<Integer, Match> generateScheldule(ArrayList<ArrayList<Player>> playersLists, ArrayList<Location> locationsList, Date startDate) {
        return createSchedule(this.matchHashMap,this.child);
    }

    @Override
    public TypeScheme getGeneratorType() {
        return null;
    }

    @Override
    public HashMap<Integer, Match> updateScheldule(HashMap<Integer, Match> matchHashMap){
        return createSchedule(matchHashMap,this.child);
    }

    public ArrayList<Player> getСhampion(){
        return this.tree.getRoot().data;
    }

    public  ArrayList<ArrayList<Player>> getPlayersOnTour(int level){
        if(level > this.tree.getLevel() || level < 1) throw new Error("invalid level");
        return getPlayersOnLevel(level);
    }

    private ArrayList<ArrayList<Player>> getPlayersOnLevel(int level) {
        ArrayList<Tree.Node> ch = this.child;
        ArrayList<ArrayList<Player>> arrayList = new ArrayList<>();
        int count = 1;
        if(level == 1) {
            for(int i = 0;i < ch.size();i++){
                if(ch.get(i).data != null) arrayList.add(ch.get(i).data);
            }
            return arrayList;
        }
        while(count < level && ch.size() > 0){
            count++;
            ch = initParents(ch);
        }
        for(int i = 0;i < ch.size();i++){
            if(ch.get(i).data != null) arrayList.add(ch.get(i).data);
        }
        return arrayList;
    }
}
