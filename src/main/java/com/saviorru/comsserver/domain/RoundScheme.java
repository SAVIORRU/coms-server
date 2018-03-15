package com.saviorru.comsserver.domain;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoundScheme implements Scheme
{

    private List<List<Meet>> toursList;

    public RoundScheme(Integer playersCount) throws Exception
    {
        if (playersCount == null) throw new NullPointerException();
        this.toursList = this.buildScheme(playersCount);
    }

    private void assignMeet(Integer firstNumber, Integer secondNumber) throws Exception {
        for (List<Meet> tour: this.toursList)
        {
            for (Meet meet: tour) {
                Set<Integer> meetSet = new HashSet<>();
                meetSet.add(meet.getFirstNumber());
                meetSet.add(meet.getSecondNumber());
                Set<Integer> paramSet = new HashSet<>();
                paramSet.add(firstNumber);
                paramSet.add(secondNumber);
                if (meetSet.equals(paramSet)) {
                    meet.assign();
                    return;
                }
            }

        }
        throw new Exception("Cant find specified pair");

    }
    private List<List<Meet>> buildScheme(Integer playersCount) throws  Exception
    {
        List<List<Meet>> toursList = new ArrayList<>();
        for (int i = 0; i < playersCount - 1; i++) {
            ArrayList<Meet> tour = new ArrayList<>();
            for (int j = i; j < playersCount; j++) {
                if (i == j) continue;
                Meet meet = new Meet(i, j);
                tour.add(meet);
            }
            toursList.add(tour);
        }
        return toursList;
    }

    @Override
    public List<Pair<Integer, Integer>> getAllPairs() {
        List<Pair<Integer, Integer>> pairList = new ArrayList<>();
        for (List<Meet> tour: this.toursList)
        {
            for (Meet meet: tour) {

                pairList.add(new Pair<>(meet.firstNumber, meet.secondNumber));
                }
            }
        return pairList;
    }

    @Override
    public Pair<Integer, Integer> getNextUnplayedPair() throws Exception {
        for (List<Meet> tour: this.toursList)
        {
            for (Meet meet: tour) {
                if (!(meet.isAssigned())){
                    meet.assign();
                    return new Pair<>(meet.firstNumber, meet.secondNumber);
                }
            }
        }
        return null;
    }

    @Override
    public List<Pair<Integer, Integer>> getAllPairsInTour(Integer tourNumber) throws Exception {
        if (tourNumber == null) throw new NullPointerException();
        if (tourNumber > this.toursList.size()-1 || tourNumber < 0) throw new ArrayIndexOutOfBoundsException();
        List<Pair<Integer, Integer>>  tour = new ArrayList<>();
        for (Meet meet: this.toursList.get(tourNumber))
        {
            tour.add(new Pair<>(meet.firstNumber, meet.secondNumber));
        }
        return tour;
    }

    @Override
    public void updateScheme(List<Integer> winnersList) {

    }


    private class Meet {
        private Integer firstNumber;
        private Integer secondNumber;
        private Boolean assigned;

        public Boolean isAssigned() {
            return assigned;
        }

        public Meet(Integer firstNumber, Integer secondNumber) throws Exception {
            if ((firstNumber == null) || (secondNumber == null))
                throw new NullPointerException();
            if (firstNumber.equals(secondNumber))
                throw new Exception("Duplicate numbers in one meet is not allowed");
            this.firstNumber = firstNumber;
            this.secondNumber = secondNumber;
            this.assigned = false;
        }

        public Integer getFirstNumber() {
            return this.firstNumber;
        }

        public Integer getSecondNumber() {
            return this.secondNumber;
        }

        public void assign() throws Exception {
            if (this.isAssigned())
                throw new Exception("Meet is already assigned");
            this.assigned = true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Meet meet = (Meet) o;

            if (!getFirstNumber().equals(meet.getFirstNumber())) return false;
            return getSecondNumber().equals(meet.getSecondNumber());
        }
    }
}

