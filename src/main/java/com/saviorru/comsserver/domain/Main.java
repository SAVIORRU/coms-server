package com.saviorru.comsserver.domain;


import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // in = new Scanner(System.in);
        //System.out.println(in.nextLine());
        //if(in.nextLine().equalsIgnoreCase( "Help me")) System.out.println("Can are you help ?");
        //HashMap<Integer,ArrayList<ArrayList<Player>>>
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> lists1 = new ArrayList<Integer>(),lists2= new ArrayList<Integer>(),lists3= new ArrayList<Integer>(),lists4= new ArrayList<Integer>();
        lists1.add(1);   lists1.add(2);   lists1.add(3);   lists1.add(0);
        lists1.add(5);
        lists1.add(6);
        lists1.add(7);
        lists1.add(8);

        lists2.add(1);
        lists2.add(3);
        lists2.add(5);
        lists2.add(7);

        lists3.add(1);
        lists3.add(5);

        lists4.add(1);

        lists.add(lists1);
        lists.add(lists2);
        lists.add(lists3);
        lists.add(lists4);
        PrintTree printTree = new PrintTree();
        String s = printTree.printTree(lists);
        System.out.print(s);
    }

}
