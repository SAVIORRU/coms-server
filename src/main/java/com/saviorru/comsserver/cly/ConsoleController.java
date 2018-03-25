package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.cly.command.CommandInfo;
import javafx.util.Pair;

import java.util.*;

public class ConsoleController {
    private RuntimeEnvironment env, backup;
    private CommandParser parser;
    private Map<String, CommandInfo> commandsMap;


    public ConsoleController() throws Exception {
        initCommandsMap();
        env = new RuntimeEnvironment(this.commandsMap);
        parser = new CommandParser();
        for (Map.Entry<String, CommandInfo> entry : this.commandsMap.entrySet()) {
            parser.addParsingRule(entry.getKey(), entry.getValue().getArgumentsList());
        }
    }


    public String parseAndExecute(String rawString) {
        Pair<String, List<String>> parsedCommand = null;
        try {
            parsedCommand = parser.parse(rawString);
        } catch (Exception e) {
            if (e.getMessage() == null)
                return "Target object are not exist";
            return e.getMessage();
        }
        if (parsedCommand.getKey() == "undo") {
            if (backup == null) return "RE don' executed any command";
            env = new RuntimeEnvironment(backup);
            return "Returning to backup completed";
        }
        doBackup();
        return env.executeCommand(parsedCommand.getKey(), parsedCommand.getValue());
    }

    private void initCommandsMap() throws Exception {
        commandsMap = new HashMap<>();
        commandsMap.put("undo", new CommandInfo("undo", new ArrayList<>(), "command"));
        commandsMap.put("start", new CommandInfo("start", new ArrayList<>(), "command"));
        commandsMap.put("finish", new CommandInfo("finish", new ArrayList<>(), "command"));
        commandsMap.put("show grid", new CommandInfo("show grid", new ArrayList<>(), "command"));
        commandsMap.put("show schedule", new CommandInfo("show schedule", new ArrayList<>(), "command"));
        commandsMap.put("show players", new CommandInfo("show players", new ArrayList<>(), "command"));
        commandsMap.put("show locations", new CommandInfo("show locations", new ArrayList<>(), "command"));
        commandsMap.put("set match result", new CommandInfo("set match result", Arrays.asList(ArgumentType.DIGIT, ArgumentType.DIGIT, ArgumentType.DIGIT), "command: matchNumber, number, number"));
        commandsMap.put("set player", new CommandInfo("set player", Arrays.asList(ArgumentType.ALPHA_DIGIT, ArgumentType.ALPHA_DIGIT, ArgumentType.DATE), "command: first name, second name, yyyy-mm-dd"));
        commandsMap.put("set location", new CommandInfo("set location", Arrays.asList(ArgumentType.ALPHA_DIGIT, ArgumentType.ALPHA_DIGIT), "command: name location, description"));
        commandsMap.put("set setting", new CommandInfo("set setting",Arrays.asList(ArgumentType.ALPHA_DIGIT, ArgumentType.ALPHA, ArgumentType.DATE_TIME),"command: tournament name, type scheme (olympic/round ...), date start (yyyy-mm-dd-hh-mm)"));
        commandsMap.put("create tournament", new CommandInfo("create tournament",new ArrayList<>(),"command"));
        commandsMap.put("help", new CommandInfo("help",new ArrayList<>(),"command"));
        commandsMap.put("report", new CommandInfo("report",new ArrayList<>(),"command"));
    }

    private void doBackup() {
        backup = new RuntimeEnvironment(env);
    }
}
