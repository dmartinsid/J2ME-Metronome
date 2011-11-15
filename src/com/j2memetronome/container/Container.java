package com.j2memetronome.container;

import com.j2memetronome.model.Metronome;
import com.j2memetronome.MetronomeMIDlet;
import com.j2memetronome.appstate.ApplicationState;
import com.j2memetronome.dao.FontDAO;
import com.j2memetronome.dao.FontDAOFileSystem;
import com.j2memetronome.dao.ImageDAO;
import com.j2memetronome.dao.ImageDAOFileSystem;
import com.j2memetronome.dao.TextDAO;
import com.j2memetronome.dao.TextDAOFileSystem;
import com.j2memetronome.device.DeviceSpecification;
import com.j2memetronome.view.Menu;
import com.j2memetronome.view.View;
import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author dmartins
 */
public class Container extends Canvas implements Runnable {

    private ApplicationState applicationState;
    private Thread applicationThread;
    private Metronome metronome;
    private MetronomeMIDlet midlet;
    private View view;
    
    // Persistence
    private FontDAO fontDAO;
    private ImageDAO imageDAO;
    private TextDAO textDAO;
    
    // Splash
    private SplashCountDown countDown;
    private Timer timer;
    
    private EventHandler eventHandler;

    public Container(MetronomeMIDlet midlet, View view) {
        this.midlet = midlet;
        this.view = view;

        initPersistenceLayer();

        metronome = new Metronome();
        applicationState = new ApplicationState();
        timer = new Timer();
        eventHandler = new EventHandler();

        Display.getDisplay(midlet).setCurrent(this);
        setFullScreenMode(true);

        applicationThread = new Thread(this);
        applicationThread.start();

    }

    private void initPersistenceLayer() {
        fontDAO = new FontDAOFileSystem();
        imageDAO = new ImageDAOFileSystem();
        textDAO = new TextDAOFileSystem();
    }

    protected void paint(Graphics graphics) {
        try {
            view.draw(graphics, fontDAO, imageDAO, textDAO, applicationState, metronome);
        } catch (Exception exception) {
            exception.printStackTrace();

        }

    }

