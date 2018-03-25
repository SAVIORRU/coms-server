package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.tournament.Tournament;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements Command {

    private List<Pair<String, String>> commandList;
    private List<String> commandNameList;

    public HelpCommand(List<Pair<String, String>> commandList) {
        this.commandList = commandList;
        this.commandNameList = new ArrayList<>();
        fillCommandNameList(commandList);
    }

    private void fillCommandNameList(List<Pair<String, String>> commandList) {
        for (Pair<String, String> command : commandList) {
            commandNameList.add(command.getKey());
        }
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() {
        boolean flag = false;
        for (String name : commandNameList)
            for (Pair<String, String> command : commandList)
                if (command.getKey().equals(name)) {
                    System.out.println("Команда: " + command.getKey() + " , " + "Формат ввода: " + command.getValue());
                    flag = true;
                }
        return flag;
    }

    @Override
    public String nameCommand() {
        return "help";
    }

    @Override
    public String commandFormat() {
        return "command";
    }
}
