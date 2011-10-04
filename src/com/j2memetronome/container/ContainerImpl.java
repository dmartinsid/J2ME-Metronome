/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2memetronome.container;

import com.j2memetronome.Metronome;
import com.j2memetronome.MetronomeMIDlet;
import com.j2memetronome.appstate.ApplicationState;
import com.j2memetronome.dao.FontDAO;
import com.j2memetronome.dao.FontDAOFileSystem;
import com.j2memetronome.dao.ImageDAO;
import com.j2memetronome.dao.ImageDAOFileSystem;
import com.j2memetronome.dao.TextDAO;
import com.j2memetronome.dao.TextDAOFileSystem;
import com.j2memetronome.device.GenericDevice;
import com.j2memetronome.resource.ResourceLoader;
import com.j2memetronome.view.Menu;
import com.j2memetronome.view.View;
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
    //-----------------------------------------
    // MENU
    //-----------------------------------------
    private Metronome metronome;
    // Options
    private MetronomeMIDlet midlet;
    private View view;
    private Timer timer;
    private Menu menu;
    // Persistence
    private FontDAO fontDAO;
    private ImageDAO imageDAO;
    private TextDAO textDAO;

    public ContainerImpl(MetronomeMIDlet midlet, View view) {
        this.midlet = midlet;
        this.view = view;

        fontDAO = new FontDAOFileSystem();
        imageDAO = new ImageDAOFileSystem();
        textDAO = new TextDAOFileSystem();



        setFullScreenMode(true);



        metronome = new Metronome();
        applicationState = new ApplicationState(ApplicationState.SPLASH);
        timer = new Timer();

        Display.getDisplay(midlet).setCurrent(this);

        menu = new Menu();

        applicationThread = new Thread(this);
        applicationThread.start();
        repaint();
    }

    protected void paint(Graphics graphics) {
        try {
            view.draw(graphics, fontDAO, imageDAO, textDAO, applicationState, metronome);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
    CountDown countDown;

    public void run() {
        while (applicationState.getState() != ApplicationState.KILL) {
            repaint();
            serviceRepaints();

            if (applicationState.getState() == ApplicationState.SPLASH) {

                if (countDown == null) {
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

    public void processMainMenu(int keyCode) {

        switch (keyCode) {
            case KEY_NUM5:
            case GenericDevice.LSK:
            case GenericDevice.FIRE: {
                switch (Menu.getIndex()) {
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

            case ApplicationState.MAIN_MENU:
                processMainMenu(keyCode);
                break;
            case ApplicationState.HELP:
            case ApplicationState.ABOUT:
                switch (keyCode) {
                    case GenericDevice.RSK:
                    case GenericDevice.CLEAR:
                        applicationState.setState(ApplicationState.MAIN_MENU);

                        break;


                }
                break;
            case ApplicationState.OPTIONS:
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


                        if (metronome.getKit() + 1 < view.supportedSounds()) {
                            metronome.setKit(metronome.getKit() + 1);

                        } else {
                            metronome.setKit(0);
                        }
                        break;


                    case GenericDevice.LEFT:
                    case Canvas.KEY_NUM4:

                        if (metronome.getKit() > 0) {
                            metronome.setKit(metronome.getKit() - 1);
                        } else {
                            metronome.setKit(view.supportedSounds() - 1);
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
                            applicationState.setState(ApplicationState.METRONOME_STARTED);
                        }
                        break;
                    case Canvas.KEY_NUM2:
                        metronome.setDenominator(metronome.getDenominator().next());
                        break;
                    case Canvas.KEY_NUM8:
                        metronome.setDenominator(metronome.getDenominator().previous());
                        break;
                    case GenericDevice.LSK:
                        applicationState.setState(ApplicationState.OPTIONS);
                        break;
                    case GenericDevice.RSK:
                        applicationState.setState(ApplicationState.MAIN_MENU);
                        Display.getDisplay(midlet).setCurrent(this);

                        break;
                    case Canvas.KEY_POUND:
                        if (metronome.getKit() < view.supportedSounds() - 1) {
                            metronome.setKit(metronome.getKit() + 1);
                        } else {
                            metronome.setKit(0);
                        }
                        break;

                }
                break;

        }
    }
    boolean alreadyCancelled = false;

    private void dismiss() {

        if (!alreadyCancelled) {
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
