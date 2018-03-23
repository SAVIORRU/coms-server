package com.saviorru.comsserver.cly.command;

public class TestCommand implements Command {


    public TestCommand() {
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() {

        System.out.print("pong");
        return true;
    }

    @Override
    public String nameCommand() {
        return "ping";
    }

    @Override
    public String commandFormat() {
        return null;
    }
}
