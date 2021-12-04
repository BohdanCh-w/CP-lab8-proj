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

        int destinationNumber = random.nextInt(building.getFloorList().size() -1);
        Floor destinationFloor = building.getFloorList().stream().filter(floor -> floor.floorNumber == destinationNumber)
                .collect(Collectors.toList()).get(0);

        int weight = random.nextInt(50) + 50;
        return new Passanger(1, destinationFloor, weight);
    }

    private Lift ChooseLift(){
        var first = queue.entrySet().iterator().next();

        Lift lift = first.getKey();
        int min = first.getValue().size();

        for(var temp : queue.entrySet()) {
            if(min > temp.getValue().size()){
                min = temp.getValue().size();
                lift = temp.getKey();
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

    public void RemovePassLift(Lift l, Passanger passanger){
         var temp = l.getLiftPassangers().stream().toList();
         temp.remove
        l.setCurrentWeight(l.getCurrentWeight() - passanger.getWeight());
    }
    // посадка пасажира з черги у певний ліфт
    public void AddPassLift(Lift l){
        while (!queue.get(l).isEmpty() &&
                l.getMaxPeopleCount() > l.getLiftPassangers().stream().count() &&
                l.getMaxWeight() > l.getCurrentWeight()-queue.get(l).getFirst().getWeight()) {
            var pas = queue.get(l).getFirst();
            l.getLiftPassangers().add(pas);
            l.setCurrentWeight((int) (l.getCurrentWeight()+pas.getWeight()));
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
