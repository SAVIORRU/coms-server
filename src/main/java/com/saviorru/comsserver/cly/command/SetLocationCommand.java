package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.dispatcher.LocationDispatcher;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.model.Player;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.List;

public class SetLocationCommand implements Command {

    private LocationDispatcher locationDispatcher;
    private List<String> arguments;

    public SetLocationCommand(LocationDispatcher locationDispatcher, List<String> arguments) {
        this.locationDispatcher = locationDispatcher;
        this.arguments = arguments;
    }

    @Override
    public Boolean execute(){
        try {
            locationDispatcher.addLocation(new Location(arguments.get(0), arguments.get(1)));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
