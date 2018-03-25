package com.saviorru.comsserver.cli.command;

public interface  Command {

     void backup();
     Boolean execute() throws Exception;
     String nameCommand();
     String commandFormat();
}
