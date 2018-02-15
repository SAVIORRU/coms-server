package com.saviorru.comsServer;

public interface IRepositoryInteractor {
    public void createUserRecord(User user);
    public void createPlayerRecord(Player player);
    public void createMatchesSchelduleRecord(MatchesScheldule scheldule);
    //public void createTableRecord (com.saviorru.comsServer.TennisTable table);
    public void createMatchRecord(Match match);
    public Integer getNextUserId();
    public Integer getNextPlayerId();
    public Integer getNextMatchId();
    public Integer getNextMatchesSchelduleId();
    //public com.saviorru.comsServer.User getUserById (Integer id);
    //public com.saviorru.comsServer.Player getPlayerById (integer id);
}
