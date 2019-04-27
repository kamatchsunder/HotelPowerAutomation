package com.example.demo.model;

import com.example.demo.HotelBase;
import com.example.demo.MotionObserver;
import com.example.demo.PowerConsumptionCalculatorRunner;
import com.example.demo.SubCorridorMotionDetectionRunner;
import com.example.demo.model.corridor.SubCorridor;
import com.example.demo.visitor.CorridorPowerConsumptionVisitor;
import com.example.demo.visitor.PowerConsumptionVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Hotel implements HotelBase {

    private static Hotel ourInstance = new Hotel();

    private int id;
    private int numberOfFloors;
    private int thresholdPowerUnit;

    private Map<Integer, Floor> floorMap = new HashMap<>();
    private List<MotionObserver> motionObservers = new ArrayList<>();
    private ExecutorService executors = Executors.newCachedThreadPool();
    private PowerConsumptionCalculatorRunner powerConsumptionCalculator;


    public static Hotel getInstance() {

        return ourInstance;
    }


    private Hotel() {

    }


    /**
     * This class is used to set the properties of the system and builds it
     *
     * @param numberOfFloors
     * @param mainCorridorsPerFloor
     * @param subCorridorsPerFloor
     */
    @Override
    public void setHotelProperties(int numberOfFloors, int mainCorridorsPerFloor, int subCorridorsPerFloor) {

        this.numberOfFloors = numberOfFloors;
        buildHotelSystem(mainCorridorsPerFloor, subCorridorsPerFloor);
        initMonitors();
    }


    /**
     * This method starts monitors to monitors like total power consumption and motion detection ..
     */
    @Override
    public void initMonitors() {

        for (Floor value : floorMap.values()) {
            value.getSubCorridors().forEach(this::accept);
        }

    }


    /**
     * This method gets the total power consumed by the hotel at a given time.
     * @param powerConsumptionVisitor
     * @return
     */
    @Override
    public int getTotalPowerConsumptionPerFloor(PowerConsumptionVisitor powerConsumptionVisitor) {

        int totalMainCorridorConsumption = 0;
        int totalSubCorridorConsumption = 0;

        for (Map.Entry<Integer, Floor> floorEntry : this.getFloorMap().entrySet()) {

            totalMainCorridorConsumption = this.getFloorMap().get(floorEntry.getKey())
                    .getMainCorridors().values().stream()
                    .mapToInt(mainCorridor ->mainCorridor.accept(powerConsumptionVisitor))
                    .sum();

            totalSubCorridorConsumption = this.getFloorMap().get(floorEntry.getKey())
                    .getSubCorridors().values().stream()
                    .mapToInt(subCorridor -> subCorridor.accept(powerConsumptionVisitor))
                    .sum();
        }

        return totalMainCorridorConsumption + totalSubCorridorConsumption;
    }


    private void accept(Integer integer, SubCorridor subCorridor) {

        SubCorridorMotionDetectionRunner subCorridorMotionDetectionRunner = new SubCorridorMotionDetectionRunner(subCorridor);
        motionObservers.add(subCorridorMotionDetectionRunner);
        executors.execute(subCorridorMotionDetectionRunner);
        powerConsumptionCalculator = new PowerConsumptionCalculatorRunner(new CorridorPowerConsumptionVisitor());
        executors.execute(powerConsumptionCalculator);
    }


    private void buildHotelSystem(int mainCorridorsPerFloor, int subCorridorsPerFloor) {

        for (int i = 0; i < numberOfFloors; i++) {
            floorMap.put(i + 1, new Floor(i + 1, mainCorridorsPerFloor, subCorridorsPerFloor));
        }

        thresholdPowerUnit = (mainCorridorsPerFloor * 15) + (subCorridorsPerFloor * 10);
    }


    public void setMotionStatus(boolean motionStatus) {

        if (motionStatus) {
            motionObservers.forEach(motionObserver -> motionObserver.setMotionStatus(true));
        }

    }


    public Map<Integer, Floor> getFloorMap() {

        return floorMap;
    }


    public int getId() {

        return id;
    }


    public int getThresholdPowerUnit() {

        return thresholdPowerUnit;
    }


    @Override
    public int hashCode() {

        return id;
    }


    @Override
    public boolean equals(Object object) {

        if (object instanceof Hotel) {

            Hotel hotel = (Hotel) object;

            if (hotel.getId() == this.getId()) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer();
        floorMap.forEach(
                (integer, floor) -> {
                    sb.append("\n Floor ").append(integer).append(" ");
                    floor.getMainCorridors().forEach((integer1, mainCorridor) -> {

                        sb.append("\n Main corridor ").append(integer1).append("\t");
                        mainCorridor.getLightMap().forEach((integer2, light) -> {
                            sb.append(" Light ").append(integer2).append("\t").append(light.getPowerStatus());
                        });

                        mainCorridor.getAcMap().forEach((integer2, ac) -> {

                            sb.append(" AC ").append(integer2).append("\t").append(ac.getPowerStatus());
                        });
                    });

                    floor.getSubCorridors().forEach((integer1, subCorridor) -> {

                        sb.append("\n Sub corridor ").append(integer1);
                        subCorridor.getLightMap().forEach((integer2, light) -> {
                            sb.append(" Light ").append(integer2).append("\t").append(light.getPowerStatus());

                        });

                        subCorridor.getAcMap().forEach((integer2, ac) -> {
                            sb.append(" AC ").append(integer2).append("\t").append(ac.getPowerStatus());

                        });
                    });
                }
        );

        return sb.toString();
    }
}
