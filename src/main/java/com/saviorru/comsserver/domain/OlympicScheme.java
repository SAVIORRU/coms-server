package com.saviorru.comsserver.domain;

import java.util.ArrayList;
import java.util.List;

public class OlympicScheme {

    private int countRounds;

    public int getCountRounds() {
        return countRounds;
    }

    public static class Node {
        public Player data;
        public Node leftPlayer, rightPlayer, nextPositionPlayer;
    }

    private Node root;
    private List<Node> parents;
    private List<Node> childrens;

    public OlympicScheme(int countRounds) {
        if (countRounds < 0) throw new IndexOutOfBoundsException();
        this.root = new Node();
        this.root.nextPositionPlayer = this.root;
        this.countRounds = countRounds;
        buildScheme(countRounds);
    }

    private void buildScheme(int level) {
        this.parents = new ArrayList<>();
        this.childrens = new ArrayList<>();
        this.parents.add(this.root);
        for (int i = 0; i < level; i++) {
            for (Node parent : this.parents) {
                parent.leftPlayer = new Node();
                parent.leftPlayer.nextPositionPlayer = parent;
                this.childrens.add(parent.leftPlayer);
                parent.rightPlayer = new Node();
                parent.rightPlayer.nextPositionPlayer = parent;
                this.childrens.add(parent.rightPlayer);
            }
            this.parents = new ArrayList<Node>(this.childrens);
            if (level != i + 1) this.childrens.clear();
        }
    }

    public Node getRoot() {
        return this.root;
    }

    public List<Node> getFirstRound() {
        return this.childrens;
    }

    public List<Node> getTheRound(int round) {
        if (round < 0 || round > getCountRounds()) throw new IndexOutOfBoundsException();
        parents = new ArrayList<>();
        if (round > 0) {
            parents = getRound(this.childrens, --round);
        }
        return parents;
    }

    private List<Node> getRound(List<Node> childrens, int round) {
        List<Node> parents = childrens;
        childrens.clear();
        for (int i = 0; i < parents.size(); i += 2) {
            childrens.add(parents.get(i).nextPositionPlayer);
        }
        if (round == 0) return childrens;
        else return getRound(childrens, --round);
    }
}
