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
    public static final int SPLASH = 0;
    public static final int MAIN_MENU = SPLASH + 1;
    public static final int START = MAIN_MENU + 1;
    public static final int OPTIONS = START + 1;
    public static final int HELP = OPTIONS + 1;
    public static final int ABOUT = HELP + 1;
    public static final int EXIT = ABOUT + 1;
    public static final int METRONOME_STARTED = EXIT + 1;
    public static final int METRONOME_STOPPED = METRONOME_STARTED + 1;
    public static final int KILL = METRONOME_STOPPED + 1;

    private int state;

    public ApplicationState(int state) {
        this.state = state;
    }
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void next()
    {
        state++;
    }
}
