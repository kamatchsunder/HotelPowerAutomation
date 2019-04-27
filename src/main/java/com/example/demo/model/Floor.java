package com.example.demo.model;

import com.example.demo.model.corridor.MainCorridor;
import com.example.demo.model.corridor.SubCorridor;

import java.util.HashMap;
import java.util.Map;


public class Floor {

    private int id;

    private int numberOfMainCorridorsPerFloor;
    private int numberOfSubCorridorsPerFloor;

    private Map<Integer, MainCorridor> mainCorridors = new HashMap<>();
    private Map<Integer, SubCorridor> subCorridors = new HashMap<>();


    public Floor(int id, int mainCorridorsPerFloor, int subCorridorsPerFloor) {

        this.id = id;
        numberOfMainCorridorsPerFloor = mainCorridorsPerFloor;
        numberOfSubCorridorsPerFloor = subCorridorsPerFloor;

        buildMainCorridor(numberOfMainCorridorsPerFloor);
        buildSubCorridor(numberOfSubCorridorsPerFloor);

    }


    private void buildMainCorridor(int numberOfMainCorridorsPerFloor) {

        for (int i = 0; i < numberOfMainCorridorsPerFloor; i++) {

            mainCorridors.put(i + 1, new MainCorridor(i + 1));
        }

    }


    private void buildSubCorridor(int numberOfSubCorridorsPerFloor) {

        for (int i = 0; i < numberOfSubCorridorsPerFloor; i++) {

            subCorridors.put(i + 1, new SubCorridor(i + 1));
        }
    }


    public int getId() {

        return id;
    }


    public Map<Integer, MainCorridor> getMainCorridors() {

        return mainCorridors;
    }


    public Map<Integer, SubCorridor> getSubCorridors() {

        return subCorridors;
    }


    @Override
    public int hashCode() {

        return id;
    }


    @Override
    public boolean equals(Object object) {

        if (object instanceof Floor) {

            Floor floor = (Floor) object;

            if (floor.getId() == this.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {

        return "";
    }

}
