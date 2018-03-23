package com.saviorru.comsserver.cly;

import javafx.util.Pair;

import java.util.*;

public class ConsoleController {
    private RuntimeEnvironment env, backup;
    private CommandParser parser;
    private Map<String, List<ArgumentType>> commandsMap;


    public ConsoleController() throws Exception {
        initCommandsMap();
        env = new RuntimeEnvironment(this.commandsMap);
        parser = new CommandParser();
        for (Map.Entry<String, List<ArgumentType>> entry : this.commandsMap.entrySet()) {
            parser.addParsingRule(entry.getKey(), entry.getValue());
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

    private void initCommandsMap() {
        commandsMap = new HashMap<>();
        commandsMap.put("undo", new ArrayList<>());
        commandsMap.put("start", new ArrayList<>());
        commandsMap.put("finish", new ArrayList<>());
        commandsMap.put("show grid", new ArrayList<>());
        commandsMap.put("show schedule", new ArrayList<>());
        commandsMap.put("show players", new ArrayList<>());
        commandsMap.put("show locations", new ArrayList<>());
        commandsMap.put("set match result", Arrays.asList(ArgumentType.DIGIT, ArgumentType.DIGIT, ArgumentType.DIGIT));
        commandsMap.put("set players", Collections.singletonList(ArgumentType.DIGIT));
        commandsMap.put("set player", Arrays.asList(ArgumentType.ALPHA, ArgumentType.ALPHA, ArgumentType.DATE));
        commandsMap.put("set locations", Collections.singletonList(ArgumentType.DIGIT));
        commandsMap.put("set location", Arrays.asList(ArgumentType.ALPHA, ArgumentType.ALPHA));
        commandsMap.put("create tournament", Collections.singletonList(ArgumentType.ALPHA));
        commandsMap.put("help", new ArrayList<>());
    }

    private void doBackup() {
        backup = new RuntimeEnvironment(env);
    }
}
