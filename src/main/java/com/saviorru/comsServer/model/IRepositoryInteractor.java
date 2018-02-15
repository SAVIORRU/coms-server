package com.saviorru.comsServer.model;

public interface IRepositoryInteractor {
    public void createUserRecord (User user);
    public void createPlayerRecord (Player player);
    public void createMatchesSchelduleRecord(MatchesScheldule scheldule);
    //public void createTableRecord (TennisTable table);
    public void createMatchRecord (Match match);
    public Integer getNextUserId();
    public Integer getNextPlayerId();
    public Integer getNextMatchId();
    public Integer getNextMatchesSchelduleId();
    //public User getUserById (Integer id);
    //public Player getPlayerById (integer id);
}
