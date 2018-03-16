package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.domain.Tournament;

public class ShowGridCommand extends Command {

    public ShowGridCommand(Tournament tournament) {
        super(tournament);
    }

    @Override
    public Boolean execute() {
        return null;
    }
}
