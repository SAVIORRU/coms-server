package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.domain.tournament.Tournament;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand extends Command {

    private List<Command> commandList;
    private List<String> commandNameList;

    public HelpCommand(Tournament tournament, List<Command> commandList) {
        super(tournament);
        this.commandList = commandList;
        this.commandNameList = new ArrayList<>();
        fillCommandNameList(commandList);
    }

    private void fillCommandNameList(List<Command> commandList) {
        for (Command command : commandList) {
            commandNameList.add(command.nameCommand());
        }
    }

    @Override
    public Boolean execute() {
        boolean flag = false;
        for (String name : commandNameList)
            for (Command command : commandList)
                if (command.nameCommand().equals(name)) {
                    System.out.println("Команда: " + command.nameCommand() + " " + "Формат ввода: " + command.commandFormat());
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
