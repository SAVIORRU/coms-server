package com.saviorru.comsServer.model;

import java.util.ArrayList;
import java.util.Calendar;

public class MatchesScheldule {
    private Integer id;
    private Calendar startDate;
    private Integer tournamentId;
    private ArrayList<ArrayList<Match>> toursList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchesScheldule that = (MatchesScheldule) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "MatchesScheldule{" +
                "id=" + id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    public ArrayList<ArrayList<Match>> getToursList() {
        return toursList;
    }

    public void setToursList(ArrayList<ArrayList<Match>> toursList) {
        this.toursList = toursList;
    }
}




