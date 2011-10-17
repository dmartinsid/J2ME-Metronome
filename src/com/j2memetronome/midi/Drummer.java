package com.j2memetronome.midi;

import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.MIDIControl;

/**
 *  
 * 
 * @author Deivid Cunha Martins
 */
public class Drummer implements DrumKit {

    private MIDIControl midiControl;
    private Player player;
    private int kit;

    /**
     * Constructor
     */
    public Drummer() {
        try {
            midiControl = this.getMIDIControl();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    

    /**
     * Plays a drum component
     * @param num
     */
    private void playDrum(int num) {
        if (midiControl == null) {
            return;
        }

        midiControl.shortMidiEvent(0x99, DRUM_NUMBERS[num], 127);


    }

    private MIDIControl getMIDIControl() throws Exception {
        if (player == null) {
            player = Manager.createPlayer(Manager.MIDI_DEVICE_LOCATOR);
            player.prefetch();

        }
        return (MIDIControl) player.getControl("javax.microedition.media.control.MIDIControl");
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
    
    public int getKit()
    {
        return kit;
    }
}
