package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.tournament.Tournament;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetPlayerCommand implements Command {

    private PlayerDispatcher playerDispatcher;
    private List<String> arguments;

    public SetPlayerCommand(PlayerDispatcher playerDispatcher, List<String> arguments) {
        this.playerDispatcher = playerDispatcher;
        this.arguments = arguments;
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() throws Exception {

            LocalDate birthDate = null;
            List<String> stringDate = Arrays.asList(arguments.get(2).split("-"));
            birthDate = LocalDate.of(Integer.parseInt(stringDate.get(0)), Integer.parseInt(stringDate.get(1)),
                    Integer.parseInt( stringDate.get(2)));
            playerDispatcher.addPlayer(new Player(arguments.get(0), arguments.get(1), birthDate));
            return true;
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
