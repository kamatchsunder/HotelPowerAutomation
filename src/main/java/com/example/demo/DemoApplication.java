package com.example.demo;

import com.example.demo.controller.SubCorridorMotionControllerImpl;
import com.example.demo.model.Hotel;


public class DemoApplication {

    public static void main(String[] args) {

        Hotel.getInstance().setHotelProperties(2,1,2);

        String output = new SubCorridorMotionControllerImpl().motionInput(1,1);
        System.out.println(output);
    }

}
