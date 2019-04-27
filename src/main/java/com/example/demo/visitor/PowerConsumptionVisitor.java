package com.example.demo.visitor;

import com.example.demo.model.corridor.MainCorridor;
import com.example.demo.model.corridor.SubCorridor;


public interface PowerConsumptionVisitor {


    int visit(MainCorridor mainCorridor);

    int visit(SubCorridor subCorridor);
}
