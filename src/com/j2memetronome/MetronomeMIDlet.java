package com.j2memetronome;

import com.j2memetronome.container.ContainerImpl;

import javax.microedition.midlet.*;

/**
 * Metronome MIDlet object
 * 
 * @author Deivid Cunha Martins
 */
public class MetronomeMIDlet extends MIDlet {


    private ContainerImpl containerImpl;
    public void startApp()
    {
        if (containerImpl == null) 
            containerImpl = new ContainerImpl(this);
        
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
