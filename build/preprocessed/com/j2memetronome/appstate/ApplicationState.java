/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.j2memetronome.appstate;

/**
 *
 * @author Deivid Martins
 */
public final class ApplicationState {
    // No enums in Java ME =(
    // Ugly, sad, but is this
    //-----------------------------------------------------------------
    // APPLICATION STATES
    //-----------------------------------------------------------------
    public static final int CHOOSE_LANG = 0;
    public static final int SPLASH = 1;
    public static final int MAIN_MENU = 2;
    public static final int START = 3;
    public static final int OPTIONS = 4;
    public static final int HELP = 5;
    public static final int ABOUT = 6;
    public static final int EXIT = 7;
    public static final int METRONOME_STARTED = 8;
    public static final int METRONOME_STOPPED = 9;
    public static final int METRONOME_OPTIONS = 10;
    public static final int KILL = 11;

}
