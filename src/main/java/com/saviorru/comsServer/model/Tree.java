package com.saviorru.comsServer.model;

import java.util.ArrayList;

public class Tree {

    public static class Node{
         Player data;
         Node left,right,parent;

    }

    private Node root;
    private ArrayList<Node> parents;
    private ArrayList<Node> childrens;

    public Tree(Integer level){
        this.root = new Node();
        this.root.parent = this.root;
        buildTree(level);
        
    }
    private void buildTree(int level){
        this.parents = new ArrayList<>();
        this.childrens = new ArrayList<>();
        this.parents.add(this.root);
        for (int i = 1; i < level ; i++)
        {
            for (Node parent: this.parents)
            {
                parent.left = new Node();
                parent.left.parent = parent;
                this.childrens.add(parent.left);
                parent.right = new Node();
                parent.right.parent = parent;
                this.childrens.add(parent.right);
            }
            this.parents = new ArrayList<Node>(this.childrens);
            if(level != i+1) this.childrens.clear();
        }
    }
    public Node getRoot(){
        return this.root;
    }

    public ArrayList<Node> getChildrens(){
        return this.childrens;
    }
}
