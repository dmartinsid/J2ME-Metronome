package com.j2memetronome.midi;

import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.MIDIControl;

/**
 *  This class acts as a drummer
 * 
 * @author Deivid Cunha Martins
 */
public class Drummer implements DrumKit
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
}
