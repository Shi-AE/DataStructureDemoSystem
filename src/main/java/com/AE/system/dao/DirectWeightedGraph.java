package com.AE.system.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 有向加权图
 *
 * @author A.E.
 * @version 1.0
 */
public class DirectWeightedGraph {
    private int pointNum;
    private int sideNum;
    private Integer source;
    private Integer[] points;
    private HashMap<Integer, ArrayList<DirectWeightedSide>> sides;

    @Override
    public String toString() {
        return "DirectWeightedGraph{" +
                "pointNum=" + pointNum +
                ", sideNum=" + sideNum +
                ", source=" + source +
                ", points=" + Arrays.toString(points) +
                ", sides=" + sides +
                '}';
    }

    public int getPointNum() {
        return pointNum;
    }

    public void setPointNum(int pointNum) {
        this.pointNum = pointNum;
    }

    public int getSideNum() {
        return sideNum;
    }

    public void setSideNum(int sideNum) {
        this.sideNum = sideNum;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer[] getPoints() {
        return points;
    }

    public void setPoints(Integer[] points) {
        this.points = points;
    }

    public HashMap<Integer, ArrayList<DirectWeightedSide>> getSides() {
        return sides;
    }

    public void setSides(HashMap<Integer, ArrayList<DirectWeightedSide>> sides) {
        this.sides = sides;
    }

    public DirectWeightedGraph(int pointNum, int sideNum, Integer source, Integer[] points, HashMap<Integer, ArrayList<DirectWeightedSide>> sides) {
        this.pointNum = pointNum;
        this.sideNum = sideNum;
        this.source = source;
        this.points = points;
        this.sides = sides;
    }

    public DirectWeightedGraph() {
    }
}
