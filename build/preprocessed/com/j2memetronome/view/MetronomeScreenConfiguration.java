package com.j2memetronome.view;

/**
 *
 * @author Deivid Martins
 */
public class MetronomeScreenConfiguration {

    private int measureX = 110;
    private int measureY = 131;
    private int bpmX = 110;
    private int bpmY = 154;
    private double ballCoefficientX = 0.43;
    private int ballY = 177;

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
