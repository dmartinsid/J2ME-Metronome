package com.j2memetronome;

import com.j2memetronome.midi.Drummer;
import com.j2memetronome.midi.FakeDrummer;

/**
 *
 * @author dmartins
 */
public class Metronome implements MetronomeConstants
{
    private int beatsPerMinute;
    private int numerator;
    private int denominator;

    private Drummer drummer;

    public Metronome()
    {
        this.beatsPerMinute = DEFAULT_BPM;
        this.denominator = DEFAULT_DENOMINATOR;
        this.numerator = DEFAULT_NUMERATOR;


            drummer = new Drummer();
      
    }
    /**
     * Constructor
     * @param beatsPerMinute
     * @param numerator
     * @param denominator
     */
    public Metronome(int beatsPerMinute, int numerator, int denominator)
    {
        this.beatsPerMinute = beatsPerMinute;
        this.denominator = denominator;
        this.numerator = numerator;

        drummer = new Drummer();

    }

    /**
     * 
     * @return time in milliseconds
     */
    public long beatsToPeriod()
    {
        return (long) ((60000 / beatsPerMinute) *  ((double)4/denominator));
    }

    //-----------------------------------------------------------------------
    // GETTERS AND SETTERS
    //-----------------------------------------------------------------------
    public int getBeatsPerMinute() {
        return beatsPerMinute;
    }

    public void setBeatsPerMinute(int beatsPerMinute) {
        this.beatsPerMinute = beatsPerMinute;
    }

    public int getDenominator() {
        return denominator;
    }
    public void setDenominator(int denominator, int action)
    {

            switch (getDenominator())
            {
                case NOTE_WHOLE :
                    setDenominator(action == Actions.INCREMENT ? NOTE_HALF : NOTE_WHOLE);
                    break;
                case NOTE_HALF :
                    setDenominator(action == Actions.INCREMENT ? NOTE_QUARTER : NOTE_WHOLE);
                    break;
                case NOTE_QUARTER :
                    setDenominator(action == Actions.INCREMENT ? NOTE_EIGHTH : NOTE_HALF);
                    break;
                case NOTE_EIGHTH :
                    setDenominator(action == Actions.INCREMENT ? NOTE_SIXTEENTH : NOTE_QUARTER );
                    break;
                case NOTE_SIXTEENTH :
                    setDenominator(action == Actions.INCREMENT ? NOTE_THIRTY_SECOND : NOTE_EIGHTH);
                    break;
                case NOTE_THIRTY_SECOND :
                    setDenominator(action == Actions.INCREMENT ? NOTE_SIXTY_FOURTH : NOTE_SIXTEENTH  );
                    break;
                case NOTE_SIXTY_FOURTH:
                    setDenominator(action == Actions.INCREMENT ? NOTE_SIXTY_FOURTH : NOTE_THIRTY_SECOND);
                    break;
            }


    }
    public void setDenominator(int denominator) {   
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public void playToms() {
        drummer.playDrum(Drummer.HI_MID_TOM);
        drummer.playDrum(Drummer.HIGH_TOM);
    }
    public void playTomsLow() {
        drummer.playDrum(Drummer.LOW_FLOOR_TOM);
        drummer.playDrum(Drummer.HIGH_FLOOR_TOM);
    }
    public void playTomsMid() {
        drummer.playDrum(Drummer.LOW_TOM);
        drummer.playDrum(Drummer.LOW_MID_TOM);
    }
    public void playBassDrum() {
        drummer.playDrum(Drummer.ACOUSTIC_BASS_DRUM);
    }

    public void playBassDrumAndCrash() {
        drummer.playDrum(Drummer.ACOUSTIC_BASS_DRUM);
        drummer.playDrum(Drummer.CRASH_CYMBAL_1);
    }

    public void playBassDrumAndHiHat() {
        drummer.playDrum(Drummer.ACOUSTIC_BASS_DRUM);
        drummer.playDrum(Drummer.CLOSED_HI_HAT);
    }

    public void playSnare() {
        drummer.playDrum(Drummer.ACOUSTIC_SNARE);
    }

    public void playMetronomeClick()
    {
        drummer.playDrum(Drummer.METRONOME_CLICK);
    }

    public void playMetronomeBell()
    {
        drummer.playDrum(Drummer.METRONOME_BELL);
    }
}
