package com.saviorru.comsServer.model;

import java.util.HashMap;

public interface MatchScheldule<T> {

    HashMap<T,Match> getMatchScheldule();
    void updateMatchScheldule();

}
