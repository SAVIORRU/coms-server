package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.domain.dispatcher.LocationDispatcher;
import com.saviorru.comsserver.domain.model.Location;

import java.util.List;

public class SetLocationCommand implements Command {

    private LocationDispatcher locationDispatcher;
    private List<String> arguments;

    public SetLocationCommand(LocationDispatcher locationDispatcher, List<String> arguments) {
        this.locationDispatcher = locationDispatcher;
        this.arguments = arguments;
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() throws Exception {
        locationDispatcher.addLocation(new Location(arguments.get(0), arguments.get(1)));
        return true;
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
