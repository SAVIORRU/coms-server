package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.cly.command.Command;
import com.saviorru.comsserver.cly.command.SetPlayerCommand;
import com.saviorru.comsserver.cly.command.ShowGridCommand;
import com.saviorru.comsserver.cly.command.TestCommand;
import com.saviorru.comsserver.domain.TimeSettings;
import com.saviorru.comsserver.domain.dispatcher.LocationDispatcher;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.schedule.Schedule;
import com.saviorru.comsserver.domain.schedule.ScheduleImpl;
import com.saviorru.comsserver.domain.tournament.Tournament;
import com.saviorru.comsserver.domain.tournament.TournamentSettings;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class RuntimeEnvironment {

    private PlayerDispatcher playerDispatcher;
    private LocationDispatcher locationDispatcher;
    private Schedule schedule;
    private TournamentSettings tournamentSettings;
    private TimeSettings timeSettings;
    private Map<String, List<ArgumentType>> commandsMap;
    private Tournament tournament;


    public RuntimeEnvironment(Map<String, List<ArgumentType>> commandsMap) throws Exception {
        if (commandsMap == null || commandsMap.isEmpty()) throw new Exception("Commands map empty or null");
        this.commandsMap = commandsMap;
        this.playerDispatcher = new PlayerDispatcher();
        locationDispatcher = new LocationDispatcher();
        timeSettings = new TimeSettings(10, 18, 12);
        schedule = new ScheduleImpl();
    }

    public String executeCommand (String command, List<String> arguments) throws Exception
    {
        if (command.isEmpty()) return ("Empty command string");
        if (!commandsMap.containsKey(command)) return ("Invalid command");
        Command executeCommand = null;
        Boolean executeResult = true;
        try {
            if (command == "test") executeCommand = new TestCommand();
            if (command == "set player")
            {
                executeCommand = new SetPlayerCommand(playerDispatcher, new Pair<String, List<String>>(command, arguments));
             }
             if (command == "show grid") executeCommand = new ShowGridCommand(tournament);
            executeResult = executeCommand.execute();
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        if (!executeResult) return "FAIL";
        return "DONE";
    }



    private Boolean isSettingsConfigured()
    {
        return tournamentSettings != null;
    }
    private Boolean isTournamentCreated()
    {
        return tournament != null;
    }

}
