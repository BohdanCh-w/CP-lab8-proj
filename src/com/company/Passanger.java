package com.company;

<<<<<<< HEAD
public class Passanger implements Prototype{
    private int id;
    private ModelType picture;
    private int destinationFloor;
    private double weight;

    public Passanger(int id, ModelType picture, int destinationFloor, double weight) {
        this.id = id;
        this.picture = picture;
=======
public class Passanger{
    private int id;
    private Floor destinationFloor;
    private int weight;

    public Passanger(int id, Floor destinationFloor, int weight) {
        this.id = id;
>>>>>>> dev
        this.destinationFloor = destinationFloor;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

<<<<<<< HEAD
    public ModelType getPicture() {
        return picture;
    }

    public void setPicture(ModelType picture) {
        this.picture = picture;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
=======
    public Floor getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(Floor destinationFloor) {
>>>>>>> dev
        this.destinationFloor = destinationFloor;
    }

    public double getWeight() {
        return weight;
    }

<<<<<<< HEAD
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public Passanger Clone() {
        return new Passanger(this.id, this.picture, this.destinationFloor, this.weight);
    }
}
=======
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Passanger Clone() {
        return new Passanger(this.id, this.destinationFloor, this.weight);
    }
}
>>>>>>> dev
