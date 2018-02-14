package com.saviorru.comsServer.model;


import java.util.HashMap;


public class Player {

    private Integer id;
    private Integer userId;
    private HashMap<Integer, Integer> statistics;

    public Player(Integer id, Integer userId, HashMap<Integer, Integer> statistics) {
        this.id = id;
        this.userId = userId;
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return getId() == player.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HashMap<Integer, Integer> getStatistics() {
        return statistics;
    }

    public void setStatistics(HashMap<Integer, Integer> statistics) {
        this.statistics = statistics;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
