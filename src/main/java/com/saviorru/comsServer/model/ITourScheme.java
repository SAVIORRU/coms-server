package com.saviorru.comsServer.model;


import java.util.ArrayList;
import java.util.GregorianCalendar;

enum SchemeType {ROUND}

public interface ITourScheme {

    public MatchesScheldule generateScheldule(IRepositoryInteractor repository, ArrayList<Integer> playersIdList, ArrayList<Integer> tablesIdList, GregorianCalendar
                                              startDate);
}
