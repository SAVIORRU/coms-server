package com.saviorru.comsServer;

import java.util.HashMap;

public class Repository implements IRepositoryInteractor {
    private HashMap<Integer, User> usersTable;
    private HashMap<Integer, Player> playersTable;
    private HashMap<Integer, Match> matchesTable;
    private HashMap<Integer, MatchesScheldule> matchesScheldulesTable;

    public Repository() {
        this.usersTable = new HashMap<>();
        this.playersTable = new HashMap<>();
        this.matchesTable = new HashMap<>();
        this.matchesScheldulesTable = new HashMap<>();
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

    public HashMap<Integer, Match> getMatchesTable() {
        return matchesTable;
    }

    public void setMatchesTable(HashMap<Integer, Match> matchesTable) {
        this.matchesTable = matchesTable;
    }

    public HashMap<Integer, MatchesScheldule> getMatchesScheldulesTable() {
        return matchesScheldulesTable;
    }

    public void setMatchesScheldulesTable(HashMap<Integer, MatchesScheldule> matchesScheldulesTable) {
        this.matchesScheldulesTable = matchesScheldulesTable;
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
    public void createMatchesSchelduleRecord(MatchesScheldule scheldule) {
        this.matchesScheldulesTable.put(scheldule.getId(), scheldule);
    }

    @Override
    public void createMatchRecord(Match match) {
        this.matchesTable.put(match.getId(),match);
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

    @Override
    public Integer getNextMatchId() {
        Integer nextId = 0;
        while (this.matchesTable.keySet().contains(nextId))
        {
            nextId+=1;
        }
        return nextId;
    }

    @Override
    public Integer getNextMatchesSchelduleId() {
        Integer nextId = 0;
        while (this. matchesScheldulesTable.keySet().contains(nextId))
        {
            nextId+=1;
        }
        return nextId;
    }
}
