package com.saviorru.comsServer.model;

import java.util.HashMap;

public class User {

    private int id;
    private String name;
    private Player player;

    public User(int id) {
        this.id = id;
        HashMap<Integer, Integer> playerStatistics = new HashMap<Integer, Integer>();
        this.player = new Player(this.id, this, playerStatistics);
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        HashMap<Integer, Integer> playerStatistics = new HashMap<Integer, Integer>();
        this.player = new Player(this.id, this, playerStatistics);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
