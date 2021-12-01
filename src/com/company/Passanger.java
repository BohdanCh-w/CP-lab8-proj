package com.company;

public class Passanger{
    private int id;
    private int destinationFloor;
    private int weight;

    public Passanger(int id, int destinationFloor, int weight) {
        this.id = id;
        this.destinationFloor = destinationFloor;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Passanger Clone() {
        return new Passanger(this.id, this.destinationFloor, this.weight);
    }
}