package com.company.strategy;

import com.company.Floor;
import com.company.Lift;

public interface IElevatorStrategy {
    void LoadPassengers(Lift lift, Floor floor);
}
