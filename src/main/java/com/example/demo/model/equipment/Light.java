package com.example.demo.model.equipment;

import com.example.demo.PowerStatus;


public class Light extends ElectricEquipment {


    private int id;
    private PowerStatus powerStatus;

    public Light(int id) {

        super(id,null);
        this.id = id;
    }


    public int getId() {

        return id;
    }


    public PowerStatus getPowerStatus() {

        return powerStatus;
    }


    public void setPowerStatus(PowerStatus powerStatus) {

        this.powerStatus = powerStatus;
    }


    @Override
    public int hashCode() {

        return id;
    }


    @Override
    public boolean equals(Object object) {

        if (object instanceof Light) {

            Light light = (Light) object;

            if (light.getId() == this.getId()) {
                return true;
            }
        }
        return false;
    }


}
