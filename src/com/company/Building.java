package com.company;

import java.util.ArrayList;

public class Building {
    private ArrayList<Floor> floorList;
    private ArrayList<Lift> liftList;

    public Building(){
        this.floorList = new ArrayList<>();
        this.liftList = new ArrayList<>();
    }

    public Building(int floorCount, int liftCount){
        this.floorList = new ArrayList<>();
        for(int i=0; i<floorCount; i++){
            floorList.add(new Floor(i));
        }

        this.liftList = new ArrayList<>();
        for(int i=0; i<liftCount; i++){
            liftList.add(new Lift());
        }
    }

    public ArrayList<Floor> getFloorList() {
        return floorList;
    }

    public ArrayList<Lift> getLiftList() {
        return liftList;
    }
}
