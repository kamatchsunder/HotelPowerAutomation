package com.example.demo.visitor;

import com.example.demo.constants.Constants;
import com.example.demo.PowerStatus;
import com.example.demo.model.corridor.Corridor;
import com.example.demo.model.corridor.MainCorridor;
import com.example.demo.model.corridor.SubCorridor;


public class CorridorPowerConsumptionVisitor implements PowerConsumptionVisitor {

    @Override
    public int visit(MainCorridor mainCorridor) {

        int mainCorridorAcCount = getPowerConsumptionCountForAc(mainCorridor,Constants.powerConsumptionOfAcInMainCorridor);
        int mainCorridorLightCount = getPowerConsumptionCountForLight(mainCorridor,Constants.powerConsumptionOfLightInMainCorridor);

        return mainCorridorAcCount + mainCorridorLightCount;

    }


    @Override
    public int visit(SubCorridor subCorridor) {

        int subCorridorAcCount = getPowerConsumptionCountForAc(subCorridor,Constants.powerConsumptionOfAcInSubCorridor);
        int subCorridorLightCount = getPowerConsumptionCountForLight(subCorridor,Constants.powerConsumptionOfLightInSubCorridor);

        return subCorridorAcCount + subCorridorLightCount;
    }

    private int getPowerConsumptionCountForAc(Corridor corridor,int powerConsumptionUnit){

        return corridor.getAcMap().values().stream()
                .filter(ac -> ac.getPowerStatus() == PowerStatus.ON)
                .mapToInt(ac -> powerConsumptionUnit)
                .sum();

    }

    private int getPowerConsumptionCountForLight(Corridor corridor,int powerConsumptionUnit){

        return corridor.getLightMap().values().stream()
                .filter(ac -> ac.getPowerStatus() == PowerStatus.ON)
                .mapToInt(ac -> powerConsumptionUnit)
                .sum();

    }
}
