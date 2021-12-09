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
            lift.MoveElevator();
            Emulation.getInstance().getUi().Building()
                    .MoveLift(liftNumber, lift.getDestinationFloor().getFloorNumber());
        }
    };

    LiftMovingThread(Lift lift, int liftNumber){
        this.lift = lift;
        this.liftNumber = liftNumber;
        this.isActive = true;
    }

    @Override
    public void run() {
        while(Emulation.getInstance().getState() != Emulation.State.STOPPED && isActive){
            liftMoving.schedule(liftTask, lift.getSpeed() * 1000);
        }
        logger.Log("Program has started", LogLvl.LOG_FILE);
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
