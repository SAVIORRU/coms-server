package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.tournament.Tournament;

public interface  Command {

     void backup();
     Boolean execute();
     String nameCommand();
     String commandFormat();
}
