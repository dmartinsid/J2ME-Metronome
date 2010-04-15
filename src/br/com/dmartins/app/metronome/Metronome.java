package br.com.dmartins.app.metronome;

/**
 *
 * @author dmartins
 */
public class Metronome implements MetronomeConstants
{
    private int beatsPerMinute;
    private int numerator;
    private int denominator;

    Metronome()
    {
        this.beatsPerMinute = DEFAULT_BPM;
        this.denominator = DEFAULT_DENOMINATOR;
        this.numerator = DEFAULT_NUMERATOR;
    }
    /**
     * Constructor
     * @param beatsPerMinute
     * @param numerator
     * @param denominator
     */
    Metronome(int beatsPerMinute, int numerator, int denominator)
    {
        this.beatsPerMinute = beatsPerMinute;
        this.denominator = denominator;
        this.numerator = numerator;
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
}
