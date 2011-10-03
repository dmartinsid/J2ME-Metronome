/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2memetronome.container;

import com.j2memetronome.Metronome;
import com.j2memetronome.MetronomeMIDlet;
import com.j2memetronome.appstate.ApplicationState;
import com.j2memetronome.device.GenericDevice;
import com.j2memetronome.resource.ResourceLoader;
import com.j2memetronome.view.Menu;
import com.j2memetronome.view.View;
import com.j2memetronome.view.ViewSELuxury;
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
public class ContainerImpl extends Canvas implements Runnable {

    private ApplicationState applicationState;
    private Thread applicationThread;
    private int languageId;
    //-----------------------------------------
    // MENU
    //-----------------------------------------
    private ResourceLoader resourceLoader;
    private Metronome metronome;
    private int firstLineScroll;
    // Options
    private static int optionsSelectedSoundComponents = 0;
    private MetronomeMIDlet midlet;
    private View view;
    private Timer timer;
    private Menu menu;

    public ContainerImpl(MetronomeMIDlet midlet, View view) {
        this.midlet = midlet;
        this.view = view;
        resourceLoader = new ResourceLoader();

        setFullScreenMode(true);

        try {
            resourceLoader.loadFonts();
            setFonts();
            resourceLoader.loadImages();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        metronome = new Metronome();
        applicationState = new ApplicationState(ApplicationState.CHOOSE_LANG);
        timer = new Timer();

        Display.getDisplay(midlet).setCurrent(this);

        menu = new Menu();

        applicationThread = new Thread(this);
        applicationThread.start();
        repaint();
    }

    public void setFonts() throws IOException {
        view.setArial(resourceLoader.getArial());
        view.setContour(resourceLoader.getContour());
        view.setMetronome(resourceLoader.getMetronomeFont());
        view.setMetronomeGreen(resourceLoader.getMetronomeGreen());
        view.setMetronomeRed(resourceLoader.getMetronomeRed());
    }

    protected void paint(Graphics g) {
        g.setColor(0x00000000);
        g.fillRect(0, 0, view.getWidth(), view.getHeight());

        switch (applicationState.getState()) {
            case ApplicationState.CHOOSE_LANG:
                view.drawChooseLanguage(g, resourceLoader.getBgMainMenu(), languageId);
                break;
            case ApplicationState.SPLASH:
                view.drawSplash(g, resourceLoader.getSplash());
                break;
            case ApplicationState.MAIN_MENU:
                view.drawMenu(g, resourceLoader.getBgMainMenu(), resourceLoader.getBgTitle(), resourceLoader.getMenu(), menu.getIndex(), UP, UP);
                break;
            case ApplicationState.OPTIONS:
            case ApplicationState.METRONOME_OPTIONS:
                view.drawOptions(g, resourceLoader.getBgMainMenu(), resourceLoader.getOptionsBar(),
                        resourceLoader.getArrowLeft(), resourceLoader.getArrowRight(), resourceLoader.getTextCommons()[ResourceLoader.STRING_OPTIONS],
                        resourceLoader.getTextCommons(), optionsSelectedSoundComponents);
                break;
            case ApplicationState.HELP:
                view.drawHelp(g, resourceLoader.getBgMainMenu(), resourceLoader.getOptionsGrid(), resourceLoader.getArrowUp(),
                        resourceLoader.getArrowDown(), resourceLoader.getTextCommons()[ResourceLoader.STRING_HELP], resourceLoader.getTextHelp(), firstLineScroll);
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

                view.drawMetronome(g, resourceLoader.getBgMetronome(), resourceLoader.getBall(), metronome.getNumerator(), metronome.getDenominator().intValue(),
                        metronome.getBeatsPerMinute(), metronome.getActualBeat(), metronome.getActualBeat() == 1,
                        applicationState.getState() == ApplicationState.METRONOME_STARTED);
                metronome.process(applicationState.getState() == ApplicationState.METRONOME_STARTED);
                break;
        }

        view.drawSoftKeys(g, applicationState.getState(), resourceLoader.getOK(), resourceLoader.getCancel());

    }
    CountDown countDown ;
    public void run() {
        while (applicationState.getState() != ApplicationState.KILL) {
            repaint();
            serviceRepaints();

            if (applicationState.getState() == ApplicationState.SPLASH) {
               
                if(countDown == null)
                {
                    countDown = new CountDown();
                    timer.schedule(countDown, 5000);
                }
            }


            if (applicationState.getState() < ApplicationState.METRONOME_STARTED) {

                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else if (applicationState.getState() >= ApplicationState.METRONOME_STARTED) {
                try {
                    if (applicationState.getState() != ApplicationState.METRONOME_STARTED) {
                        Thread.sleep(50);

                    } else {

                        Thread.sleep(metronome.sleepTime());
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
                    resourceLoader.loadImagesLang();
                    resourceLoader.loadText();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                applicationState.setState(ApplicationState.SPLASH);
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
                switch ( menu.getIndex()) {
                    case 0:
                        applicationState.setState(ApplicationState.METRONOME_STOPPED);
                        break;

                    case 1:
                        applicationState.setState(ApplicationState.OPTIONS);
                        break;
                    case 2:
                        applicationState.setState(ApplicationState.HELP);
                        break;
                    case 3:
                        applicationState.setState(ApplicationState.ABOUT);
                        break;

                }

                break;
            }
            case GenericDevice.RSK:
            case GenericDevice.CLEAR:
                applicationState.setState(ApplicationState.EXIT);
                break;
            case GenericDevice.UP:
                menu.previousIndex();
                break;
            case GenericDevice.DOWN:
                menu.nextIndex();

                break;
        }

    }


    public void processEvents(int keyCode) {

        switch (applicationState.getState()) {

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
                        applicationState.setState(ApplicationState.MAIN_MENU);

                        firstLineScroll = 0;
                        break;
                    case GenericDevice.UP:
                        if (firstLineScroll > 0) {
                            firstLineScroll--;
                        }
                        break;
                    case GenericDevice.DOWN:

                        if (firstLineScroll < resourceLoader.getTextHelp().length - view.maxLines() && applicationState.getState() == ApplicationState.HELP) {
                            firstLineScroll++;
                        } else if (firstLineScroll < resourceLoader.getTextAbout().length - view.maxLines() && applicationState.getState() == ApplicationState.ABOUT) {
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

                        if (applicationState.getState() == ApplicationState.OPTIONS) {
                            applicationState.setState(ApplicationState.MAIN_MENU);

                        } else {
                            applicationState.setState(ApplicationState.METRONOME_STOPPED);
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
                        applicationState.setState(ApplicationState.MAIN_MENU);

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
                        applicationState.setState(ApplicationState.METRONOME_STOPPED);

                        metronome.increaseBeatsPerMinute();

                        break;
                    case GenericDevice.LEFT:
                        applicationState.setState(ApplicationState.METRONOME_STOPPED);
                        metronome.decreaseBeatsPerMinute();
                        break;
                    case GenericDevice.UP:
                        metronome.setNumerator(metronome.getNumerator() + 1);
                        break;
                    case GenericDevice.DOWN:
                        metronome.setNumerator(metronome.getNumerator() == 2 ? 2 : metronome.getNumerator() - 1);
                        break;
                    case GenericDevice.FIRE:
                    case Canvas.KEY_NUM5:
                        if (applicationState.getState() == ApplicationState.METRONOME_STARTED) {
                            applicationState.setState(ApplicationState.METRONOME_STOPPED);
                        } else if (applicationState.getState() == ApplicationState.METRONOME_STOPPED) {
                            applicationState.setState( ApplicationState.METRONOME_STARTED);
                        }
                        break;
                    case Canvas.KEY_NUM2:
                        metronome.setDenominator(metronome.getDenominator().next());
                        break;
                    case Canvas.KEY_NUM8:
                        metronome.setDenominator(metronome.getDenominator().previous());
                        break;
                    case GenericDevice.LSK:
                        applicationState.setState(ApplicationState.METRONOME_OPTIONS);
                        break;
                    case GenericDevice.RSK:
                        applicationState.setState(ApplicationState.MAIN_MENU);
                        Display.getDisplay(midlet).setCurrent(this);
                   
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

    boolean alreadyCancelled = false;
    private void dismiss() {

        if(!alreadyCancelled)
        {
            timer.cancel();
            applicationState.setState(ApplicationState.MAIN_MENU);
            alreadyCancelled = true;
        }
    }

    private class CountDown extends TimerTask {

        public void run() {
            dismiss();
        }
    }
}
