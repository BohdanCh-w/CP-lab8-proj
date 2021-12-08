package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Floor {
    private Integer floorNumber;
    //private HashMap<Lift, Boolean> lifts;
    private HashMap<Lift, ArrayDeque<Passanger>> queue;

    public Floor(Integer fn){
        this.floorNumber = fn;
        this.queue = new HashMap<>();
    }

    public Passanger CreatePassenger(Building building){
        Random random = new Random();

        int destinationNumber = random.nextInt(building.getFloorList().size() - 1);
        Floor destinationFloor = building.getFloorList().stream().filter(floor -> floor.floorNumber == destinationNumber)
                .collect(Collectors.toList()).get(0);

        int currentFloorNumber;
        do {
            currentFloorNumber = random.nextInt(building.getFloorList().size() - 1);
        } while (currentFloorNumber == destinationNumber);

        Floor currentFloor = building.getFloorList().get(currentFloorNumber);

        int weight = random.nextInt(50) + 50;

        return new Passanger(destinationFloor, weight, currentFloor);
    }

    private Lift ChooseLift(){
        var first = queue.entrySet().iterator().next();

        Lift lift = first.getKey();
        int min = first.getValue().size();

        for(var temp : queue.entrySet()) {
            if(!temp.getKey().isMoving()) {
                if (min > temp.getValue().size()) {
                    min = temp.getValue().size();
                    lift = temp.getKey();
                }
            }
        }

        return lift;
    }

    public void AddPassengerToQueue(Passanger passanger){
        Lift lift = ChooseLift();

        var que = queue.get(lift);
        que.add(passanger);

        queue.put(lift, que);
    }

    public HashMap<Lift, ArrayDeque<Passanger>> getQueue() {
        return queue;
    }

    public void RemovePassLift(Lift l){
         ArrayList<Passanger> temp = l.getLiftPassengers();
         var outPassengers = temp.stream().filter(passenger -> passenger.getCurrentFloor() == this)
                 .collect(Collectors.toList());

         temp.removeAll(outPassengers);
         l.setLiftPassengers(temp);

         int weight = temp.stream().map(Passanger::getWeight).reduce(0, Integer::sum);
         l.setCurrentWeight(weight);
    }
    // посадка пасажира з черги у певний ліфт
    public void AddPassLift(Lift l){
        while (!queue.get(l).isEmpty() &&
                l.getMaxPeopleCount() > l.getLiftPassengers().size() &&
                l.getMaxWeight() > l.getCurrentWeight() - queue.get(l).getFirst().getWeight()) {
            var pas = queue.get(l).getFirst();
            l.getLiftPassengers().add(pas);
            l.setCurrentWeight(l.getCurrentWeight() + pas.getWeight());
            queue.get(l).removeFirst();
        }
    }
    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }
    public Integer getFloorNumber() {
        return floorNumber;
    }
}
