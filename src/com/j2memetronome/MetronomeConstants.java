/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.j2memetronome;

/**
 *
 * @author dmartins
 */
public interface MetronomeConstants {
    //----------------------------------------------------------------------
    // NOTES
    //----------------------------------------------------------------------
    int WHOLE_NOTE = 1;
    int HALF_NOTE = 2;
    int QUARTER_NOTE = 4;
    int EIGHTH_NOTE = 8;
    int SIXTEENTH_NOTE = 16;
    int THIRTY_SECOND_NOTE = 32;
    int SIXTY_FOURTH_NOTE = 64;
    //-----------------------------------------------------------------------
    // MEASURES
    //-----------------------------------------------------------------------
    int VALID_DENOMINATORS[] = {2,4,8,16,32,64};

    // Notes
    int NOTE_WHOLE = 1;
    int NOTE_HALF = 2;
    int NOTE_QUARTER = 4;
    int NOTE_EIGHTH = 8;
    int NOTE_SIXTEENTH = 16;
    int NOTE_THIRTY_SECOND = 32;
    int NOTE_SIXTY_FOURTH = 64;

    //---------------------------------------------------------------
    // METRONOME LIMITS
    //---------------------------------------------------------------
    int BPM_MAX = 350;
    int BPM_MIN = 1;

    // default configuration
    int DEFAULT_BPM = 120;
    int DEFAULT_DENOMINATOR = 4;
    int DEFAULT_NUMERATOR = 4;
}
