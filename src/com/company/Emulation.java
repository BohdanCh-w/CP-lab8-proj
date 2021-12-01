package com.company;

import java.util.ArrayList;

public class Emulation {
    private Building building;
    private Boolean state;
    private Integer spawnSpeed;
    private Integer liftSpeed;
    private ArrayList<Thread> liftThreads;
    private static Emulation emulation;

    private Emulation ( Integer _spawnSpeed, Integer _liftSpeed){
        this.building = new Building();
        this.state = false;
        this.spawnSpeed = _spawnSpeed;
        this.liftThreads = new ArrayList<>();
        this.liftSpeed = _liftSpeed;
    }

    public static Emulation getInstance(Integer ss, Integer ls) {
        if(emulation == null)
            emulation = new Emulation(ss,ls);
        return emulation;
    }
    public void SpawnPassenger(){

    }
    public void Stop(){

    }
    public void Resume(){

    }

    public void setSpawnSpeed(Integer spawnSpeed) {
        this.spawnSpeed = spawnSpeed;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public void setLiftSpeed(Integer liftSpeed) {
        this.liftSpeed = liftSpeed;
    }

    public Boolean getState() {
        return state;
    }

    public Integer getLiftSpeed() {
        return liftSpeed;
    }

    public Integer getSpawnSpeed() {
        return spawnSpeed;
    }

    public ArrayList<Thread> getLiftThreads() {
        return liftThreads;
    }
}
