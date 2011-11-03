package com.j2memetronome.musictheory;

/**
 *
 * @author Deivid Martins
 */
public class Measure {
    
    private int numerator;
    private RhythmicFigure denominator;

    public Measure(int numerator, RhythmicFigure denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public RhythmicFigure getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void increaseNumerator()
    {
        numerator++;
    }
    
    public void decreaseNumerator()
    {
        if(numerator > 1)
            numerator--;
    }
    
    public void increaseDenominator()
    {
        denominator = denominator.next();
    }
    
    public void decreaseDenominator()
    {
        denominator = denominator.previous();
    }
    public String toString() {
        return numerator + "/" + denominator.intValue();
    }
    
    

    
    
    
    
    
}
