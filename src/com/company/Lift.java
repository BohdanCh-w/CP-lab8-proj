package com.company;

public class Lift {
    private Integer maxWeight;
    private Integer maxWidth;
    private Integer maxLength;
    private Integer maxHeight;
    private Integer maxPeopleCount;
    private Float speed;

    public void moveToFloor(Floor f){

    }

    public void setMaxHeight(Integer maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public void setMaxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setMaxPeopleCount(Integer maxPeopleCount) {
        this.maxPeopleCount = maxPeopleCount;
    }

    public void setMaxWidth(Integer maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Integer getMaxHeight() {
        return maxHeight;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public Integer getMaxPeopleCount() {
        return maxPeopleCount;
    }

    public Integer getMaxWidth() {
        return maxWidth;
    }

    public Float getSpeed() {
        return speed;
    }
}
