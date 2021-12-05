package com.company.strategy;

import com.company.Floor;
import com.company.Lift;

import java.util.ArrayList;

public class NoNewStrategy implements IElevatorStrategy{
    private int nextFloor;
    private int currentFloor;

    public NoNewStrategy(int nextFloor, int currentFloor) {
        this.nextFloor = nextFloor;
        this.currentFloor = currentFloor;
    }

    public int getNextFloor() {
        return nextFloor;
    }

    public void setNextFloor(int nextFloor) {
        this.nextFloor = nextFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    @Override
    public Floor MoveTo(ArrayList<Floor> floorList, Lift lift) {

    }
}
