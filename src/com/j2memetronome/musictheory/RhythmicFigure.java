
package com.j2memetronome.musictheory;

/**
 *
 * @author Deivid Martins
 */
public class RhythmicFigure {

    private final int denominator;

    public static final RhythmicFigure WHOLE = new RhythmicFigure(1);
    public static final RhythmicFigure HALF = new RhythmicFigure(2);
    public static final RhythmicFigure QUARTER = new RhythmicFigure(4);
    public static final RhythmicFigure EIGHTH = new RhythmicFigure(8);
    public static final RhythmicFigure SIXTEENTH = new RhythmicFigure(16);
    public static final RhythmicFigure THIRTY_SECOND = new RhythmicFigure(32);
    public static final RhythmicFigure SIXTY_FOURTH = new RhythmicFigure(64);

    private RhythmicFigure(int denominator) {
        this.denominator = denominator;
    }

    public int intValue()
    {
        return denominator;
    }

    public RhythmicFigure next()
    {
       if(this.equals(WHOLE))
           return HALF;
       else if(this.equals(HALF))
           return QUARTER;
       else if(this.equals(QUARTER))
           return EIGHTH;
       else if(this.equals(EIGHTH))
           return SIXTEENTH;
       else if(this.equals(SIXTEENTH))
           return THIRTY_SECOND;
       else
           return SIXTY_FOURTH;

    }
    public RhythmicFigure previous()
    {
       if(this.equals(SIXTY_FOURTH))
           return THIRTY_SECOND;
       else if(this.equals(THIRTY_SECOND))
           return SIXTEENTH;
       else if(this.equals(SIXTEENTH))
           return EIGHTH;
       else if(this.equals(EIGHTH))
           return QUARTER;
       else if(this.equals(QUARTER))
           return HALF;
       else
           return WHOLE;

    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        final RhythmicFigure other = (RhythmicFigure) object;
        if (this.denominator != other.denominator) {
            return false;
        }
        return true;
    }



    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.denominator;
        return hash;
    }
}
