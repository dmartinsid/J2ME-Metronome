package com.j2memetronome;

import com.j2memetronome.container.ContainerImpl;
import com.j2memetronome.view.MetronomeCanvas;
import javax.microedition.midlet.*;

/**
 * Metronome MIDlet object
 * 
 * @author Deivid Cunha Martins
 */
public class MetronomeMIDlet extends MIDlet {

    private MetronomeCanvas canvas;
    private ContainerImpl containerImpl;
    public void startApp()
    {
        if (canvas == null) {
           // canvas = new MetronomeCanvas(this);
            containerImpl = new ContainerImpl(this);
        }
    }

    public void pauseApp()
    {
        
    }

    public void kill()
    {
        destroyApp(true);
    }
    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }

}
