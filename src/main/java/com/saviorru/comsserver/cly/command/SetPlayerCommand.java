package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.model.Location;
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
    private LocalDate birthDate;

    public SetPlayerCommand(PlayerDispatcher playerDispatcher, List<String> arguments, LocalDate birthDate) {
        this.playerDispatcher = playerDispatcher;
        this.arguments = arguments;
        this.birthDate = birthDate;
    }

    @Override
    public Boolean execute(){
        try {
            playerDispatcher.addPlayer(new Player(arguments.get(0), arguments.get(1), birthDate));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
