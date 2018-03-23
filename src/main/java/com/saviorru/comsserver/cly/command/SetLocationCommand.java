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
    private Pair<String, List<String>> arguments;

    public SetLocationCommand(LocationDispatcher locationDispatcher, Pair<String, List<String>> arguments) {
        this.locationDispatcher = locationDispatcher;
        this.arguments = arguments;
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() throws Exception {
        if (arguments.getKey().equals(nameCommand())) {
            locationDispatcher.addLocation(new Location(arguments.getValue().get(0),arguments.getValue().get(1)));
            return true;
        }
        return false;
    }

    @Override
    public String nameCommand() {
        return "set location";
    }

    @Override
    public String commandFormat() {
        return "command: name location, description";
    }
}
