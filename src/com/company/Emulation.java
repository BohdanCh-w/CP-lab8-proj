package com.company;

import com.company.ui.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;

public class Emulation {
    private UI ui;
    private Building building;
    private Boolean state;
    private Integer spawnSpeed;
    private ArrayList<Thread> liftThreads;
    private static Emulation emulation;

    private Timer passengerTimer;
    private SpawnPassengersThread passengerGenerator;

    private Emulation (){
        this.building = new Building();
        this.state = false;
        this.spawnSpeed = null;
        this.liftThreads = new ArrayList<>();
        this.ui = new UI();

        this.passengerTimer = new Timer();
        this.passengerGenerator = new SpawnPassengersThread();

        ui.addOnStart(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(state) {
                    ((JButton)(e.getSource())).setText("Stop");
                    Stop();
                } else {
                    ((JButton)(e.getSource())).setText("Start");
                    Start();
                }
            }
        });

        ui.addOnClose(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Closed");
            }
        });
    }

    public static Emulation getInstance() {
        if(emulation == null)
            emulation = new Emulation();
        return emulation;
    }

    public void Start(){
        state = true;
        passengerTimer.schedule(passengerGenerator, spawnSpeed * 1000, spawnSpeed * 1000);

        for(int i=0; i<building.getLiftList().size(); i++){
            liftThreads.add(new Thread(new LiftMovingThread(building.getLiftList().get(i), i)));
            liftThreads.get(i).start();
        }
    }

    public void Stop(){
        state = false;
        passengerTimer.cancel();

        for(int i=0; i<building.getLiftList().size(); i++){
            if(liftThreads.get(i).isAlive()){
                liftThreads.get(i).interrupt();
            }
        }
    }

    public void Resume(){
        state = true;
        passengerTimer.schedule(passengerGenerator, spawnSpeed * 1000, spawnSpeed * 1000);

        for(int i=0; i<building.getLiftList().size(); i++){
            if(liftThreads.get(i).isInterrupted()){
                liftThreads.get(i).start();
            }
        }
    }

    public UI getUi() {return ui;}
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
