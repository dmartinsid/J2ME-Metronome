package com.j2memetronome.model;

import com.j2memetronome.midi.Drummer;
import com.j2memetronome.musictheory.Measure;
import com.j2memetronome.musictheory.RhythmicFigure;

/**
 *
 * @author dmartins
 */
public class Metronome implements MetronomeLimits {

    private int beatsPerMinute;
    private int actualBeat;
    private Drummer drummer;
    private Measure measure;

    public Metronome() {
        setup();
        this.beatsPerMinute = 120;
        this.measure = new Measure(4, RhythmicFigure.QUARTER);
        
        
    }

    public Metronome(int beatsPerMinute, int numerator, RhythmicFigure denominator) 
    {
        setup();
        this.beatsPerMinute = beatsPerMinute;
        this.measure = new Measure(numerator, denominator);

    }
    
    

    public void play() 
    {
        if (actualBeat == 1)
            playFirst();
        else
            playOthers();
        
        checkBeat();
    }

    /**
     * 
     * @return time in milliseconds
     */
    public long sleepTime() {
        return (long) ((60000 / beatsPerMinute) * ((double) 4 / measure.getDenominator().intValue()));
    }

    public int getBeatsPerMinute() {
        return beatsPerMinute;
    }

    public void increaseBeatsPerMinute() {
        if (BPM_MAX > beatsPerMinute) {
            beatsPerMinute = beatsPerMinute + 1;
        }
    }

    public void decreaseBeatsPerMinute() {
        if (BPM_MIN < beatsPerMinute) {
            beatsPerMinute = beatsPerMinute - 1;
        }
    }

    public int getActualBeat() {
        return actualBeat;
    }

    public Measure getMeasure() {
        return measure;
    }

      

    public void setKit(int kitID) {
        drummer.setKit(kitID);
    }
    public int getKit() {
        return drummer.getKit();
    }

    private void playFirst() {
        drummer.playFirst();
    }

    private void playOthers() {
        drummer.playOthers();

    }

    private void checkBeat() {
        if(actualBeat == measure.getNumerator())
            actualBeat = 1;
        else 
            actualBeat++;
    }
    
    private void setup()
    {
        this.drummer = new Drummer();
        this.actualBeat = 1;
    }
}
