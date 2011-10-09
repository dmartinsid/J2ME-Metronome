package com.j2memetronome;

import com.j2memetronome.midi.Drummer;
import com.j2memetronome.note.RhythmicFigure;

/**
 *
 * @author dmartins
 */
public class Metronome implements MetronomeLimits {

    private int beatsPerMinute;
    private int numerator;
    private RhythmicFigure denominator;
    private int actualBeat;
    private Drummer drummer;

    public Metronome() {
        this.beatsPerMinute = 120;
        this.denominator = RhythmicFigure.QUARTER;
        this.numerator = 4;
        actualBeat = 1;
        drummer = new Drummer();
    }

    public Metronome(int beatsPerMinute, int numerator, RhythmicFigure denominator) {
        this.beatsPerMinute = beatsPerMinute;
        this.denominator = denominator;
        this.numerator = numerator;

        drummer = new Drummer();
        actualBeat = 1;
    }

    public void process(boolean isStarted) {
        if (!isStarted) {
            actualBeat = 1;
        } else if (actualBeat == numerator) {
            playOthers();
            actualBeat = 1;
        } else {
            if (actualBeat == 1) {
                playFirst();
            } else {
                playOthers();
            }

            actualBeat++;
        }
    }

    /**
     * 
     * @return time in milliseconds
     */
    public long sleepTime() {
        return (long) ((60000 / beatsPerMinute) * ((double) 4 / denominator.intValue()));
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

    public RhythmicFigure getDenominator() {
        return denominator;
    }

    public void setDenominator(RhythmicFigure denominator) {
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public void setKit(int kitID) {
        drummer.setKit(kitID);
    }
    public int getKit() {
        return drummer.getKit();
    }

    public void playFirst() {
        drummer.playFirst();
    }

    public void playOthers() {
        drummer.playOthers();
    }
}
