package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.cly.CommandParser;
import com.saviorru.comsserver.domain.dispatcher.LocationDispatcher;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import javafx.util.Pair;

import java.util.List;
import java.util.Scanner;

public class SetLocationsCommand implements Command {

    private CommandParser commandParser;
    private Integer countPlayers;
    private LocationDispatcher   locationDispatcher;

    public SetLocationsCommand(CommandParser commandParser, Integer countPlayers, LocationDispatcher locationDispatcher){
        this.commandParser = commandParser;
        this.countPlayers = countPlayers;
        this.locationDispatcher = locationDispatcher;
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (countPlayers > 0) {
            System.out.print("Введите  места проведения");
            command = scanner.nextLine();
            Pair<String, List<String>> arguments = commandParser.parse(command);
            if (arguments.getKey().equals("set location")) {
                if (new SetLocationCommand(locationDispatcher, arguments).execute())
                    countPlayers--;
                else System.out.println("Место не добавлено!");
            }
        }
        return true;
    }

    @Override
    public String nameCommand() {
        return "set locations";
    }

    @Override
    public String commandFormat() {
        return "command: count location";
    }
}
