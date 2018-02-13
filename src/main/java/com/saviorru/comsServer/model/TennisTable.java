package com.saviorru.comsServer.model;

public class TennisTable {

    private int id;
    private int number;
    private String location;

    public TennisTable(int id) {
        this.id = id;
    }

    public TennisTable(int id, int number, String location) {
        this.id = id;
        this.number = number;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "TennisTable{" +
                "id=" + id +
                ", number=" + number +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TennisTable that = (TennisTable) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
