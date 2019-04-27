package com.example.demo;

import com.example.demo.model.corridor.SubCorridor;
import com.example.demo.model.Floor;
import com.example.demo.model.Hotel;
import com.example.demo.visitor.CorridorPowerConsumptionVisitor;


public class SubCorridorPowerStrategy implements HotelPowerStrategy {

    private CorridorPowerConsumptionVisitor corridorConsumptionVisitor;

    public SubCorridorPowerStrategy(CorridorPowerConsumptionVisitor corridorConsumptionVisitor){

        this.corridorConsumptionVisitor = corridorConsumptionVisitor;
    }

    @Override
    public void changePowerStatus(int floorNumber, int corridorNumber, int hour) {

        Hotel hotel = Hotel.getInstance();
        Floor floor = hotel.getFloorMap().get(floorNumber);
        SubCorridor subCorridor = floor.getSubCorridors().get(corridorNumber);

        if(hour > 18 || hour < 6) {
            subCorridor.changeLightPowerStatusToON();
        }

        if (hotel.getTotalPowerConsumptionPerFloor(corridorConsumptionVisitor) >= hotel.getThresholdPowerUnit()) {
            subCorridor.changeACPowerStatusToOFF();
        }

    }
}
