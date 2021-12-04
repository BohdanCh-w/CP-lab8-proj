package com.company;

import com.company.strategy.IElevatorStrategy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Lift {
    private Integer maxWeight;
    private Integer maxPeopleCount;
    private Floor currentFloor;
    private Floor destinationFloor;
    private ArrayDeque<Passanger> liftPassangers;
    private IElevatorStrategy strategy;
    private Integer currentWeight;

    public Lift(Integer mv, Integer mpc){
        this.maxWeight = mv;
        this.maxPeopleCount = mpc;
        this.currentFloor = Emulation.getInstance().getBuilding().getFloorList().get(0);
        this.liftPassangers = new ArrayDeque<>();
    }

    public void MoveElevator(){

    }

    //їзда до поверху призначення
    public void moveToFloor(Floor f){
        this.destinationFloor = f;
        while (this.currentFloor!=this.destinationFloor){
            moveToNextDoorFloor(Emulation.getInstance().getBuilding().getFloorList(), this.currentFloor.getFloorNumber()-this.destinationFloor.getFloorNumber());
        }
    }
    //strategy 1
    public void notifyFloor(){
        if (destinationFloor == currentFloor){
            currentFloor.AddPassLift(this);
        };
    }
    //їзда до наступного поверху
    public void moveToNextDoorFloor(ArrayList<Floor> floorList, Integer direction){
        if (direction>0){
            this.currentFloor = floorList.stream().
                    filter((el)->(el.getFloorNumber() - currentFloor.getFloorNumber())==1).collect(Collectors.toList()).get(0);
        }
        else if (direction<0){
            this.currentFloor = floorList.stream().
                    filter((el)->(el.getFloorNumber() - currentFloor.getFloorNumber())==-1).collect(Collectors.toList()).get(0);
        }
        notifyFloor();
    }
    public void leaveLift(){
        //liftPassangers.stream().filter((el)->el.getDestinationFloor().equals(this.destinationFloor));
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
    public ArrayDeque<Passanger> getLiftPassangers() {
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

    public IElevatorStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(IElevatorStrategy strategy) {
        this.strategy = strategy;
    }
    public Integer getMaxWeight() {
        return maxWeight;
    }
    public Integer getMaxPeopleCount() {
        return maxPeopleCount;
    }
}
