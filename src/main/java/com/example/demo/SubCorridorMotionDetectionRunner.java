package com.example.demo;

import com.example.demo.model.corridor.Corridor;


public class SubCorridorMotionDetectionRunner implements Runnable,MotionObserver{

    private Corridor corridor;
    private boolean isMotionDetected;


    public SubCorridorMotionDetectionRunner(Corridor corridor){

        this.corridor = corridor;
    }

    @Override
    public void setMotionStatus(boolean isMotionDetected) {

        this.isMotionDetected = isMotionDetected;
    }

    @Override
    public void run() {

        long currentTime = System.currentTimeMillis();

        while(true){
            long timeNow = System.currentTimeMillis();
            if(timeNow - currentTime >= (60 * 1000 * 5)){
                if(!isMotionDetected ) {
                    corridor.changeLightPowerStatusToOFF();
                    corridor.changeACPowerStatusToON();
                }
                else{
                    isMotionDetected = false;
                }
                currentTime = timeNow;
            }
        }

    }



}
