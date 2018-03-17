package com.saviorru.comsserver.domain;

import java.util.List;

public class PrintTree {

    public void printTree(List<List<Integer>> lists) {
        double space = 10;
        int step = 0;
        int element = 1;
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {
                if (element == 1) printS(space, lists.get(i).get(j));
                else printS(space + step, lists.get(i).get(j));
                element++;
            }
            element = 1;
            space += 6 * (i + 1);
            step += 7 * (i + 1);
            System.out.println("");
            System.out.println("");
        }
    }

    private void printS(double space, int Integer) {
        while (space > 0) {
            System.out.print(" ");
            space--;
        }
        if (Integer == 0) {
            System.out.print("(" + " " + ")");
            return;
        }
        System.out.print("(" + Integer + ")");
    }
}
