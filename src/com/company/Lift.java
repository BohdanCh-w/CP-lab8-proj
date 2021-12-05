package com.company;

import com.company.strategy.IElevatorStrategy;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Lift {
    private Integer maxWeight;
    private Integer maxPeopleCount;
    private Floor currentFloor;
    private Floor destinationFloor;
    private ArrayList<Passanger> liftPassengers;
    private IElevatorStrategy strategy;
    private Integer currentWeight;

    private boolean isMoving;

    public Lift(Integer mv, Integer mpc, boolean isMoving){
        this.maxWeight = mv;
        this.maxPeopleCount = mpc;
        this.isMoving = isMoving;
        this.currentFloor = Emulation.getInstance().getBuilding().getFloorList().get(0);
        this.liftPassengers = new ArrayList<>();
    }

    public void MoveElevator(){

    }

    //їзда до поверху призначення
    public void moveToFloor(Floor f){
        this.destinationFloor = f;
        isMoving = true;
        while (this.currentFloor!=this.destinationFloor){
            moveToNextDoorFloor(Emulation.getInstance().getBuilding().getFloorList(), this.currentFloor.getFloorNumber()-this.destinationFloor.getFloorNumber());
        }
    }
    //strategy 1
    public void notifyFloor(){
        if (destinationFloor == currentFloor){
            //currentFloor.AddPassLift(this);
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
        //liftPassengers.stream().filter((el)->el.getDestinationFloor().equals(this.destinationFloor));
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

    public ArrayList<Passanger> getLiftPassengers() {
        return liftPassengers;
    }

    public void setLiftPassengers(ArrayList<Passanger> liftPassengers) {
        this.liftPassengers = liftPassengers;
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

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }
}
