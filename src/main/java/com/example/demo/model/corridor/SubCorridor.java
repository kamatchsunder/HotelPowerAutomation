package com.example.demo.model.corridor;

import com.example.demo.visitor.CorridorElement;
import com.example.demo.visitor.PowerConsumptionVisitor;
import com.example.demo.model.equipment.AC;
import com.example.demo.model.equipment.Light;
import com.example.demo.PowerStatus;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.constants.Constants.*;
import static com.example.demo.constants.Constants.defaultNumberOfAcsPerSubCorridor;
import static com.example.demo.constants.Constants.defaultNumberOfLightsPerSubCorridor;


public class SubCorridor extends Corridor implements CorridorElement {


    private int id;
    private Map<Integer, Light> lightMap = new HashMap<>();
    private Map<Integer, AC> acMap = new HashMap<>();

    private PowerStatus defaultLightPowerStatus = defaultLightStatusInSubCorridor;
    private PowerStatus defaultAcPowerStatus = defaultAcStatusInSubCorridor;


    public SubCorridor(int id) {

        this.id = id;
        initLights();
        initAcs();
    }


    private void initAcs() {

        for (int i = 0; i < defaultNumberOfAcsPerSubCorridor ; i++) {

            AC ac = new AC(i+1);
            ac.setPowerStatus(defaultAcPowerStatus);
            acMap.put(i+1,ac);
        }
    }


    private void initLights() {

        for (int i = 0; i < defaultNumberOfLightsPerSubCorridor ; i++) {

            Light light = new Light(i+1);
            light.setPowerStatus(defaultLightPowerStatus);

            lightMap.put(i+1,light);
        }
    }



    public int getId() {

        return id;
    }


    public Map<Integer, Light> getLightMap() {

        return lightMap;
    }


    public Map<Integer, AC> getAcMap() {

        return acMap;
    }


    @Override
    public void changeLightPowerStatusToON() {

        getLightMap().values().forEach(light -> light.setPowerStatus(PowerStatus.ON));

    }


    @Override
    public void changeLightPowerStatusToOFF() {

        getLightMap().values().forEach(light -> light.setPowerStatus(PowerStatus.OFF));
    }


    @Override
    public void changeACPowerStatusToON() {

        getAcMap().values().forEach(light -> light.setPowerStatus(PowerStatus.ON));
    }


    @Override
    public void changeACPowerStatusToOFF() {

        getAcMap().values().forEach(light -> light.setPowerStatus(PowerStatus.OFF));

    }


    @Override
    public int accept(PowerConsumptionVisitor powerConsumptionVisitor) {

        return powerConsumptionVisitor.visit(this);
    }

    @Override
    public int hashCode() {

        return id;
    }


    @Override
    public boolean equals(Object object) {

        if (object instanceof SubCorridor) {

            SubCorridor corridor = (SubCorridor) object;

            if (corridor.getId() == this.getId()) {
                return true;
            }
        }
        return false;
    }
}
