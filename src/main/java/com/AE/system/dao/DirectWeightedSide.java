package com.AE.system.dao;

public class DirectWeightedSide {
    private int target;
    private int Weight;

    @Override
    public String toString() {
        return "DirectWeightedSide{" +
                "target=" + target +
                ", Weight=" + Weight +
                '}';
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public DirectWeightedSide() {
    }

    public DirectWeightedSide(int target, int weight) {
        this.target = target;
        Weight = weight;
    }
}
