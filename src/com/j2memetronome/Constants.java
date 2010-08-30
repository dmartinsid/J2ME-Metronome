package com.j2memetronome;

/**
 *  Unfortunately MIDP 2.0 no have Enums...
 *  My unique choice is create a class with constants..
 *
 * @author Deivid Cunha Martins
 */
public class Constants
{
    //-----------------------------------------------------------------
    // APPLICATION STATES
    //-----------------------------------------------------------------
    public static final int STATE_CHOOSE_LANG = 0;
    public static final int STATE_SPLASH = 1;
    public static final int STATE_MAIN_MENU = 2;
    public static final int STATE_START = 3;
    public static final int STATE_OPTIONS = 4;
    public static final int STATE_HELP = 5;
    public static final int STATE_ABOUT = 6;
    public static final int STATE_EXIT = 7;
    public static final int STATE_METRONOME_STARTED = 8;
    public static final int STATE_METRONOME_STOPPED = 9;
    public static final int STATE_METRONOME_OPTIONS = 10;
    public static final int STATE_KILL = 11;

    

    //--------------------------------------------------------------
    // MAIN MENU CONSTANTS
    //--------------------------------------------------------------
    public static final int MENU_ANIMATION_X_INITIAL = -128;
    public static final int MENU_ANIMATION_Y_INITIAL = 0;
    public static final int MAIN_MENU_LENGHT = 4;
    public static final int ABOUT_AND_HELP_TEXT_INITIAL_Y = 30;


    //--------------------------------------------------------------
    // METRONOME CANVAS POSITION CONSTANTS
    //--------------------------------------------------------------
    public static int BALL_BPM_INITIAL_X = 8;

    //----------------------------------------------------------------------
    // FADE
    //----------------------------------------------------------------------
    public static final int FADE_IN = 0;
    public static final int FADE_OUT = 1; 

    //----------------------------------------------------------------------
    // OPTIONS
    //----------------------------------------------------------------------
    public static final int OPTIONS_NUMBER = 2;

    public static final int OPTIONS_SOUND_KITS = 0;

    // String IDs
    public static int STRING_ABOUT = 0;
    public static int STRING_HELP = 1;
    public static int STRING_OPTIONS = 2;
    public static int STRING_EXIT = 3;
    public static int STRING_BASS_DRUM_AND_SNARE = 4;
    public static int STRING_BASS_DRUM_SNARE_HH = 5;
    public static int STRING_TOMS = 6;
    public static int STRING_CLICK_AND_BELL = 7;
    public static int STRING_KITS = 8;
    public static int STRING_EXIT_TEXT = 9;
    


    public static int DIFFERENT_SOUNDS = 4;

    


    // with enum this code will be more easy and better...
    public static final int ENGLISH = 0;
    public static final int PORTUGUESE = 1;

    public static final String ENGLISH_RES = "en";
    public static final String PORTUGUESE_RES = "pt";
    //...
    

    // Others device constants
    public static int DEVICE_MAX_NUMBER_OF_LINES = 6;
   }
