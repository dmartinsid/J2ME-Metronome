package com.j2memetronome.midi;

import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.MIDIControl;

/**
 *  This class acts as a drummer
 * 
 * @author Deivid Cunha Martins
 */
public class Drummer
{
    private MIDIControl mc;
    private Player mp;

    
    /**
     * Constructor
     */
    public Drummer() {
        try {
            mc = this.getMIDIControl();                    
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Plays a drum component
     * @param num
     */


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

    

    // this data was in a Interface, but some devices cast these hexadecimal to integer in compilation
    // generating an Exception

    // MIDI numbers of each component
    final static int[] DRUM_NUMBERS = {
        0x23, // Acoustic Bass Drum
        0x24, // Bass Drum
        0x25, // Side Stick
        0x26, // Acoustic Snare
        0x27, // Hand Clap
        0x28, // Electric Snare
        0x29, // Low Floor Tom
        0x2A, // Closed Hi-Hat
        0x2B, // High Floor Tom
        0x2C, // Pedal Hi-Hat
        0x2D, // Low Tom
        0x2E, // Open Hi-Hat
        0x2F, // Low-Mid Tom
        0x30, // Hi-Mid Tom
        0x31, // Crash Cymbal 1
        0x32, // High Tom
        0x33, // Ride Cymbal 1
        0x34, // Chinese Cymbal
        0x35, // Ride Bell
        0x36, // Tambourine
        0x37, // Splash Cymbal
        0x38, // Cowbell
        0x39, // Crash Cymbal 2
        0x3A, // Vibraslap
        0x3B, // Ride Cymbal 2
        0x22, // Metronome Bell
        0x21  // Metronome Click
    };

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
     public static int METRONOME_BELL = 25;
     public static int METRONOME_CLICK = 26;
}
