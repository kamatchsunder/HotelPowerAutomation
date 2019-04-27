package com.example.demo.constants;

import com.example.demo.PowerStatus;


public class Constants {

    public static int defaultNumberOfLightsPerMainCorridor = 1;
    public static int defaultNumberOfAcsPerMainCorridor =   1;

    public static int defaultNumberOfLightsPerSubCorridor = 1;
    public static int defaultNumberOfAcsPerSubCorridor =   1;

    public static int powerConsumptionOfLightInMainCorridor = 5;
    public static int powerConsumptionOfAcInMainCorridor = 10;

    public static int powerConsumptionOfLightInSubCorridor = 5;
    public static int powerConsumptionOfAcInSubCorridor = 10;

    public static PowerStatus defaultAcStatusInMainCorridor = PowerStatus.ON;
    public static PowerStatus  defaultLightStatusInMainCorridor = PowerStatus.ON;

    public static PowerStatus defaultAcStatusInSubCorridor = PowerStatus.ON;
    public static PowerStatus  defaultLightStatusInSubCorridor = PowerStatus.OFF;

}
