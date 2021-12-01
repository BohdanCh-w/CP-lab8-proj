package com.company;

import java.util.ArrayList;

public class Lift {
    private Integer maxWeight;
    private Integer maxPeopleCount;
    private Floor currentFloor;
    private Floor destinationFloor;
    private ArrayList<Passanger> liftPassangers;
    private Integer currentWeight;

    public Lift(Integer mv, Integer mpc){
        this.maxWeight = mv;
        this.maxPeopleCount = mpc;
        this.currentFloor = Emulation.getInstance().getBuilding().getFloorList().get(0);
        this.liftPassangers = new ArrayList<>();
    }
    public void moveToFloor(Floor f){
        this.destinationFloor = f;
        while (this.currentFloor!=this.destinationFloor){
            moveToNextDoorFloor(this.currentFloor.getFloorNumber()-this.destinationFloor.getFloorNumber());
        }
    }
    //strategy 1
    public void notifyFloor(){
        if (destinationFloor == currentFloor){
            currentFloor.update(this);
        };
    }
    public void moveToNextDoorFloor(Integer direction){
        if (direction>0){
            this.currentFloor = (Floor) Emulation.getInstance().getBuilding().getFloorList().stream().
                    filter((el)->(el.getFloorNumber() - currentFloor.getFloorNumber())==1);
        }
        else if (direction<0){
            this.currentFloor = (Floor) Emulation.getInstance().getBuilding().getFloorList().stream().
                    filter((el)->(el.getFloorNumber() - currentFloor.getFloorNumber())==-1);
        }
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public Floor getDestinationFloor() {
        return destinationFloor;
    }

    public Integer getCurrentWeight() {
        return currentWeight;
    }
    public ArrayList<Passanger> getLiftPassangers() {
        return liftPassangers;
    }

    public void setCurrentWeight(Integer currentWeight) {
        this.currentWeight = currentWeight;
    }

    public void setDestinationFloor(Floor destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setMaxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
    }
    public void setMaxPeopleCount(Integer maxPeopleCount) {
        this.maxPeopleCount = maxPeopleCount;
    }
    public Integer getMaxWeight() {
        return maxWeight;
    }
    public Integer getMaxPeopleCount() {
        return maxPeopleCount;
    }
}
