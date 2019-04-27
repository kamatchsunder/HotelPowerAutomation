package com.example.demo;

import com.example.demo.model.Hotel;
import com.example.demo.visitor.PowerConsumptionVisitor;


public class PowerConsumptionCalculatorRunner implements Runnable{

    PowerConsumptionVisitor powerConsumptionVisitor;


    public PowerConsumptionCalculatorRunner(PowerConsumptionVisitor powerConsumptionVisitor){

        this.powerConsumptionVisitor = powerConsumptionVisitor;

    }
    @Override
    public void run() {

        Hotel hotel = Hotel.getInstance();


        while(true){

            if(hotel.getTotalPowerConsumptionPerFloor(powerConsumptionVisitor) >= hotel.getThresholdPowerUnit()){

                hotel.getFloorMap().forEach((floorNumber, floor) -> {

                    floor.getSubCorridors().forEach((subCorridorNumber, subCorridor) -> {

                        subCorridor.changeACPowerStatusToOFF();

                    });

                });

            }
        }
    }
}
