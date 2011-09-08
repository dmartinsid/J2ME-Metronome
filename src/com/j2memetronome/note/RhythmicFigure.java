/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2memetronome.note;

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
}
