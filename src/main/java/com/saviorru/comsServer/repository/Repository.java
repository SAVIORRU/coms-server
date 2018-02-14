package com.saviorru.comsServer.repository;
import com.saviorru.comsServer.model.IRepositoryInteractor;
import com.saviorru.comsServer.model.Player;
import com.saviorru.comsServer.model.User;

import java.util.HashMap;

public class Repository implements IRepositoryInteractor {
    private HashMap<Integer, User> usersTable;
    private HashMap<Integer, Player> playersTable;

    public Repository() {
    }

    public HashMap<Integer, User> getUsersTable() {
        return usersTable;
    }

    public void setUsersTable(HashMap<Integer, User> usersTable) {
        this.usersTable = usersTable;
    }

    public HashMap<Integer, Player> getPlayersTable() {
        return playersTable;
    }

    public void setPlayersTable(HashMap<Integer, Player> playersTable) {
        this.playersTable = playersTable;
    }

    @Override
    public void createUserRecord(User user) {
        this.usersTable.put(user.getId(), user);

    }

    @Override
    public void createPlayerRecord(Player player) {
        this.playersTable.put(player.getId(),player);

    }

    @Override
    public Integer getNextUserId() {
        Integer nextId = 0;
        while (this.usersTable.keySet().contains(nextId))
        {
            nextId+=1;
        }
        return nextId;
    }

    @Override
    public Integer getNextPlayerId() {
        Integer nextId = 0;
        while (this.playersTable.keySet().contains(nextId))
        {
            nextId+=1;
        }
        return nextId;
    }
}
