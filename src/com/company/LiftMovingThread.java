package com.company;

import com.company.logger.LogLvl;

import java.util.Timer;
import java.util.TimerTask;
import static com.company.Program.logger;

public class LiftMovingThread extends Thread{
    private Lift lift;
    private int liftNumber;
    private boolean isActive = false;

    Timer liftMoving = new Timer();
    TimerTask liftTask = new TimerTask() {
        @Override
        public void run() {
            if(Emulation.getInstance().getState() != Emulation.State.STOPPED && isActive){
                lift.MoveElevator();
                Emulation.getInstance().getUi().Building()
                        .MoveLift(liftNumber, lift.getDestinationFloor().getFloorNumber());
            }
        }
    };

    LiftMovingThread(Lift lift, int liftNumber){
        this.lift = lift;
        this.liftNumber = liftNumber;
        this.isActive = true;
    }

    @Override
    public void run() {
        while(isActive){
            lift.MoveElevator();
            try{
                Thread.currentThread().sleep(lift.getSpeed() * 1000);
            }
            catch (Exception threadException){
                logger.Log("Thread problem", LogLvl.LOG_ERROR);
            }
            Emulation.getInstance().getUi().Building()
                    .MoveLift(liftNumber, lift.getDestinationFloor().getFloorNumber());
            logger.Log(String.format("Lift %s moved from %s to %s", liftNumber, lift.getCurrentFloor().getFloorNumber(), lift.getDestinationFloor().getFloorNumber()), LogLvl.LOG_FILE);
        }
        //liftMoving.schedule(liftTask, lift.getSpeed() * 1000, lift.getSpeed() * 1000);
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
