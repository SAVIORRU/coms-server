package com.saviorru.comsserver.model.generators;

<<<<<<< HEAD:src/main/java/com/saviorru/comsserver/model/generators/OlympicSchelduleGenerator.java
<<<<<<< HEAD:src/main/java/com/saviorru/comsserver/model/generators/OlympicSchelduleGenerator.java
=======
import com.saviorru.comsserver.model.Tree;
>>>>>>> feature/refactor_match_player:src/main/java/com/saviorru/comsserver/model/generators/OlympicSchelduleGenerator.java
=======
>>>>>>> feature/location:src/main/java/com/saviorru/comsserver/model/generators/OlympicSchelduleGenerator.java
import com.saviorru.comsserver.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class OlympicSchelduleGenerator implements SchelduleGenerator {

    private HashMap<Integer,Match> matchHashMap;
    private ArrayList<ArrayList<Player>> playersLists;
    private Date dateBegin;
    private ArrayList<Location> locationArrayList;
    private Tree tree;
    private Integer countPlayers;
    private ArrayList<Tree.Node> child,parents = new ArrayList<>();

    public OlympicSchelduleGenerator(){
    }
    private void init(ArrayList<ArrayList<Player>> playersLists, ArrayList<Location> locationsList, Date startDate){
        this.playersLists = playersLists;
        this.matchHashMap = new HashMap<>();
        this.dateBegin = startDate;
        this.locationArrayList = locationsList;
        this.countPlayers = playersLists.size();
        this.tree = new Tree(countTour(this.countPlayers).intValue());
        this.child = this.tree.getChildrens();
        addPlayers(this.playersLists);
    }
    private Double countTour(Integer countPlayers){
        Double count = 0.0,st = 2.0,n = 1.0;
        while(count  < countPlayers){
            count = Math.pow(st,n);
            n++;
        }
        return n;
    }

    private void addPlayers(ArrayList<ArrayList<Player>> playersLists){
        if(this.countPlayers == Math.pow(2,countTour(this.countPlayers))) fillFirstTourStandartCountPlayer(playersLists);
        else{
            fillFirstTourNotStandartCountPlayer(playersLists);
        }
    }

    private void fillFirstTourNotStandartCountPlayer(ArrayList<ArrayList<Player>> playersLists){
        for(int i = 0, j = 0  ; j < this.child.size() ; i += 2 , j += 4) {
            this.child.get(j).data = playersLists.get(i);
            this.child.get(j + 1).data = playersLists.get(i+1);
            this.matchHashMap.put(this.matchHashMap.size(),createNewMatch(this.child.get(j).data,this.child.get(j + 1).data));
        }
        for(int i = this.matchHashMap.size()*2, j = 2; j < this.child.size() ; i ++, j += 4) {
            this.child.get(j).data = playersLists.get(i);
            addPlayersNextTour(this.child.get(j));
        }
    }

    private void fillFirstTourStandartCountPlayer(ArrayList<ArrayList<Player>> playersLists){
        for(int i = 0, j = 0  ;i < playersLists.size(); i+=2 , j += 2) {
            this.child.get(j).data = playersLists.get(i);
            this.child.get(j + 1).data = playersLists.get(i+1);
            this.matchHashMap.put(this.matchHashMap.size(),createNewMatch(this.child.get(j).data,this.child.get(j + 1).data));
        }
    }

    private Tree.Node addPlayersNextTour(Tree.Node node){
        if(node.parent.right.data == null || node.parent.left.data == null){
            if(!checkLackPartner(node)){
                if(node.parent.data == null) { node.parent.data = node.data;
                    return  node.parent;
                }
            }
        }
        return node;
    }

    private boolean checkLackPartner(Tree.Node node){
        if((node.parent.right.right == null && node.parent.right.left == null)|| (node.parent.right.right.data == null && node.parent.right.left.data == null)) {
            if ((node.parent.left.right == null && node.parent.left.left == null) || (node.parent.left.right.data == null && node.parent.left.left.data == null)) {
                return false;
            }
        }
        return true;
    }

    private HashMap<Integer,Match> createSchedule(HashMap<Integer,Match> matchHashMap,ArrayList<Tree.Node> parents){
        int  countPLayedMatch = 0;
        for(int i =  matchHashMap.size()-1 ;i >= 0 ; i-- ) {
//            if (matchHashMap.get(i).getStateMatch() == StateMatch.PLAYED){
//                countPLayedMatch++;
//                addInSceme(parents,matchHashMap.get(i).getWinner());
//            }
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
        Tree.Node node;
        for(int i = 0;i < child.size(); i++){
            if(child.get(i).data == winner){
                if(child.get(i).parent.data == null) {
                    child.get(i).parent.data = winner;
                    node = addPlayersNextTour(child.get(i).parent);
                    node = finedTour(node);
                    if (node != null) {
                        if (noClone(createNewMatch(node.left.data, node.right.data)))
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
//            if (match.getFirstSide() == this.matchHashMap.get(i).getFirstSide() && match.getSecondSide() == this.matchHashMap.get(i).getSecondSide()
//                                                                                || match.getFirstSide() == match.getSecondSide()) return false;
//            if(match.getFirstSide() ==  this.matchHashMap.get(i).getSecondSide() && match.getSecondSide() == this.matchHashMap.get(i).getFirstSide()) return false;
        }
        return true;
    }
    private Match createNewMatch(ArrayList<Player> firstSide,ArrayList<Player> secondSide){
        return null; //new OneOnOneMatch(firstSide,secondSide,null,new Date());
    }
    private Tree.Node finedTour(Tree.Node node){
        if(node.parent.right.data != null && node.parent.left.data != null) {
            if (node.parent.data == null) return node.parent;
        }
        return null;
    }
    @Override
    public HashMap<Integer, Match> generateScheldule(ArrayList<ArrayList<Player>> playersLists, ArrayList<Location> locationsList, Date startDate) {
        init(playersLists,locationsList,startDate);
        return this.matchHashMap;
    }

    @Override
    public TypeScheme getGeneratorType() {
        return TypeScheme.OLYMPIC;
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
