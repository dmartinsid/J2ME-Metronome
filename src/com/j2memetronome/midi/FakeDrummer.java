/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.j2memetronome.midi;

import java.io.IOException;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

/**
 *  This Drummer load midi sounds and plays these sound
 *  It do not play native sound.
 *  Will be used in device which no support MIDIControl
 *
 * @author dmartins
 */
public class FakeDrummer extends Drummer {

    private Player mp;

    /**
     * Constructor
     */
    public FakeDrummer() {
    }
    /**
     * Plays a drum component
     * @param num
     */
    int lastTone = -1;

    public void playDrum(int num) {
        try {

            if (lastTone != - 1) {
                if (mp.getState() == Player.STARTED) {
                    mp.stop();
                }
                if (mp.getState() == Player.PREFETCHED) {
                    mp.deallocate();
                }
                if (mp.getState() == Player.REALIZED ||
                        mp.getState() == Player.UNREALIZED) {
                    mp.close();
                }


            }
            try {
                mp = Manager.createPlayer(getClass().getResourceAsStream("" + String.valueOf(DRUM_NUMBERS[num]) + ".mid"), "audio/midi");
                mp.realize();
                mp.prefetch();

                mp.start();
                lastTone = num;
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (MediaException ex) {
                ex.printStackTrace();
            }



        } catch (MediaException ex) {
            ex.printStackTrace();
        }

    }
}
