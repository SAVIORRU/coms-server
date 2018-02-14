package com.saviorru.comsServer.model;

public interface IRepositoryInteractor {
    public void createUserRecord (User user);
    public void createPlayerRecord (Player player);
    //public void createTableRecord (TennisTable table);
    //public void createMatchRecord (Match match);
    public Integer getNextUserId();
    public Integer getNextPlayerId();
    //public User getUserById (Integer id);
    //public Player getPlayerById (integer id);
}
