package com.j2memetronome;

import com.j2memetronome.container.ContainerImpl;
import com.j2memetronome.view.View;
import com.j2memetronome.view.ViewFactory;
import com.j2memetronome.view.ViewSELuxury;

import javax.microedition.midlet.*;

/**
 * Metronome MIDlet object
 * 
 * @author Deivid Cunha Martins
 */
public class MetronomeMIDlet extends MIDlet {


    private ContainerImpl containerImpl;
    private View view;
    public void startApp()
    {
        if (containerImpl == null) 
        {
            view = new ViewFactory().getView();
            containerImpl = new ContainerImpl(this, view);
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
