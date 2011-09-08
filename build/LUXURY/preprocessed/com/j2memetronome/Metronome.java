package com.j2memetronome;

import com.j2memetronome.midi.Drummer;
import com.j2memetronome.note.RhythmicFigure;


/**
 *
 * @author dmartins
 */
public class Metronome implements MetronomeConstants
{
    private int beatsPerMinute;
    private int numerator;
    private RhythmicFigure denominator;
    private int actualBeat;
    private int kitID;

    private Drummer drummer;

    public Metronome()
    {
        this.beatsPerMinute = DEFAULT_BPM;
        this.denominator = RhythmicFigure.QUARTER;
        this.numerator = DEFAULT_NUMERATOR;
        actualBeat = 1;
        drummer = new Drummer();
    }
    /**
     * Constructor
     * @param beatsPerMinute
     * @param numerator
     * @param denominator
     */
    public Metronome(int beatsPerMinute, int numerator, RhythmicFigure denominator)
    {
        this.beatsPerMinute = beatsPerMinute;
        this.denominator = denominator;
        this.numerator = numerator;

        drummer = new Drummer();
        actualBeat = 1;
    }

    public void process(boolean isStarted)
    {
        if(!isStarted)
        {
            actualBeat = 1;
        }
        else if(actualBeat == numerator)
        {
            playOthers();
            actualBeat = 1;
        }
        else
        {
            if(actualBeat == 1)
                playFirst();
            else
                playOthers();
            
            actualBeat++;
        }
    }
    /**
     * 
     * @return time in milliseconds
     */
    public long sleepTime()
    {
        return (long) ((60000 / beatsPerMinute) *  ((double)4/denominator.intValue()));
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

    public int getActualBeat()
    {
        return actualBeat;
    }
    public RhythmicFigure getDenominator() {
        return denominator;
    }
    public void setDenominator(RhythmicFigure denominator, int action)
    {

                if(this.denominator == RhythmicFigure.WHOLE)
                {

                    setDenominator(action == Actions.INCREMENT ? RhythmicFigure.HALF : RhythmicFigure.WHOLE);
                }
           
                /*case NOTE_HALF :
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
            }*/


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

    public void playTomsHigh() {
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

    public void setKit(int kitID)
    {
        this.kitID = kitID;
    }

    public void playFirst()
    {
        if (kitID == 0)
            playSnare();
        else if (kitID == 1)
            playBassDrumAndCrash();
        else if (kitID == 2)
            playTomsLow();
        else
            playMetronomeBell();
    }
    public void playOthers()
    {
        if (kitID == 0)
            playBassDrum();
        else if (kitID == 1)
            playBassDrumAndHiHat();
        else if(kitID == 2)
            playTomsMid();
        else
            playMetronomeClick();
    }

    public void setDenominator(int Action) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
