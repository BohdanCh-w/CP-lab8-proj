package com.company.strategy;

import com.company.Floor;
import com.company.Lift;

import java.util.ArrayList;

public interface IElevatorStrategy {
    void LoadPassengers(Lift lift, Floor floor);
}
