package com.company;

import com.company.Building;
import com.company.Emulation;
import com.company.Floor;
import com.company.Passanger;

import java.util.Random;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class SpawnPassengersThread extends TimerTask {
    @Override
    public void run() {
        Building building = Emulation.getInstance().getBuilding();
        Random random = new Random();

        int destinationNumber = random.nextInt(building.getFloorList().size() - 1);
        Floor destinationFloor = building.getFloorList().stream().filter(floor -> floor.getFloorNumber() == destinationNumber)
                .collect(Collectors.toList()).get(0);

        int currentFloorNumber;
        do {
            currentFloorNumber = random.nextInt(building.getFloorList().size() - 1);
        } while (currentFloorNumber == destinationNumber);

        Floor currentFloor = building.getFloorList().get(currentFloorNumber);

        int weight = random.nextInt(50) + 50;

        building.getFloorList().get(building.getFloorList().indexOf(currentFloor))
                .AddPassengerToQueue(new Passanger(currentFloor, destinationFloor, weight));
    }
}
