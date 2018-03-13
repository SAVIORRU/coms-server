package com.saviorru.comsserver.domain;

public class Points {

    private int pointsFirstSide;
    private int pointsSecondSide;

    public Points(){
        this.pointsFirstSide = 0;
        this.pointsSecondSide = 0;
    }

    public Points(int pointsFirstSide, int pointsSecondSide) throws Exception {
        if(pointsFirstSide < 0 || pointsSecondSide < 0) throw new Exception("The number of points is indicated by a negative number");
        this.pointsFirstSide = pointsFirstSide;
        this.pointsSecondSide = pointsSecondSide;
    }

    public void setPoints(int pointsFirstSide, int pointsSecondSide) throws Exception {
        if(pointsFirstSide < 0 || pointsSecondSide < 0) throw new Exception("The number of points is indicated by a negative number");
        this.pointsFirstSide = pointsFirstSide;
        this.pointsSecondSide = pointsSecondSide;
    }

    public int getPointsFirstSide() {
        return this.pointsFirstSide;
    }

    public int getPointsSecondSide() {
        return this.pointsSecondSide;
    }
}
