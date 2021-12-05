package com.company.strategy;

import com.company.Floor;
import com.company.Lift;

import java.util.ArrayList;

public class TakeNewStrategy implements IElevatorStrategy{
    @Override
    public void LoadPassengers(Lift lift, Floor floor){
        floor.AddPassLift(lift);
    }
}
