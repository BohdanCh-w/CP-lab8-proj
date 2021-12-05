package com.company;

public class Passanger {
    private int id;
    private Floor destinationFloor;
    private int weight;
    private Floor currentFloor;

    public Passanger(int id, Floor destinationFloor, int weight, Floor currentFloor) {
        this.id = id;
        this.destinationFloor = destinationFloor;
        this.weight = weight;
        this.currentFloor = currentFloor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Floor getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(Floor destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Passanger Clone() {
        return new Passanger(this.id, this.destinationFloor, this.weight, this.currentFloor);
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }
}
