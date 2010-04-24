package com.j2memetronome;

import com.j2memetronome.view.MetronomeCanvas;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author dmartins
 */
public class dmartinsMetronomeMIDlet extends MIDlet {
    private Display display;
    private static dmartinsMetronomeMIDlet midlet;

    /**
     * Singleton pattern
     * @return
     */
    public static dmartinsMetronomeMIDlet getInstance()
    {
        return midlet;
    }

    public void startApp() {
        if(midlet == null)
        {
            midlet = this;
            display = Display.getDisplay(this);
            new SplashScreen(display,new MetronomeCanvas());
        }
    }

    public void pauseApp() {
    }

    public void kill()
    {
        destroyApp(true);
    }
    public void destroyApp(boolean unconditional) {
        midlet = null;
        this.notifyDestroyed();
    }

}
