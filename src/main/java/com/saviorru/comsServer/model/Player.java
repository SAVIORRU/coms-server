package com.saviorru.comsServer.model;


import java.util.HashMap;


public class Player {

    private int id;
    private User user;
    private HashMap<Integer, Integer> statistics;

    public Player(int id, User user, HashMap<Integer, Integer> statistics) {
        this.id = id;
        this.user = user;
        this.statistics = statistics;
    }

    public Player(int id) {
        this.id = id;
    }

    public HashMap<Integer, Integer> getStatistics() {
        return statistics;
    }

    public void setStatistics(HashMap<Integer, Integer> statistics) {
        this.statistics = statistics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
