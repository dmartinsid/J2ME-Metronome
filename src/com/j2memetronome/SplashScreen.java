/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.j2memetronome;

import java.io.IOException;
import java.util.*;
import javax.microedition.lcdui.*;

/**
 *
 * @author dmartins
 */
public class SplashScreen extends Canvas
{
    private Display     display;
    private Displayable next;
    private Timer       timer = new Timer();
    private Image       splashImage;

    public SplashScreen(Display display, Displayable next)
    {
        this.display = display;
        this.next = next;
        display.setCurrent( this );
        this.setFullScreenMode(true);
    }

    protected void keyPressed( int keyCode ){
        dismiss();
    }

    protected void paint( Graphics g ){
        try {
            loadResources();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if(splashImage != null)
            g.drawImage(splashImage, 0, 0, Graphics.TOP | Graphics.LEFT);
    }

    protected void pointerPressed( int x, int y ){
        dismiss();
    }

    protected void showNotify()
    // called automatically when the Canvas is put on screen
    {  timer.schedule( new CountDown(), 5000 );
    // CountDown started after 5 secs
    }

    private void dismiss(){
        timer.cancel();
        display.setCurrent( next );
    }

    public void loadResources() throws IOException
    {
        splashImage = Image.createImage("/Splash.png");
    }

    // ----------------------------------------
    private class CountDown extends TimerTask
    {
        public void run(){
            dismiss();
        }
    }
}

