package com.saviorru.comsserver.domain;

import java.util.*;

public class PlayerDispatcher {

    private Map<Player, Integer> playersNumbersMap;

    public PlayerDispatcher()
    {
        this.playersNumbersMap = new HashMap<>();
    }

    public void addPlayer(Player player) throws Exception
    {
        if (player == null) throw new NullPointerException();
        if (playersNumbersMap.containsKey(player)) throw new Exception("Player already exist in dispatcher");
        for (int i =0; i  < playersNumbersMap.entrySet().size()+1; i++)
        {
            if (!(playersNumbersMap.values().contains(i+1)))
            {playersNumbersMap.put(player, i+1);
                break;}
        }
    }
    public void addPlayers(List<Player> playersList) throws Exception
    {
        if (playersList == null) throw new NullPointerException();
        for (Player player: playersList)
        {
            this.addPlayer(player);
        }

    }
    public List<Integer> getAllPlayersNumbers()
    {

        return new ArrayList<Integer>(this.playersNumbersMap.values());
    }
    public Integer getPlayerNumber(Player player) throws Exception
    {
        if (player == null) throw new NullPointerException();
        if (!(playersNumbersMap.containsKey(player))) throw new Exception("Can't find specified player");
        return playersNumbersMap.get(player);
    }
    public List<Player> getAllPlayers()
    {
        return new ArrayList<>(this.playersNumbersMap.keySet());
    }
    public Player getPlayerByNumber(Integer playerNumber) throws Exception
    {
        if (playerNumber == null ) throw new NullPointerException();
        if (!(playersNumbersMap.containsValue(playerNumber))) throw new Exception("Can't find specified number");
        for (Player player: this.playersNumbersMap.keySet())
        {
            if (this.playersNumbersMap.get(player) == playerNumber)
                return player;
        }
        return null;
    }

}
