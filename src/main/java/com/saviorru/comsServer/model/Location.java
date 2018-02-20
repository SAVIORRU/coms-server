package com.saviorru.comsServer.model;

public class Location<T> {

    private T locationGamePlace;

    Location(T locationGamePlace){
        this.locationGamePlace = locationGamePlace;
    }

    T getLocationGamePlace(){
        return locationGamePlace;
    };

}
