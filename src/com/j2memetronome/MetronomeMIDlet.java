package com.j2memetronome;

import com.j2memetronome.container.Container;
import com.j2memetronome.view.View;
import com.j2memetronome.view.ViewFactory;

import javax.microedition.midlet.*;

/**
 * Metronome MIDlet object
 * 
 * @author Deivid Martins
 */
public class MetronomeMIDlet extends MIDlet {


    private Container container;
    private View view;
    
    public void startApp()
    {
        if (container == null) 
        {
            view = new ViewFactory().getView();
            container = new Container(this, view);
        }
    }

    public void pauseApp()
    {
        
    }

    public void kill()
    {
        destroyApp(true);
    }
    public void destroyApp(boolean unconditional) 
    {
        notifyDestroyed();
    }

}
