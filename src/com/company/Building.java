package com.company;

import java.util.ArrayList;

public class Building {
    private ArrayList<Floor> floorList;
    private ArrayList<Lift> liftList;

    public Building(){
        this.floorList = new ArrayList<>();
        this.liftList = new ArrayList<>();
    }

    public ArrayList<Floor> getFloorList() {
        return floorList;
    }

    public ArrayList<Lift> getLiftList() {
        return liftList;
    }
}
