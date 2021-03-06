package com.company;

import com.company.logger.*;
import com.company.ui.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;

import static com.company.Program.logger;

public class Emulation {
    private UI ui;
    private Building building;
    private State state;
    private Integer spawnSpeed;
    private ArrayList<LiftMovingThread> liftThreads;
    private static Emulation emulation;
    private Timer passengerTimer;
    private SpawnPassengersThread passengerGenerator;

    public enum State{
        INITIALIZED,
        NON_INITIALIZED,
        STOPPED
    }

    private Emulation (){
        this.state = State.NON_INITIALIZED;
        this.liftThreads = new ArrayList<>();
        this.ui = new UI();
        this.spawnSpeed = null;
        this.passengerTimer = new Timer();
        this.passengerGenerator = new SpawnPassengersThread();

        ui.addOnStart(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(state==State.INITIALIZED) {
                    ((JButton)(e.getSource())).setText("Stop");
                    Stop();
                } else if(state==State.NON_INITIALIZED){
                    ((JButton)(e.getSource())).setText("Start");
                    Start();
                } else{
                    ((JButton)(e.getSource())).setText("Resume");
                    Resume();
                }

                logger.Log(String.format("Program state changed to: %s", state), LogLvl.LOG_FILE);
            }
        });

        ui.addOnClose(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(state!=State.NON_INITIALIZED) {
                    passengerTimer.cancel();
                    for(int i=0; i<liftThreads.size();i++){
                        liftThreads.get(i).terminate();
                        try{
                            liftThreads.get(i).join();
                        }
                        catch (Exception exception){
                            logger.Log(String.format("Error while exit: %s", exception.getMessage()), LogLvl.LOG_ERROR);
                        }
                    }
                }
                logger.Log(String.format("Program finished"), LogLvl.LOG_FILE);
                System.exit(0);
            }
        });

        ui.addOnBuild(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBuilding();
            }
        });
    }

    public static Emulation getInstance() {
        if(emulation == null)
            emulation = new Emulation();
        return emulation;
    }

    public void Start(){
        state = State.INITIALIZED;
        passengerTimer.schedule(passengerGenerator, spawnSpeed * 1000, spawnSpeed * 1000);

        for(int i=0; i<building.getLiftList().size(); i++){
            liftThreads.add(new LiftMovingThread(building.getLiftList().get(i)));
            liftThreads.get(i).start();
        }
    }

    public void Stop(){
        state = State.STOPPED;
        passengerTimer.cancel();

        for(int i=0; i<liftThreads.size(); i++){
            if(liftThreads.get(i).isAlive()){
                liftThreads.get(i).interrupt();
            }
        }
    }

    public void Resume(){
        state = State.INITIALIZED;
        passengerTimer.schedule(passengerGenerator, spawnSpeed * 1000, spawnSpeed * 1000);

        for(int i=0; i<liftThreads.size(); i++){
            if(liftThreads.get(i).isInterrupted()){
                liftThreads.get(i).start();
            }
        }
    }

    public UI getUi() {return ui;}
    public Building getBuilding() {
        return building;
    }
    public State getState() {
        return state;
    }
    public Integer getSpawnSpeed() {
        return spawnSpeed;
    }

    public void setBuilding() {
        int floorCount = this.ui.Configuration().getFloorsCount();
        int liftCount = this.ui.Configuration().getLiftsCount();
        this.spawnSpeed = this.ui.Configuration().getPassangerSpawnTime();

        this.building = new Building(floorCount, liftCount);
        for(int i=0; i<building.getLiftList().size(); i++){
            building.getLiftList().get(i).setCurrentFloor(building.getFloorList().get(0));
            building.getLiftList().get(i).setDestinationFloor(building.getFloorList().get(0));
        }

        for(var floor : building.getFloorList()){
            floor.initializeQueue();
        }

        this.ui.Building().CreateBuilding(floorCount, liftCount);
        this.ui.Configuration().SetLiftListForConfig(building.getLiftList());
    }
    public void setSpawnSpeed(Integer spawnSpeed) {
        this.spawnSpeed = spawnSpeed;
    }
    public void setState(State state) {
        this.state = state;
    }
}
