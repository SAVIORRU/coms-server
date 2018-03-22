package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.domain.Dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.tournament.Tournament;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.List;

public class SetPlayerCommand extends Command {

    private PlayerDispatcher playerDispatcher;
    private Pair<String, List<String>> arguments;

    public SetPlayerCommand(Tournament tournament, PlayerDispatcher playerDispatcher, Pair<String, List<String>> arguments) {
        super(tournament);
        this.playerDispatcher = playerDispatcher;
        this.arguments = arguments;
    }

    @Override
    public Boolean execute() {
        if(arguments.getKey() == nameCommand()) {
            try {
                playerDispatcher.addPlayer(new Player(arguments.getValue().get(0),arguments.getValue().get(1),
                        LocalDate.of(Integer.parseInt(arguments.getValue().get(2).substring(0,3)),
                                     Integer.parseInt(arguments.getValue().get(2).substring(5,6)),
                                     Integer.parseInt(arguments.getValue().get(2).substring(8,9)))));
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String nameCommand() {
        return "set player";
    }

    @Override
    public String commandFormat() {
        return "command: firstName, secondName, yyyy-mm-dd";
    }
}
