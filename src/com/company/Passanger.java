package com.company;
import java.util.concurrent.atomic.AtomicInteger;

public class Passanger {
    private static AtomicInteger id;
    private Floor currentFloor;
    private Floor destinationFloor;
    private int weight;

    public Passanger(Floor currentFloor, Floor destinationFloor, int weight) {
        id.incrementAndGet();
        this.destinationFloor = destinationFloor;
        this.weight = weight;
        this.currentFloor = currentFloor;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }
    public Floor getDestinationFloor() {
        return destinationFloor;
    }
    public int getWeight() {
        return weight;
    }
    public int getId() {
        return id.get();
    }

    public void setDestinationFloor(Floor destinationFloor) {
        this.destinationFloor = destinationFloor;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setCurrentFloor(Floor currentFloor) {this.currentFloor = currentFloor;}

    public static void InitializeId(){Passanger.id = new AtomicInteger();}
    public Passanger Clone() {
        return new Passanger(this.currentFloor, this.destinationFloor, this.weight);
    }
}