    public void run() {
        while (applicationState.getState() != ApplicationState.KILL) {
            repaint();
            serviceRepaints();

            if (applicationState.getState() == ApplicationState.SPLASH) {
                if (countDown == null) {
                    countDown = new SplashCountDown();
                    timer.schedule(countDown, 5000);
                }
            }

            try {
                sleep();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void sleep() throws InterruptedException {
        if (applicationState.getState() != ApplicationState.METRONOME_STARTED) {
            Thread.sleep(50);
        } else {
            Thread.sleep(metronome.sleepTime());
        }
    }

    protected void keyPressed(int keyCode) {
        eventHandler.onKey(keyCode);
    }

    protected void keyRepeated(int keyCode) {
        eventHandler.onKey(keyCode);
    }

    private class SplashCountDown extends TimerTask {

        public void run() {
            dismiss();
        }

        private void dismiss() {
            timer.cancel();
            applicationState.setState(ApplicationState.MAIN_MENU);
        }
    }

    private class EventHandler {

        public void onKey(int keyCode) {
            handleEvents(keyCode);
        }

        private void handleEvents(int keyCode) {

            switch (applicationState.getState()) {

                case ApplicationState.MAIN_MENU:
                    handleMainMenu(keyCode);
                    break;
                case ApplicationState.HELP:
                case ApplicationState.ABOUT:
                    handleHelpAndAbout(keyCode);
                    break;
                case ApplicationState.OPTIONS:
                    handleOptions(keyCode);
                    break;
                case ApplicationState.EXIT:
                    handleExit(keyCode);
                    break;
                case ApplicationState.METRONOME_STARTED:
                case ApplicationState.METRONOME_STOPPED:
                    handleMetronome(keyCode);
                    break;

            }
        }

        private void handleMainMenu(int keyCode) {

            switch (keyCode) {
                case KEY_NUM5:
                case DeviceSpecification.LSK:
                case DeviceSpecification.FIRE: {
                    switch (view.menu().getIndex()) {
                        case Menu.START:
                            applicationState.setState(ApplicationState.METRONOME_STOPPED);
                            break;
                        case Menu.OPTIONS:
                            applicationState.setState(ApplicationState.OPTIONS);
                            break;
                        case Menu.HELP:
                            applicationState.setState(ApplicationState.HELP);
                            break;
                        case Menu.ABOUT:
                            applicationState.setState(ApplicationState.ABOUT);
                            break;
                    }
                    break;
                }
                case DeviceSpecification.RSK:
                case DeviceSpecification.CLEAR:
                    applicationState.setState(ApplicationState.EXIT);
                    break;
                case DeviceSpecification.UP:
                    view.menu().previousIndex();
                    break;
                case DeviceSpecification.DOWN:
                    view.menu().nextIndex();
                    break;
            }

        }

        private void handleHelpAndAbout(int keyCode) {
            switch (keyCode) {
                case DeviceSpecification.RSK:
                    applicationState.setState(ApplicationState.MAIN_MENU);
                    break;
            }

        }

        private void handleOptions(int keyCode) {
            switch (keyCode) {
                case DeviceSpecification.LSK:
                case DeviceSpecification.CLEAR:
                    if (applicationState.lastState() == ApplicationState.MAIN_MENU) {
                        applicationState.setState(ApplicationState.MAIN_MENU);

                    } else {
                        applicationState.setState(ApplicationState.METRONOME_STOPPED);
                    }
                    break;
                case DeviceSpecification.RIGHT:
                case Canvas.KEY_NUM6:
                    metronome.nextKit(view.supportedSounds());
                    break;

                case DeviceSpecification.LEFT:
                case Canvas.KEY_NUM4:

                    metronome.previousKit(view.supportedSounds());
                    break;



            }
        }

        private void handleExit(int keyCode) {
            switch (keyCode) {
                case DeviceSpecification.RSK:
                case DeviceSpecification.CLEAR:
                    applicationState.setState(ApplicationState.MAIN_MENU);
                    break;
                case Canvas.KEY_NUM5:
                case DeviceSpecification.FIRE:
                case DeviceSpecification.LSK:
                    midlet.kill();
                    break;

            }
        }

        private void handleMetronome(int keyCode) {
            switch (keyCode) {

                case DeviceSpecification.RIGHT:
                    applicationState.setState(ApplicationState.METRONOME_STOPPED);
                    metronome.increaseBeatsPerMinute();

                    break;
                case DeviceSpecification.LEFT:
                    applicationState.setState(ApplicationState.METRONOME_STOPPED);
                    metronome.decreaseBeatsPerMinute();
                    break;
                case DeviceSpecification.UP:
                    metronome.getMeasure().increaseNumerator();
                    break;
                case DeviceSpecification.DOWN:
                    metronome.getMeasure().decreaseNumerator();
                    break;
                case DeviceSpecification.FIRE:
                case Canvas.KEY_NUM5:
                    if (applicationState.getState() == ApplicationState.METRONOME_STARTED) {
                        applicationState.setState(ApplicationState.METRONOME_STOPPED);
                    } else{
                        applicationState.setState(ApplicationState.METRONOME_STARTED);
                    }
                    break;
                case Canvas.KEY_NUM2:
                    metronome.getMeasure().increaseDenominator();
                    break;
                case Canvas.KEY_NUM8:
                    metronome.getMeasure().decreaseDenominator();
                    break;
                case DeviceSpecification.LSK:
                    applicationState.setState(ApplicationState.OPTIONS);
                    break;
                case DeviceSpecification.RSK:
                    applicationState.setState(ApplicationState.MAIN_MENU);

                    break;
                case Canvas.KEY_POUND:
                    metronome.nextKit(view.supportedSounds());
                    break;

            }
        }
    }
}
