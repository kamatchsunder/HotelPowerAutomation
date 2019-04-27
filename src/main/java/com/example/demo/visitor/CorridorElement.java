package com.example.demo.visitor;

import com.example.demo.visitor.PowerConsumptionVisitor;


public interface CorridorElement {

    int accept(PowerConsumptionVisitor powerConsumptionVisitor);
}
