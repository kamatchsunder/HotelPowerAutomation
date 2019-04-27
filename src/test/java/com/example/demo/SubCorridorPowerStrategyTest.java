package com.example.demo;

import com.example.demo.model.Hotel;
import com.example.demo.visitor.CorridorPowerConsumptionVisitor;
import org.junit.Before;
import org.junit.Test;


public class SubCorridorPowerStrategyTest {

    SubCorridorPowerStrategy target;

    Hotel hotel;

    @Before
    public void setUp(){

        hotel = Hotel.getInstance();
        hotel.setHotelProperties(1,1,1);
        CorridorPowerConsumptionVisitor corridorPowerConsumptionVisitor = new CorridorPowerConsumptionVisitor();
        target = new SubCorridorPowerStrategy(corridorPowerConsumptionVisitor);
    }

    @Test
    public void testChangePowerStatus(){
        target.changePowerStatus(1,1,19);

        System.out.println(hotel.toString());
    }
}
