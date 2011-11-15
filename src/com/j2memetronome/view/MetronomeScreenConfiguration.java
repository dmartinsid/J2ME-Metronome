package com.j2memetronome.view;

/**
 *
 * @author Deivid Martins
 */
public class MetronomeScreenConfiguration {

    private int measureX;
    private int measureY;
    private int bpmX;
    private int bpmY;
    private double ballCoefficientX;
    private int ballY;

    public MetronomeScreenConfiguration(int measureX, int measureY, int bpmX, int bpmY, double ballCoefficientX, int ballY) 
    {
        this.measureX = measureX;
        this.measureY = measureY;
        this.bpmX = bpmX;
        this.bpmY = bpmY;
        this.ballCoefficientX = ballCoefficientX;
        this.ballY = ballY;
    }

    public double getBallCoefficientX() {
        return ballCoefficientX;
    }

    public int getBallY() {
        return ballY;
    }

    public int getBpmX() {
        return bpmX;
    }

    public int getBpmY() {
        return bpmY;
    }

    public int getMeasureX() {
        return measureX;
    }

    public int getMeasureY() {
        return measureY;
    }
    
    
    
    
}
