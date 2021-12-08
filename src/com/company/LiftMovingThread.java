package com.company;

import java.util.Timer;
import java.util.TimerTask;

public class LiftMovingThread implements Runnable{
    private Lift lift;
    private int liftNumber;
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
    }

    @Override
    public void run() {
        while(Emulation.getInstance().getState()){
            liftMoving.schedule(liftTask, lift.getSpeed() * 1000);
        }
    }
}
