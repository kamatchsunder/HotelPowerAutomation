package com.example.demo.model.equipment;

import com.example.demo.PowerStatus;
import com.example.demo.model.corridor.SubCorridor;


public class ElectricEquipment {

    private int id;
    private PowerStatus powerStatus;


    public ElectricEquipment(int id, PowerStatus powerStatus) {

        this.id = id;
        this.powerStatus = powerStatus;
    }


    public int getId() {

        return id;
    }


    @Override
    public int hashCode() {

        return id;
    }


    @Override
    public boolean equals(Object object) {

        if (object instanceof ElectricEquipment) {

            ElectricEquipment electricEquipment = (ElectricEquipment) object;

            if (electricEquipment.getId() == this.getId()) {
                return true;
            }
        }
        return false;
    }

}
