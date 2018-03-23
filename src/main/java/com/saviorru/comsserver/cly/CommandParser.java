package com.saviorru.comsserver.cly;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.*;

import com.saviorru.comsserver.cly.ArgumentType;

public class CommandParser {
    private Map<String, List<ArgumentType>> parsingRules;

    public CommandParser() {
        parsingRules = new HashMap<>();
    }

    public void addParsingRule(String commandName, List<ArgumentType> fieldsType) throws Exception {
        if (commandName.isEmpty()) throw new Exception("Empty command name");
        if (fieldsType == null) throw new Exception("Null argument types list");
        if (this.parsingRules.containsKey(commandName))
            throw new Exception("Parsing rule for this command already exists");
        this.parsingRules.put(commandName, fieldsType);
    }


    public Pair<String, List<String>> parse(String commandLine) throws Exception {
        if (commandLine.isEmpty()) return null;
        List<String> parts = new ArrayList<String>(Arrays.asList(commandLine.split(":")));
        String commandString = parts.get(0);
        commandString = commandString.toLowerCase();
        commandString = commandString.trim();
        List<String> rawArguments = new ArrayList<>();
        if (parts.size() > 1)
            rawArguments = new ArrayList<String>(Arrays.asList(parts.get(1).split(",")));
        if (!(this.parsingRules.containsKey(commandString)))
            throw new Exception("Invalid command");
        List<ArgumentType> argumentTypes = this.parsingRules.get(commandString);
        List<String> parsedArguments = new ArrayList<>();
        if (rawArguments.size() != argumentTypes.size())
            throw new Exception("Invalid arguments count");
        for (int i = 0; i < argumentTypes.size(); i++) {
            switch (argumentTypes.get(i)) {
                case ALPHA:
                    parsedArguments.add(parseAlpha(rawArguments.get(i).trim()));
                    break;
                case DIGIT:
                    parsedArguments.add(parseDigit(rawArguments.get(i).trim()));
                    break;
                case DATE_TIME:
                    parsedArguments.add(parseDateTime(rawArguments.get(i).trim()));
                    break;
                case DATE:
                    parsedArguments.add(parseDate(rawArguments.get(i).trim()));
                    break;
                case ALPHA_DIGIT:
                    parsedArguments.add(parseAlphaDigit(rawArguments.get(i).trim()));
                    break;
            }
        }
        return new Pair<String, List<String>>(commandString, parsedArguments);
    }

    private String parseDigit(String rawField) throws Exception {
        if (rawField == null || rawField.isEmpty()) throw new Exception("String cannot be empty or null");
        if (!(rawField.matches("[0-9]+")))
            throw new Exception("Неверный формат аргумента");
        return rawField;
    }

    private String parseAlpha(String rawField) throws Exception {
        if (rawField == null || rawField.isEmpty()) throw new Exception("String cannot be empty or null");
        if (!(rawField.matches("[A-Za-zА-Яа-я]+")))
            throw new Exception("Неверный формат аргумента");
        return rawField;
    }

    private String parseDateTime(String rawField) throws Exception {
        if (rawField == null || rawField.isEmpty()) throw new Exception("String cannot be empty or null");
        List<String> parts = new ArrayList<String>(Arrays.asList(rawField.split("-")));
        if (parts.size() != 5) throw new Exception("Неверный формат аргумента");
        for (String string : parts) {
            if (string.isEmpty())
                throw new Exception("Неверный формат аргумента");
        }
        if (parts.get(0).length() != 4)
            throw new Exception("Неверный формат аргумента YYYY");
        for (int i = 1; i < parts.size(); i++) {
            if (parts.get(i).length() != 2)
                throw new Exception("Неверный формат аргумента");
        }
        List<Integer> parsedInts = new ArrayList<>();
        for (String string : parts)
            parsedInts.add(Integer.parseInt(parseDigit(string)));
        if (parsedInts.get(0) < 0) throw new Exception("Неверное значение аргумента YYYY");
        if (parsedInts.get(1) < 1 || parsedInts.get(1) > 12) throw new Exception("Неверное значение аргумента MM");
        if (parsedInts.get(2) < 1 || parsedInts.get(2) > 31) throw new Exception("Неверное значение аргумента DD");
        if (parsedInts.get(3) < 0 || parsedInts.get(3) > 23) throw new Exception("Неверное значение аргумента HH");
        if (parsedInts.get(4) < 0 || parsedInts.get(4) > 59) throw new Exception("Неверное значение аргумента MM");
        return rawField;
    }

    private String parseDate(String rawField) throws Exception {
        if (rawField == null || rawField.isEmpty()) throw new Exception("String cannot be empty or null");
        List<String> parts = new ArrayList<String>(Arrays.asList(rawField.split("-")));
        if (parts.size() != 3) throw new Exception("Неверный формат аргумента");
        for (String string : parts) {
            if (string.isEmpty())
                throw new Exception("Неверный формат аргумента");
        }
        if (parts.get(0).length() != 4)
            throw new Exception("Неверный формат аргумента YYYY");
        for (int i = 1; i < parts.size(); i++) {
            if (parts.get(i).length() != 2)
                throw new Exception("Неверный формат аргумента");
        }
        List<Integer> parsedInts = new ArrayList<>();
        for (String string : parts)
            parsedInts.add(Integer.parseInt(parseDigit(string)));
        if (parsedInts.get(0) < 0) throw new Exception("Неверное значение аргумента YYYY");
        if (parsedInts.get(1) < 1 || parsedInts.get(1) > 12) throw new Exception("Неверное значение аргумента MM");
        if (parsedInts.get(2) < 1 || parsedInts.get(2) > 31) throw new Exception("Неверное значение аргумента DD");
        return rawField;
    }

    private String parseAlphaDigit(String rawField) throws Exception
    {
        if (rawField == null || rawField.isEmpty()) throw new Exception("String cannot be empty or null");
        if (!(rawField.matches("[A-Za-zА-Яа-я0-9]+")))
            throw new Exception("Неверный формат аргумента");
        return rawField;

    }
}





