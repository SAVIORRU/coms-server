package com.saviorru.comsServer.model;

import java.util.Date;

public interface Match<T> {

    Date getDate();
    T getResult();
    String getWinner();

}
