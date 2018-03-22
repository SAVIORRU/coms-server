package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.tournament.Tournament;
import javafx.util.Pair;

import java.util.List;

public class CommandManagement {

    private List<Command> commandList;
    private CommandParser commandParser;
    private Command command;
    private Tournament tournament;

    public CommandManagement(Tournament tournament, List<Command> commandList) {
        this.commandList = commandList;
        this.commandParser = commandParser;
        this.tournament = tournament;
    }

    public boolean command(Pair<String, List<String>> commandConsole) {
        if (commandConsole.getKey().equals("help")) {
            return executeCommand(new HelpCommand(tournament, commandList));
        }
        if (commandConsole.getKey().equals("start")) {
            return executeCommand(new StartTournamentCommand(tournament));
        }
        if (commandConsole.getKey().equals("finish")) {
            return executeCommand(new FinishTournamentCommand(tournament));
        }
        if (commandConsole.getKey().equals("show grid")) {
            return executeCommand(new ShowGridCommand(tournament));
        }
        if (commandConsole.getKey().equals("show schedule")) {
            return executeCommand(new ShowScheduleCommand(tournament));
        }
        if (commandConsole.getKey().equals("set players")) {
            try {
                return executeCommand(new SetPlayersCommand(tournament,new CommandParser(),Integer.parseInt(commandConsole.getValue().get(0)),new PlayerDispatcher()));
            } catch (Exception e) {
                System.out.print("Не верный формат команды");
                return false;
            }
        }
        if (commandConsole.getKey().equals("set match result")) {
            try {
                return executeCommand(new SetMatchResultCommand(tournament,Integer.parseInt(commandConsole.getValue().get(0))-1,Integer.parseInt(commandConsole.getValue().get(1)),Integer.parseInt(commandConsole.getValue().get(2))));
            } catch (Exception e) {
                System.out.print("Не верный формат команды");
                return false;
            }
        }
        return false;
    }

    private boolean executeCommand(Command command){
        return command.execute();
    }
}
