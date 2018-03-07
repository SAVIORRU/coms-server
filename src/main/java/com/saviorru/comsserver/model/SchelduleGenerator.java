package com.saviorru.comsserver.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public interface SchelduleGenerator {
    public HashMap<Integer, Match> generateScheldule(ArrayList<ArrayList<Player>> playersLists, ArrayList<Location> locationsList,
                                               Date startDate);
    public HashMap<Integer, Match> updateScheldule(HashMap<Integer, Match> scheldule);
    public TypeScheme getGeneratorType();
}
