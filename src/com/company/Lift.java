package com.company;

import com.company.strategy.IElevatorStrategy;
import com.company.strategy.NoNewStrategy;

import java.util.ArrayList;
import java.util.stream.Collectors;
import static com.company.Program.logger;
import com.company.logger.LogLvl;

public class Lift {
    private Integer maxWeight;
    private Integer maxPeopleCount;
    private Floor currentFloor;
    private Floor destinationFloor;
    private ArrayList<Passanger> liftPassengers;
    private IElevatorStrategy strategy;
    private Integer currentWeight;
    private boolean isMoving;
    private Integer speed;

    public Lift() {
          maxWeight = 400;
          maxPeopleCount = 5;
          liftPassengers = new ArrayList<>();
          strategy = new NoNewStrategy();
          currentWeight = 0;
          isMoving = false;
          speed = 5;
    }

    public Lift(Integer mv, Integer mpc, boolean isMoving, int speed){
        this.maxWeight = mv;
        this.maxPeopleCount = mpc;
        this.isMoving = isMoving;
        this.speed = speed;
        this.currentFloor = Emulation.getInstance().getBuilding().getFloorList().get(0);
        this.liftPassengers = new ArrayList<>();
    }

    public void MoveElevator(){
        if(liftPassengers.size() != 0){
            logger.Log(String.format("Lift %s moved from %s to %s", 
                Emulation.getInstance().getBuilding().getLiftList().indexOf(this), 
                this.getCurrentFloor().getFloorNumber(), 
                this.getDestinationFloor().getFloorNumber()), LogLvl.LOG_CONSOLE);
                this.moveToFloor(liftPassengers.get(0).getDestinationFloor());
        }
        else{
            if(currentFloor.getQueue().get(this).size() == 0){
                Floor takePass = NoPassFloor();
                this.moveToFloor(takePass);
                logger.Log(String.format("Lift %s moved from %s to %s",
                        Emulation.getInstance().getBuilding().getLiftList().indexOf(this),
                        this.getCurrentFloor().getFloorNumber(),
                        this.getDestinationFloor().getFloorNumber()), LogLvl.LOG_CONSOLE);
                this.moveToFloor(liftPassengers.get(0).getDestinationFloor());
            }
            else{
                currentFloor.AddPassLift(this);
                logger.Log(String.format("Lift %s waiting for passangers at %s",
                        Emulation.getInstance().getBuilding().getLiftList().indexOf(this),
                        this.getCurrentFloor().getFloorNumber()), LogLvl.LOG_CONSOLE);
            }
        }
    }

    private Floor NoPassFloor(){
        Floor takePass = currentFloor;
        for(var floor : Emulation.getInstance().getBuilding().getFloorList()){
            for(var que : floor.getQueue().entrySet()){
                if(que.getValue().size() > 0){
                    takePass = floor;
                    return takePass;
                }
            }
        }
        return takePass;
    }

    //їзда до поверху призначення
    public void moveToFloor(Floor f){
        this.destinationFloor = f;
        isMoving = true;
        while (this.currentFloor!=this.destinationFloor){
            moveToNextDoorFloor(Emulation.getInstance().getBuilding().getFloorList(), this.destinationFloor.getFloorNumber() - this.currentFloor.getFloorNumber());
        }
        isMoving = false;
    }
    //strategy 1
    public void notifyFloor(){
        if (destinationFloor == currentFloor){
            destinationFloor.RemovePassLift(this);
            strategy.LoadPassengers(this, destinationFloor);
        };
    }
    //їзда до наступного поверху
    public void moveToNextDoorFloor(ArrayList<Floor> floorList, Integer direction){
        if (direction>0){
            this.currentFloor = floorList.stream().
            filter((el)->(el.getFloorNumber() - currentFloor.getFloorNumber())==1).collect(Collectors.toList()).get(0);
        }
        else if (direction<0){
            this.currentFloor = floorList.stream().
            filter((el)->(el.getFloorNumber() - currentFloor.getFloorNumber())==-1).collect(Collectors.toList()).get(0);
        }
        Emulation.getInstance().getUi().Building().AnimateLift(
                Emulation.getInstance().getBuilding().getLiftList().indexOf(this),
                this.currentFloor.getFloorNumber(), this.getSpeed() * 200);
        notifyFloor();
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public Floor getDestinationFloor() {
        return destinationFloor;
    }

    public Integer getCurrentWeight() {
        return currentWeight;
    }

    public ArrayList<Passanger> getLiftPassengers() {
        return liftPassengers;
    }

    public void setLiftPassengers(ArrayList<Passanger> liftPassengers) {
        this.liftPassengers = liftPassengers;
    }

    public void setCurrentWeight(Integer currentWeight) {
        this.currentWeight = currentWeight;
    }

    public void setDestinationFloor(Floor destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setMaxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setMaxPeopleCount(Integer maxPeopleCount) {
        this.maxPeopleCount = maxPeopleCount;
    }

    public IElevatorStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(IElevatorStrategy strategy) {
        this.strategy = strategy;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public Integer getMaxPeopleCount() {
        return maxPeopleCount;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
