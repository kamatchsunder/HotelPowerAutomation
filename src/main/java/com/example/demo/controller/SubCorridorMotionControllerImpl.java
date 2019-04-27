package com.example.demo.controller;

import com.example.demo.model.Hotel;
import com.example.demo.HotelPowerStrategy;
import com.example.demo.SubCorridorPowerStrategy;
import com.example.demo.visitor.CorridorPowerConsumptionVisitor;

import java.util.Calendar;
import java.util.Date;


public class SubCorridorMotionControllerImpl implements CorridorMotionController {

    private CorridorPowerConsumptionVisitor corridorConsumptionVisitor;
    private HotelPowerStrategy hotelPowerStrategy;

    public SubCorridorMotionControllerImpl(){
        this.corridorConsumptionVisitor = new CorridorPowerConsumptionVisitor();
        hotelPowerStrategy = new SubCorridorPowerStrategy(corridorConsumptionVisitor);
    }

    @Override
    public String motionInput(int floorNumber, int corridorNumber) {

        int hour = getCurrentHour();
        hotelPowerStrategy.changePowerStatus(floorNumber,corridorNumber,hour);
        Hotel hotel = Hotel.getInstance();
        hotel.setMotionStatus(true);
        return Hotel.getInstance().toString();
    }

    private static int getCurrentHour(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
}
