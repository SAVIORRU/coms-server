package com.saviorru.comsserver.domain;


import sun.reflect.generics.tree.Tree;


public class Main {
    public static void main(String[] args) {
        // in = new Scanner(System.in);
        //System.out.println(in.nextLine());
        //if(in.nextLine().equalsIgnoreCase( "Help me")) System.out.println("Can are you help ?");
        //HashMap<Integer,ArrayList<ArrayList<Player>>>

        int[][] mas = {{1, 2, 3, 4, 5, 0, 7, 8, 9, 10, 11, 12,13,14,15,16}, {0, 1, 3, 5, 7, 11, 12, 13, 15, 0, 0, 0,0, 0, 0, 0}, {0, 0, 1, 5, 11, 13, 0, 0, 0, 0, 0, 0,0, 0, 0, 0}, {0, 0, 0, 5, 11, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0},
                {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0}};
        double space = 10;
        int step = 0;
        int element = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 16; j++) {

                if (mas[i][j] != 0 && i > 0) {
                    if (element == 1) printS(space, mas[i][j]);
                    else printS(space + step, mas[i][j]);
                    element++;
                } else {
                    if(i == 0) {
                        if (element == 1) printS(space, mas[i][j]);
                        else printS(space + step, mas[i][j]);
                        element++;
                    }
                }
            }
            element = 1;
            space += 6 * (i+1);
            step += 7 * (i + 1);
            System.out.println("");
            System.out.println("");
        }
    }

    public static void printS(double space, int symbol) {
        while (space > 0) {
            System.out.print(" ");
            space--;
        }
        if (symbol == 0) {
            System.out.print("(" + " " + ")");
            return;
        }
        System.out.print("(" + symbol + ")");
    }
}
