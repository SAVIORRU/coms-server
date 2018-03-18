package com.saviorru.comsserver.domain;

import java.util.List;

public class PrintTree {

    public String printTree(List<List<Integer>> lists) {
        StringBuilder tree = new StringBuilder();
        double space = 10;
        int step = 0;
        int element = 1;
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {
                if (element == 1) printS(space, lists.get(i).get(j),tree);
                else printS(space + step, lists.get(i).get(j),tree);
                element++;
            }
            element = 1;
            space += 6 * (i + 1);
            step += 7 * (i + 1);
            tree.append("\n");
            tree.append("\n");
        }
        return tree.toString();
    }

    private void printS(double space, int Integer,StringBuilder stringBuilder) {
        while (space > 0) {
            stringBuilder.append(" ");
            space--;
        }
        if (Integer == 0) {
            stringBuilder.append("(" + " " + ")");
            return;
        }
        stringBuilder.append("(").append(Integer).append(")");
    }
}
