package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class Floor {
    private Integer floorNumber;
    //private HashMap<Lift, Boolean> lifts;
    private HashMap<Lift, ArrayDeque<Passanger>> queue;

    public Floor(Integer fn){
        this.floorNumber = fn;
        this.queue = new HashMap<>();
    }

    public HashMap<Lift, ArrayDeque<Passanger>> getQueue() {
        return queue;
    }
    // посадка пасажира з черги у певний ліфт
    public void update(Lift l){
        while (!queue.get(l).isEmpty() &&
                l.getMaxPeopleCount()==l.getLiftPassangers().stream().count() &&
                l.getMaxWeight()<l.getCurrentWeight()-queue.get(l).getFirst().getWeight()) {
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
