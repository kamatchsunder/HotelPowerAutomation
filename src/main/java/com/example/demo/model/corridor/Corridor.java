package com.example.demo.model.corridor;

import com.example.demo.model.Floor;
import com.example.demo.model.equipment.AC;
import com.example.demo.model.equipment.Light;

import java.util.HashMap;
import java.util.Map;


public abstract class Corridor implements PowerStatusController{

    private int id;
    protected Map<Integer, Light> lightMap = new HashMap<>();
    protected Map<Integer, AC> acMap = new HashMap<>();

    public int getId() {

        return id;
    }


    public void setId(int id) {

        this.id = id;
    }


    public Map<Integer, Light> getLightMap() {

        return lightMap;
    }


    public Map<Integer, AC> getAcMap() {

        return acMap;
    }


    @Override
    public int hashCode() {

        return id;
    }


    @Override
    public boolean equals(Object object) {

        if (object instanceof Corridor) {

            Corridor corridor = (Corridor) object;

            if (corridor.getId() == this.getId()) {
                return true;
            }
        }
        return false;
    }
}
