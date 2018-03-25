package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.dispatcher.LocationDispatcher;
import com.saviorru.comsserver.domain.model.Location;

public class ShowLocationCommand implements Command {

    private LocationDispatcher locationDispatcher;
    public ShowLocationCommand(LocationDispatcher locationDispatcher){
        this.locationDispatcher = locationDispatcher;
    }

    @Override
    public Boolean execute(){
        int number = 1;
        for(Location location: locationDispatcher.getAllLocations()){
            number++;
            System.out.println("Место проведение номер " + number + " : " +  location.getPlace()
                            + "; Описание: " + location.getDescription());
        }
        return number > 0;
    }
}
