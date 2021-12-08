package com.company;
import java.util.concurrent.atomic.AtomicInteger;

public class Passanger {
    private static AtomicInteger id;
    private Floor destinationFloor;
    private int weight;
    private Floor currentFloor;

    public Passanger(Floor destinationFloor, int weight, Floor currentFloor) {
        id.incrementAndGet();
        this.destinationFloor = destinationFloor;
        this.weight = weight;
        this.currentFloor = currentFloor;
    }

    public int getId() {
        return id.get();
    }

    public static void InitializeId(){
        Passanger.id = new AtomicInteger();
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
        return new Passanger(this.destinationFloor, this.weight, this.currentFloor);
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }
}
