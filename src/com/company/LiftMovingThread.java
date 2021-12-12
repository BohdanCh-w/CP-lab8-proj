package com.company;

import java.util.Timer;
import java.util.TimerTask;

public class LiftMovingThread extends Thread{
    private Lift lift;
    private boolean isActive = false;

    Timer liftMoving = new Timer();
    TimerTask liftTask = new TimerTask() {
        @Override
        public void run() {
            if(Emulation.getInstance().getState() != Emulation.State.STOPPED && isActive){
                lift.MoveElevator();
            }
        }
    };

    LiftMovingThread(Lift lift){
        this.lift = lift;
        this.isActive = true;
    }

    @Override
    public void run() {
        while(isActive){
            lift.MoveElevator();
        }
    }

    public boolean terminate(){
        if(Thread.currentThread().isAlive()){
            this.isActive = false;
            return true;
        }
        else{
            return false;
        }
    }
}
