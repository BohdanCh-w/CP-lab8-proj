package com.company;

import java.util.*;
import java.util.stream.Collectors;
import static com.company.Program.logger;
import com.company.logger.LogLvl;

public class Floor {
    private Integer floorNumber;
    private HashMap<Lift, ArrayDeque<Passanger>> queue;

    public Floor(Integer fn){
        this.floorNumber = fn;
        this.queue = new HashMap<Lift, ArrayDeque<Passanger>>();
    }

    private Lift ChooseLift(){
        var first = queue.entrySet().iterator().next();

        Lift lift = first.getKey();
        int min = first.getValue().size();

        for(var temp : queue.entrySet()) {
                if (min > temp.getValue().size()) {
                    min = temp.getValue().size();
                    lift = temp.getKey();
                }
        }

        return lift;
    }

    public Lift AddPassengerToQueue(Passanger passanger){
        Lift lift = ChooseLift();

        var que = queue.get(lift);
        que.add(passanger);

        queue.put(lift, que);

        return lift;
    }

    public void RemovePassLift(Lift l){
         ArrayList<Passanger> temp = l.getLiftPassengers();
         var outPassengers = temp.stream().filter(passenger -> passenger.getDestinationFloor().getFloorNumber() == this.getFloorNumber())
                 .collect(Collectors.toList());

         temp.removeAll(outPassengers);
         l.setLiftPassengers(temp);

         int weight = temp.stream().map(Passanger::getWeight).reduce(0, Integer::sum);
         l.setCurrentWeight(weight);
         logger.Log(String.format("Lift %s unloaded at %s", 
            Emulation.getInstance().getBuilding().getLiftList().indexOf(l),
            this.getFloorNumber()), LogLvl.LOG_CONSOLE);
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
            
            try {Thread.sleep(400);} catch (InterruptedException e) {};
            Emulation.getInstance().getUi().Building().changePassangerNumber(
                l.getCurrentFloor().getFloorNumber(), 
                Emulation.getInstance().getBuilding().getLiftList().indexOf(l), -1);
        }
    }

    public void initializeQueue() {
        for(var lift : Emulation.getInstance().getBuilding().getLiftList()){
            this.queue.put(lift, new ArrayDeque<>());
        }
    }

    public HashMap<Lift, ArrayDeque<Passanger>> getQueue() {
        return queue;
    }
    public Integer getFloorNumber() {
        return floorNumber;
    }
    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }
}
