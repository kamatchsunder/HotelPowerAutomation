package com.example.demo.controller;

import com.example.demo.model.Hotel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
public class SubCorridorMotionControllerImplTest {

    SubCorridorMotionControllerImpl target;

    Hotel hotel;

    @Before
    public void setUp()
    {
        hotel = Hotel.getInstance();
        hotel.setHotelProperties(1,1,1);
        target = new SubCorridorMotionControllerImpl();
    }

    @Test
    public void testMotionInput(){

        System.out.println(target.motionInput(1,1));
    }
}
