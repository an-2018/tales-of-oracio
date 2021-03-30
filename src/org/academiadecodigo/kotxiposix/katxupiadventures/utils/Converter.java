package org.academiadecodigo.kotxiposix.katxupiadventures.utils;

import org.academiadecodigo.kotxiposix.katxupiadventures.scenario.Scenario;

public class Converter {

    public static int toPixelPos(int value){
        return value * Scenario.getCELLSIZE() + Scenario.getPADDING();
    }

    public static int toPixel(int value){
        return value * Scenario.getCELLSIZE();
    }

    public static int fromPixelPos(int value){
        return (value) /Scenario.getCELLSIZE();
    }
}
