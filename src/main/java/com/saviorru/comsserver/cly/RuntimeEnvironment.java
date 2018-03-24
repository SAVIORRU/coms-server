package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.cly.command.*;
import com.saviorru.comsserver.domain.TimeSettings;
import com.saviorru.comsserver.domain.dispatcher.LocationDispatcher;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.schedule.Schedule;
import com.saviorru.comsserver.domain.schedule.ScheduleImpl;
import com.saviorru.comsserver.domain.tournament.Tournament;
import com.saviorru.comsserver.domain.tournament.TournamentSettings;
import javafx.util.Pair;

import java.util.ArrayList;
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

    public RuntimeEnvironment(RuntimeEnvironment env) {
        this.playerDispatcher = env.playerDispatcher;
        this.locationDispatcher = env.locationDispatcher;
        this.schedule = env.schedule;
        this.tournamentSettings = env.tournamentSettings;
        this.timeSettings = env.timeSettings;
        this.commandsMap = env.commandsMap;
        this.tournament = env.tournament;
    }

    public String executeCommand(String command, List<String> arguments) {
        if (command.isEmpty()) return ("Empty command string");
        if (!commandsMap.containsKey(command)) return ("Invalid command");
        Command executeCommand = null;
        Boolean executeResult = true;
        try {
            if (command.equals("help")) {
                List<Pair<String,String>> pairList = new ArrayList<>();
                pairList.add(new Pair<>("start","command"));
                pairList.add(new Pair<>("finish","command"));
                pairList.add(new Pair<>("show schedule","command"));
                pairList.add(new Pair<>("show players","command"));
                pairList.add(new Pair<>("show location","command"));
                pairList.add(new Pair<>("create tournament","command"));
                pairList.add(new Pair<>("show grid","command"));
                pairList.add(new Pair<>("report","command"));
                pairList.add(new Pair<>("set player","command: first name, second name, yyyy-mm-dd"));
                pairList.add(new Pair<>("set location","command: name location, description"));
                pairList.add(new Pair<>("set match result","command: matchNumber, number, number"));
                pairList.add(new Pair<>("set setting","command: tournament name, type scheme (olympic/round ...), date start (yyyy-mm-dd-hh-minmin)"));
                pairList.add(new Pair<>("exit","command"));
                executeCommand = new HelpCommand(pairList);
            }

            if (command.equals("set player")) {
                if (isTournamentCreated()) return "Tournament is created";
                executeCommand = new SetPlayerCommand(playerDispatcher, arguments);
            }
            if (command.equals("show schedule")) {
                executeCommand = new ShowScheduleCommand(tournament);
            }
            if (command.equals("show players")) {
                executeCommand = new ShowPlayersCommand(playerDispatcher);
            }
            if (command.equals("show locations")) {
                executeCommand = new ShowLocationCommand(locationDispatcher);
            }
            if (command.equals("set location")) {
                if (isTournamentCreated()) return "Tournament is created";
                executeCommand = new SetLocationCommand(locationDispatcher, arguments);
            }
            if (command.equals("start")) {
                if (!isTournamentCreated()) return "Tournament is not created";
                if (!tournament.isStart())
                    executeCommand = new StartTournamentCommand(tournament);
                else throw new Exception("Tournament is started");
            }
            if (command.equals("finish")) {
                if (!isTournamentCreated()) return "Tournament is not created";
                if (tournament.isStart())
                    executeCommand = new FinishTournamentCommand(tournament);
                else throw new Exception("Tournament is not started");
            }
            if (command.equals("create tournament")) {
                if (isTournamentCreated()) return "Tournament is created";
                executeCommand = new CreateTennisTournamentCommand(this);
            }
            if (command.equals("set match result")) {
                if (!isTournamentCreated()) return "Tournament is not created";
                if (tournament.isStart())
                    executeCommand = new SetMatchResultCommand(tournament, Integer.parseInt(arguments.get(0))-1, Integer.parseInt(arguments.get(1)),
                            Integer.parseInt(arguments.get(2)));
                else throw new Exception("Tournament is not started");
            }
            if (command.equals("set setting")) {
                if (isTournamentCreated()) return "Tournament is created";
                executeCommand = new SetSettingTournamentCommand(arguments,timeSettings, this);
            }
            if (command.equals("show grid")) {
                if (!isTournamentCreated()) return "Tournament is not created";
                executeCommand = new ShowGridCommand(tournament);
            }
            if (command.equals("report")) {
                if (!isTournamentCreated()) return "Tournament is not created";
                executeCommand = new ReportCommand(tournament);
            }
            executeResult = executeCommand.execute();
        } catch (Exception e) {
            if (e.getMessage() == null)
                return "Target object are not exist";
            return e.getMessage();
        }
        if (!executeResult) return "FAIL";
        return "DONE";
    }


    private Boolean isSettingsConfigured() {
        return tournamentSettings != null;
    }

    private Boolean isTournamentCreated() {
        return tournament != null;
    }

    public void setTournamentSettings(TournamentSettings tournamentSettings) {
        this.tournamentSettings = tournamentSettings;
    }

    public void setTimeSettings(TimeSettings timeSettings) {
        this.timeSettings = timeSettings;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public PlayerDispatcher getPlayerDispatcher() {
        return playerDispatcher;
    }

    public LocationDispatcher getLocationDispatcher() {
        return locationDispatcher;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public TournamentSettings getTournamentSettings() {
        return tournamentSettings;
    }

    public TimeSettings getTimeSettings() {
        return timeSettings;
    }

    public Tournament getTournament() {
        return tournament;
    }
}
