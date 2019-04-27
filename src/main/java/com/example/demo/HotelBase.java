package com.example.demo;

import com.example.demo.visitor.PowerConsumptionVisitor;


public interface HotelBase {

    void setHotelProperties(int numberOfFloors, int mainCorridorsPerFloor, int subCorridorsPerFloor);
    void initMonitors();
    int getTotalPowerConsumptionPerFloor(PowerConsumptionVisitor powerConsumptionVisitor);
}
