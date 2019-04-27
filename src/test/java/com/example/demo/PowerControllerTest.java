package com.example.demo;

import com.example.demo.controller.CorridorMotionController;
import com.example.demo.controller.SubCorridorMotionControllerImpl;
import com.example.demo.model.Hotel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
public class PowerControllerTest {

    private CorridorMotionController target;

    @Before
    public void setUp(){

        target = new SubCorridorMotionControllerImpl();

        initializeHotel();
    }


    private void initializeHotel() {

        Hotel.getInstance().setHotelProperties(2,1,2);

        System.out.println(Hotel.getInstance().toString());
    }


    @Test
    public void testMotionInCorridorDefault() throws InterruptedException {


        String output = target.motionInput(1,1);


        System.out.println(output);

    }

    @Test
    public void testNoMotionInCorridorAfterFiveMinutes() throws InterruptedException {

        String output = target.motionInput(1,1);
        Thread.sleep(6000L);
        System.out.println(output);
        output = target.motionInput(1,1);
        System.out.println(output);

    }
}
