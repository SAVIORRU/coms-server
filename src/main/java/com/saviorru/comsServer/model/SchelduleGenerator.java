package com.saviorru.comsServer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public interface SchelduleGenerator {
    public HashMap<Integer, Match> generateScheldule(ArrayList<ArrayList<Player>> playersLists, ArrayList<Location> locationsList,
                                               Date startDate);
    public TypeScheme getGeneratorType();
}
