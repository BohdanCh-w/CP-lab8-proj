package com.company;

public class Passanger implements Prototype{
    private int id;
    private ModelType picture;
    private int destinationFloor;
    private double weight;

    public Passanger(int id, ModelType picture, int destinationFloor, double weight) {
        this.id = id;
        this.picture = picture;
        this.destinationFloor = destinationFloor;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        this.destinationFloor = destinationFloor;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public Passanger Clone() {
        return new Passanger(this.id, this.picture, this.destinationFloor, this.weight);
    }
}
