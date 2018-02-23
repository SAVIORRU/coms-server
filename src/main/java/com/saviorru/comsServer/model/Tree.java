package com.saviorru.comsServer.model;

import java.util.ArrayList;

public class Tree {

    public static class Node{
         Match data;
         Node left,rigth,parent;
    }

    private Node root;

    Tree(Integer level){
        this.root = new Node();
        this.root.parent = this.root;
        buildTree(level);
        
    }
    public void buildTree(int level){
        ArrayList<Node> parents = new ArrayList<>();
        ArrayList<Node> childrens = new ArrayList<>();
        parents.add(this.root);
        for (int i = 1; i < level ; i++)
        {
            for (Node parent: parents)
            {
                parent.left = new Node();
                parent.left.parent = parent;
                childrens.add(parent.left);
                parent.rigth = new Node();
                parent.rigth.parent = parent;
                childrens.add(parent.rigth);
            }
            parents = new ArrayList<Node>(childrens);
            childrens.clear();
        }
    }
}
