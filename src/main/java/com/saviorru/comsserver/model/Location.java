package com.saviorru.comsserver.model;

public class Location<T> {

    private T locationGamePlace;

    public Location(T locationGamePlace){
        this.locationGamePlace = locationGamePlace;
    }

    T getLocationGamePlace(){
        return locationGamePlace;
    };

}
