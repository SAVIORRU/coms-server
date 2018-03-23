package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.tournament.Tournament;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.List;

public class SetPlayerCommand implements Command {

    private PlayerDispatcher playerDispatcher;
    private Pair<String, List<String>> arguments;

    public SetPlayerCommand(PlayerDispatcher playerDispatcher, Pair<String, List<String>> arguments) {
        this.playerDispatcher = playerDispatcher;
        this.arguments = arguments;
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() throws Exception {
        if (arguments.getKey().equals(nameCommand())) {
            playerDispatcher.addPlayer(new Player(arguments.getValue().get(0), arguments.getValue().get(1),
                    LocalDate.of(Integer.parseInt(arguments.getValue().get(2).substring(0, 3)),
                            Integer.parseInt(arguments.getValue().get(2).substring(5, 6)),
                            Integer.parseInt(arguments.getValue().get(2).substring(8, 9)))));
            return true;
        }
        return false;
    }

    @Override
    public String nameCommand() {
        return "set player";
    }

    @Override
    public String commandFormat() {
        return "command: first name, second name, yyyy-mm-dd";
    }
}
