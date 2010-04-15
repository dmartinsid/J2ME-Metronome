/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dmartins.app.metronome;

import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.MIDIControl;

/**
 *
 * @author dmartins
 */
public class Drummer {

    // J2ME do not support enum
    public static int ACOUSTIC_BASS_DRUM = 0;
    public static int BASS_DRUM = 1;
    public static int SIDE_STICK = 2;
    public static int ACOUSTIC_SNARE = 3;
    public static int HAND_CLAP = 4;
    public static int ELECTRIC_SNARE = 5;
    public static int LOW_FLOOR_TOM = 6;
    public static int CLOSED_HI_HAT = 7;
    public static int HIGH_FLOOR_TOM = 8;
    public static int PEDAL_HI_HAT = 9;
    public static int LOW_TOM = 10;
    public static int OPEN_HI_HAT = 11;
    public static int LOW_MID_TOM = 12;
    public static int HI_MID_TOM = 13;
    public static int CRASH_CYMBAL_1 = 14;
    public static int HIGH_TOM = 15;
    public static int RIDE_CYMBAL_1 = 16;
    public static int CHINESE_CYMBAL = 17;
    public static int RIDE_BELL = 18;
    public static int TAMBOURINE = 19;
    public static int SPLASH_CYMBAL = 20;
    public static int COWBELL = 21;
    public static int CRASH_CYMBAL_2 = 22;
    public static int VIBRASLAP = 23;
    public static int RIDE_CYMBAL2 = 24;

    private static final int[] DRUM_NUMBERS = {
        35, // Acoustic Bass Drum
        36, // Bass Drum
        37, // Side Stick
        38, // Acoustic Snare
        39, // Hand Clap
        40, // Electric Snare
        41, // Low Floor Tom
        42, // Closed Hi-Hat
        43, // High Floor Tom
        44, // Pedal Hi-Hat
        45, // Low Tom
        46, // Open Hi-Hat
        47, // Low-Mid Tom
        48, // Hi-Mid Tom
        49, // Crash Cymbal 1
        50, // High Tom
        51, // Ride Cymbal 1
        52, // Chinese Cymbal
        53, // Ride Bell
        54, // Tambourine
        55, // Splash Cymbal
        56, // Cowbell
        57, // Crash Cymbal 2
        58, // Vibraslap
        59, // Ride Cymbal 2
    };
    private MIDIControl mc;
    private Player mp;

    Drummer()
    {
        try {
            mc = this.getMIDIControl();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void playDrum(int num) {       
        if (mc == null) {
            return;
        } 
        mc.shortMidiEvent(0x99, DRUM_NUMBERS[num], 127);
    }


    public MIDIControl getMIDIControl() throws Exception {
        if (mp == null) {
            mp = Manager.createPlayer(Manager.MIDI_DEVICE_LOCATOR);
            mp.prefetch();
        }
        return (MIDIControl) mp.getControl("javax.microedition.media.control.MIDIControl");
    }
}
