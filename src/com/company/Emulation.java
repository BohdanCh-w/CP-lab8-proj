package com.company;

import com.company.ui.UI;

import java.util.ArrayList;

public class Emulation {
    private UI ui;
    private Building building;
    private Boolean state;
    private Integer spawnSpeed;
    private ArrayList<Thread> liftThreads;
    private static Emulation emulation;

    private Emulation (){
        this.building = new Building();
        this.state = false;
        this.spawnSpeed = null;
        this.liftThreads = new ArrayList<>();
        this.ui = new UI();
    }

    public static Emulation getInstance() {
        if(emulation == null)
            emulation = new Emulation();
        return emulation;
    }

    public void Start(){

    }

    public void Stop(){

    }
    public void Resume(){

    }

    public Building getBuilding() {
        return building;
    }
    public Boolean getState() {
        return state;
    }
    public Integer getSpawnSpeed() {
        return spawnSpeed;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
    public void setSpawnSpeed(Integer spawnSpeed) {
        this.spawnSpeed = spawnSpeed;
    }
    public void setState(Boolean state) {
        this.state = state;
    }
}
