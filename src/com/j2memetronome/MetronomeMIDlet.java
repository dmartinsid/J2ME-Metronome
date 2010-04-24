package com.j2memetronome;

import com.j2memetronome.view.MetronomeCanvas;
import javax.microedition.midlet.*;

/**
 * Metronome MIDlet object
 * 
 * @author Deivid Cunha Martins
 */
public class MetronomeMIDlet extends MIDlet {
    

    public void startApp() {
        new MetronomeCanvas(this);
    }

    public void pauseApp() {
    }

    public void kill()
    {
        destroyApp(true);
    }
    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }

}
