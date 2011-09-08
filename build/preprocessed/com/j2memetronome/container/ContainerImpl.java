/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.j2memetronome.container;

import com.j2memetronome.Actions;
import com.j2memetronome.Metronome;
import com.j2memetronome.MetronomeMIDlet;
import com.j2memetronome.appstate.ApplicationState;
import com.j2memetronome.device.GenericDevice;
import com.j2memetronome.resource.ResourceLoader;
import com.j2memetronome.view.View;
import com.j2memetronome.view.ViewNKLuxury;
import com.j2memetronome.view.ViewNKTouch;
import com.j2memetronome.view.ViewSELuxury;
import com.j2memetronome.view.ViewSEMidsized;
import com.j2memetronome.view.ViewSEQVGA;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author dmartins
 */
public class ContainerImpl extends Canvas implements Runnable{

    private int applicationState;
    private Thread applicationThread;

    private int languageId;

    //-----------------------------------------
    // MENU
    //-----------------------------------------
    private int menuIndex;

    private ResourceLoader resourceLoader;
   
    private Metronome metronome;

    private int firstLineScroll;
    // Options
    private static int optionsSelectedSoundComponents = 0;


    private MetronomeMIDlet midlet;

    private View view;

    private Timer timer;

    public ContainerImpl(MetronomeMIDlet midlet)
    {
        this.midlet = midlet;
        resourceLoader = new ResourceLoader();
        //#ifdef QVGA
//#         view = new ViewSEQVGA();
        //#elif LUXURY
//#         view = new ViewSELuxury();
        //#elif NKLUXURY
//#         view = new ViewNKLuxury();
        //#elif NKTOUCH
//#         view = new ViewNKTouch();
        //#else
        view = new ViewSEMidsized();
        //#endif
        setFullScreenMode(true);

        try {
            resourceLoader.loadFonts();
            setFonts();
            resourceLoader.loadImages();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

         // default is bpm equals 120 and measure 4/4
        metronome = new Metronome();
        applicationState = ApplicationState.CHOOSE_LANG;
        timer = new Timer();
        
        Display.getDisplay(midlet).setCurrent(this);

        menuIndex = 0;
        // Create Thread and Start
        applicationThread = new Thread(this);
        applicationThread.start();
        repaint();
    }

    public void setFonts() throws IOException
    {
        view.setArial(resourceLoader.getArial());
        view.setContour(resourceLoader.getContour());
        view.setMetronome(resourceLoader.getMetronomeFont());
        view.setMetronomeGreen(resourceLoader.getMetronomeGreen());
        view.setMetronomeRed(resourceLoader.getMetronomeRed());
    }

   
 

    protected void paint(Graphics g)
    {
        g.setColor(0x00000000);
        g.fillRect(0, 0, view.getWidth(), view.getHeight());

        switch (applicationState)
        {
            case ApplicationState.CHOOSE_LANG:
                view.drawChooseLanguage(g, resourceLoader.getBgMainMenu(), languageId);
                break;
            case ApplicationState.SPLASH:
                view.drawSplash(g, resourceLoader.getSplash());
                break;
            case ApplicationState.MAIN_MENU:
                view.drawMenu(g, resourceLoader.getBgMainMenu(), resourceLoader.getBgTitle(), resourceLoader.getMenu(), menuIndex, UP, UP);
                break;
            case ApplicationState.OPTIONS:
            case ApplicationState.METRONOME_OPTIONS:
                view.drawOptions(g, resourceLoader.getBgMainMenu(), resourceLoader.getOptionsBar(),
                        resourceLoader.getArrowLeft(), resourceLoader.getArrowRight(), resourceLoader.getTextCommons()[ResourceLoader.STRING_OPTIONS],
                        resourceLoader.getTextCommons(), optionsSelectedSoundComponents);
                break;
            case ApplicationState.HELP:
                view.drawHelp(g, resourceLoader.getBgMainMenu(), resourceLoader.getOptionsGrid(), resourceLoader.getArrowUp(),
                        resourceLoader.getArrowDown(), resourceLoader.getTextCommons()[ResourceLoader.STRING_HELP], resourceLoader.getTextHelp(), firstLineScroll );
                break;
            case ApplicationState.ABOUT:
                view.drawAbout(g, resourceLoader.getBgMainMenu(), resourceLoader.getOptionsGrid(), resourceLoader.getArrowUp(),
                        resourceLoader.getArrowDown(), resourceLoader.getTextCommons()[ResourceLoader.STRING_ABOUT], resourceLoader.getTextAbout(), firstLineScroll);
                break;
            case ApplicationState.EXIT:
                view.drawExit(g, resourceLoader.getBgMainMenu(), 
                        resourceLoader.getTextCommons()[ResourceLoader.STRING_EXIT], resourceLoader.getTextCommons()[ResourceLoader.STRING_EXIT_TEXT]);
                break;
            case ApplicationState.METRONOME_STARTED:
            case ApplicationState.METRONOME_STOPPED:
                
                view.drawMetronome(g, resourceLoader.getBgMetronome(), resourceLoader.getBall(), metronome.getNumerator(), metronome.getDenominator(),
                        metronome.getBeatsPerMinute(), metronome.getActualBeat(), metronome.getActualBeat() == 1,
                        applicationState == ApplicationState.METRONOME_STARTED);
                metronome.process(applicationState == ApplicationState.METRONOME_STARTED);
                break;
        }
        
        view.drawSoftKeys(g, applicationState, resourceLoader.getOK(), resourceLoader.getCancel());

    }

    public void run() {
        while (applicationState != ApplicationState.KILL)
        {
            repaint();
            serviceRepaints();

            if (applicationState == ApplicationState.SPLASH)
                timer.schedule(new CountDown(), 5000);


            if (applicationState < ApplicationState.METRONOME_STARTED) {               

                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } 
            else if (applicationState >= ApplicationState.METRONOME_STARTED)
            {
                try {
                    if (applicationState != ApplicationState.METRONOME_STARTED) {
                        Thread.sleep(50);
                       
                    } else {

                        Thread.sleep(metronome.beatsToPeriod());
                    }


                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    //----------------------------------------------------------------
    // EVENTS
    //----------------------------------------------------------------
    protected void keyPressed(int keyCode) {
        processEvents(keyCode);

    }

    protected void keyRepeated(int keyCode) {
        processEvents(keyCode);
    }

    public void processChooseLanguage(int keyCode) {
        switch (keyCode) {
            case KEY_NUM5:
            case GenericDevice.LSK:
            case GenericDevice.FIRE: {

                try {
                    resourceLoader.loadImagesLang(languageId);
                    resourceLoader.loadText(languageId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                applicationState = ApplicationState.SPLASH;
                break;

            }
            case GenericDevice.UP:
                if (languageId == 0) {
                    languageId = 1;
                } else if (languageId - 1 >= 0) {
                    languageId--;
                }
                break;
            case GenericDevice.DOWN:
                if (languageId + 1 < 2) {
                    languageId++;
                } else {
                    languageId = 0;
                }

                break;
        }
    }

    public void processMainMenu(int keyCode) {
        
        switch (keyCode) {
            case KEY_NUM5:
            case GenericDevice.LSK:
            case GenericDevice.FIRE: {
                switch (menuIndex) {
                    case 0:
                        applicationState = ApplicationState.START;
                        applicationState = ApplicationState.METRONOME_STOPPED;

                        break;

                    case 1:
                        applicationState = ApplicationState.OPTIONS;
                        break;
                    case 2:
                        applicationState = ApplicationState.HELP;
                        break;
                    case 3:
                        applicationState = ApplicationState.ABOUT;
                        break;

                }
                break;
            }
            case GenericDevice.RSK:
            case GenericDevice.CLEAR:
                applicationState = ApplicationState.EXIT;
                break;
            case GenericDevice.UP:
                if (menuIndex == 0) {
                    menuIndex = 3;
                } else if (menuIndex - 1 >= 0) {
                    menuIndex--;
                }
                break;
            case GenericDevice.DOWN:
                if (menuIndex + 1 < view.MAIN_MENU_LENGHT) {
                    menuIndex++;
                } else {
                    menuIndex = 0;
                }

                break;
        }

    }

    //public void processHelp(int )
    public void processEvents(int keyCode) {

        switch (applicationState) {

            case ApplicationState.CHOOSE_LANG:
                processChooseLanguage(keyCode);
                break;
            case ApplicationState.MAIN_MENU:
                processMainMenu(keyCode);
                break;
            case ApplicationState.HELP:
            case ApplicationState.ABOUT:
                switch (keyCode) {
                    case GenericDevice.RSK:
                    case GenericDevice.CLEAR:
                        applicationState = ApplicationState.MAIN_MENU;
                        //resetAnimation();
                        firstLineScroll = 0;
                        break;
                    case GenericDevice.UP:
                        if (firstLineScroll > 0) {
                            firstLineScroll--;
                        }
                        break;
                    case GenericDevice.DOWN:

                        if (firstLineScroll < resourceLoader.getTextHelp().length - view.maxLines() && applicationState == ApplicationState.HELP) {
                            firstLineScroll++;
                        } else if (firstLineScroll < resourceLoader.getTextAbout().length - view.maxLines() && applicationState == ApplicationState.ABOUT) {
                            firstLineScroll++;
                        }

                        break;


                }
                break;
            case ApplicationState.OPTIONS:
            case ApplicationState.METRONOME_OPTIONS:
                switch (keyCode) {
                    case GenericDevice.LSK:
                    case GenericDevice.CLEAR:

                        if (applicationState == ApplicationState.OPTIONS) {
                            applicationState = ApplicationState.MAIN_MENU;
                        //resetAnimation();
                        } else {
                            applicationState = ApplicationState.METRONOME_STOPPED;
                        }
                        break;
                    case UP:

                    case GenericDevice.RIGHT:
                    case Canvas.KEY_NUM6:

               
                        if (optionsSelectedSoundComponents + 1 < view.supportedSounds()) {
                            optionsSelectedSoundComponents++;

                        } else if (optionsSelectedSoundComponents + 1 == view.supportedSounds()) {
                            optionsSelectedSoundComponents = 0;
                        }
                        metronome.setKit(optionsSelectedSoundComponents);
                        break;


                    case GenericDevice.LEFT:
                    case Canvas.KEY_NUM4:

                        if (optionsSelectedSoundComponents > 0) {
                            optionsSelectedSoundComponents--;
                        } else if (optionsSelectedSoundComponents - 1 < 0) {
                            optionsSelectedSoundComponents = view.supportedSounds() - 1;
                        }
                        break;



                }
                break;
            case ApplicationState.EXIT:
                switch (keyCode) {
                    case GenericDevice.RSK:
                    case GenericDevice.CLEAR:
                        applicationState = ApplicationState.MAIN_MENU;
                        //resetAnimation();
                        break;
                    case Canvas.KEY_NUM5:
                    case GenericDevice.FIRE:
                    case GenericDevice.LSK:
                        midlet.kill();
                        break;

                }
                break;

            case ApplicationState.METRONOME_STARTED:
            case ApplicationState.METRONOME_STOPPED:

                switch (keyCode) {

                    case GenericDevice.RIGHT:
                        applicationState = ApplicationState.METRONOME_STOPPED;
                        if (metronome.getBeatsPerMinute() < Metronome.BPM_MAX) {
                            metronome.setBeatsPerMinute(metronome.getBeatsPerMinute() + 1);
                        }
                        break;
                    case GenericDevice.LEFT:
                        applicationState = ApplicationState.METRONOME_STOPPED;
                        if (metronome.getBeatsPerMinute() > Metronome.BPM_MIN) {
                            metronome.setBeatsPerMinute(metronome.getBeatsPerMinute() - 1);
                        }
                        break;
                    case GenericDevice.UP:
                        metronome.setNumerator(metronome.getNumerator() + 1);
                        break;
                    case GenericDevice.DOWN:
                        metronome.setNumerator(metronome.getNumerator() == 2 ? 2 : metronome.getNumerator() - 1);
                        break;
                    case GenericDevice.FIRE:
                    case Canvas.KEY_NUM5:
                        if (applicationState == ApplicationState.METRONOME_STARTED) {
                            applicationState = ApplicationState.METRONOME_STOPPED;
                        } else if (applicationState == ApplicationState.METRONOME_STOPPED) {
                            applicationState = ApplicationState.METRONOME_STARTED;
                        }
                        break;
                    case Canvas.KEY_NUM2:
                        metronome.setDenominator(metronome.getDenominator(), Actions.INCREMENT);
                        break;
                    case Canvas.KEY_NUM8:
                        metronome.setDenominator(metronome.getDenominator(), Actions.DECREMENT);
                        break;
                    case GenericDevice.LSK:
                        applicationState = ApplicationState.METRONOME_OPTIONS;
                        break;
                    case GenericDevice.RSK:
                        applicationState = ApplicationState.MAIN_MENU;
                        Display.getDisplay(midlet).setCurrent(this);
                        applicationState = ApplicationState.MAIN_MENU;
                        break;
                    case Canvas.KEY_POUND:
                        if (optionsSelectedSoundComponents < view.supportedSounds() - 1) {
                            optionsSelectedSoundComponents++;
                        } else {
                            optionsSelectedSoundComponents = 0;
                        }

                        metronome.setKit(optionsSelectedSoundComponents);
                        break;

                }
                break;

        }
    }


    private void dismiss() {
        timer.cancel();
        applicationState = ApplicationState.MAIN_MENU;
    }

    private class CountDown extends TimerTask {

        public void run() {
            dismiss();
        }
    }
}
