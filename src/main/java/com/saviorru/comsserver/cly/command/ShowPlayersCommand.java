package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.model.Player;

public class ShowPlayersCommand implements Command {

    private PlayerDispatcher playerDispatcher;

    public ShowPlayersCommand(PlayerDispatcher playerDispatcher){
        this.playerDispatcher = playerDispatcher;
    }

    @Override
    public Boolean execute(){
        int number = 0;
        for(Player player: playerDispatcher.getAllPlayers()){
            number++;
            System.out.println("Игрок " + number + " : " + player.getFirstName()  + " , "
                            + player.getLastName() + " , " +  " age: " + player.getAge());

        }
        return number > 0;
    }
}
