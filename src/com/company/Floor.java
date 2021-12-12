package com.company;

import java.util.*;
import java.util.stream.Collectors;
import static com.company.Program.logger;
import com.company.logger.LogLvl;

public class Floor {
    private Integer floorNumber;
    private HashMap<Lift, ArrayDeque<Passanger>> queues;

    public Floor(Integer fn){
        this.floorNumber = fn;
        this.queues = new HashMap<Lift, ArrayDeque<Passanger>>();
    }

    private Lift ChooseLift() {
        var lifts = queues.keySet().stream().filter(obj -> obj.getCurrentFloor().getFloorNumber() == this.getFloorNumber()).collect(Collectors.toSet());
        if (lifts.size() == 0) {
            lifts = queues.keySet();
        }

        Lift out = null;
        var min = Integer.MAX_VALUE;
        for(var lift : lifts) {
            if (min > queues.get(lift).size()) {
                min = queues.get(lift).size();
                out = lift;
            }
        }

        return out;
    }

    public Lift AddPassengerToQueue(Passanger passanger){
        Lift lift = ChooseLift();

        var que = queues.get(lift);
        que.add(passanger);

        queues.put(lift, que);

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
        while (!queues.get(l).isEmpty() &&
                l.getMaxPeopleCount() > l.getLiftPassengers().size() &&
                l.getMaxWeight() > l.getCurrentWeight() - queues.get(l).getFirst().getWeight()) {
            var pas = queues.get(l).getFirst();
            l.getLiftPassengers().add(pas);
            l.setCurrentWeight(l.getCurrentWeight() + pas.getWeight());
            queues.get(l).removeFirst();
            
            try {Thread.sleep(400);} catch (InterruptedException e) {};
            Emulation.getInstance().getUi().Building().changePassangerNumber(
                l.getCurrentFloor().getFloorNumber(), 
                Emulation.getInstance().getBuilding().getLiftList().indexOf(l), -1);
        }
    }

    public void initializeQueue() {
        for(var lift : Emulation.getInstance().getBuilding().getLiftList()){
            this.queues.put(lift, new ArrayDeque<>());
        }
    }

    public HashMap<Lift, ArrayDeque<Passanger>> getQueue() {
        return queues;
    }
    public Integer getFloorNumber() {
        return floorNumber;
    }
    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }
}
