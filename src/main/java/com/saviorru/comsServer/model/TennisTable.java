package com.saviorru.comsServer.model;

public class TennisTable {

    private Integer id;
    private Integer number;
    private String location;

    public TennisTable(int id) {
        this.id = id;
    }

    public TennisTable(int id, int number, String location) {
        this.id = id;
        this.number = number;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TennisTable that = (TennisTable) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
