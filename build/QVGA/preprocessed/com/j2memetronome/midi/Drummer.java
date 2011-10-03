package com.j2memetronome.midi;

import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.MIDIControl;

/**
 *  This class acts as a this
 * 
 * @author Deivid Cunha Martins
 */
public class Drummer implements DrumKit {

    private MIDIControl mc;
    private Player mp;
    private int kit;

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
    private void playDrum(int num) {
        if (mc == null) {
            return;
        }

        mc.shortMidiEvent(0x99, DRUM_NUMBERS[num], 127);


    }

    private MIDIControl getMIDIControl() throws Exception {
        if (mp == null) {
            mp = Manager.createPlayer(Manager.MIDI_DEVICE_LOCATOR);
            mp.prefetch();

        }
        return (MIDIControl) mp.getControl("javax.microedition.media.control.MIDIControl");
    }

    private void playTomsHigh() {
        this.playDrum(Drummer.HI_MID_TOM);
        this.playDrum(Drummer.HIGH_TOM);
    }

    private void playTomsLow() {
        this.playDrum(Drummer.LOW_FLOOR_TOM);
        this.playDrum(Drummer.HIGH_FLOOR_TOM);
    }

    private void playTomsMid() {
        this.playDrum(Drummer.LOW_TOM);
        this.playDrum(Drummer.LOW_MID_TOM);
    }

    private void playBassDrum() {
        this.playDrum(Drummer.ACOUSTIC_BASS_DRUM);
    }

    private void playBassDrumAndCrash() {
        this.playDrum(Drummer.ACOUSTIC_BASS_DRUM);
        this.playDrum(Drummer.CRASH_CYMBAL_1);
    }

    private void playBassDrumAndHiHat() {
        this.playDrum(Drummer.ACOUSTIC_BASS_DRUM);
        this.playDrum(Drummer.CLOSED_HI_HAT);
    }

    private void playSnare() {
        this.playDrum(Drummer.ACOUSTIC_SNARE);
    }

    private void playMetronomeClick() {
        this.playDrum(Drummer.METRONOME_CLICK);
    }

    private void playMetronomeBell() {
        this.playDrum(Drummer.METRONOME_BELL);
    }

    public void playFirst() {
        if (kit == 0) {
            playSnare();
        } else if (kit == 1) {
            playBassDrumAndCrash();
        } else if (kit == 2) {
            playTomsLow();
        } else {
            playMetronomeBell();
        }
    }

    public void playOthers() {
        if (kit == 0) {
            playBassDrum();
        } else if (kit == 1) {
            playBassDrumAndHiHat();
        } else if (kit == 2) {
            playTomsMid();
        } else {
            playMetronomeClick();
        }
    }

    public void setKit(int kit)
    {
        this.kit = kit;
    }
}
