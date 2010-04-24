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

    //-------------------------------------------------------------
    // MAIN MENU TEXT
    //-------------------------------------------------------------
    public static final String MAIN_MENU_ABOUT_TITLE = "ABOUT";
    public static final String MAIN_MENU_HELP_TITLE = "HELP";
    public static final String MAIN_MENU_OPTIONS_TITLE = "OPTIONS";
    public static final String MAIN_MENU_EXIT_TITLE = "EXIT";


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


    public static String SOUND_OPTIONS_COMPONENTS[] = {"Bass Drum and Snare", "Bass Drum, Snare and HH", "Toms"};
    public static String SOUND_OPTIONS_COMPONENTS_TEXT = "KITS:";

    // Text
    // need refactoring for txt and rms
    public static String EXIT_TEXT[] = {"Are you sure?"};


    

   }
