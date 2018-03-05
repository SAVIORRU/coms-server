package com.saviorru.comsServer.model;

public class Points {

    private int pointsFirstSide;
    private int pointsSecondSide;

    Points(){
        this.pointsFirstSide = 0;
        this.pointsSecondSide = 0;
    }

    void setPoints(int pointsFirstSide,int pointsSecondSide) throws Exception {
        if(pointsFirstSide < 0 || pointsSecondSide < 0) throw new Exception("The number of points is indicated by a negative number");
        this.pointsFirstSide = pointsFirstSide;
        this.pointsSecondSide = pointsSecondSide;
    }

    int getPointsFirstSide() {
        return this.pointsFirstSide;
    }

    int getPointsSecondSide() {
        return this.pointsSecondSide;
    }
}
