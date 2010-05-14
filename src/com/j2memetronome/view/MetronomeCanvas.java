package com.j2memetronome.view;

import com.j2memetronome.Actions;
import com.j2memetronome.Constants;
import com.j2memetronome.font.FontConstants;
import com.j2memetronome.Metronome;
import com.j2memetronome.device.GenericDevice;
import com.j2memetronome.TxtReader;
import com.j2memetronome.MetronomeMIDlet;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.lcdui.*;

import mwt.Font;
import mwt.Component;

/**
 *
 * @author Deivid Cunha Martins
 */
public class MetronomeCanvas extends Canvas implements Runnable, GenericDevice {

    private static int state;           // Application state
    private int menuIdx;                // To hold the current highlighted menu option
    private int menuLanguageId;
    private Thread thread;          // Menu Thread
    private int firstLineScroll = 0;
    private Timer timer;
    private String languageStr;

    // Images Menu
    private Image imageMenu,
            imageBGMainMenu,
            imageBGTitle,
            imageOK,
            imageCancel,
            imageArrowUp,
            imageArrowDown,
            imageArrowLeft,
            imageArrowRight,
            imageOptionsGrid,
            imageOptionsBar;

    // Images Metronome
    private Image imageBGMetronome,
            imageBall;

    // Image Splash
    private Image imageSplash;
    private Metronome metronome;
    Font[] fontMetronome = new Font[3];
    int fadeRGB[];
    int fadeCount = 255;
    private int count = 1;

    // Animation variables
    private int menuAnimationX = Constants.MENU_ANIMATION_X_INITIAL,
            menuAnimationY = Constants.MENU_ANIMATION_Y_INITIAL;
    // Font
    private Font[] font = new Font[2];
    // Text
    private String textHelp[], textAbout[], textCommons[];
    // Options
    private static int optionsSelectedSoundType = 0;
    private static int optionsSelectedSoundComponents = 0;
    private MetronomeMIDlet midlet;

    private int height, width;

    public MetronomeCanvas(MetronomeMIDlet midlet)
    {  
        setFullScreenMode(true);
        height = getHeight(); 
        width = getWidth();

        //#ifdef LUXURY
//#         Constants.DEVICE_MAX_NUMBER_OF_LINES = Constants.DEVICE_MAX_NUMBER_OF_LINES + 3;
        //#endif
        
        fadeRGB = new int[width * height];
        
        this.midlet = midlet;

        try {
            loadFonts();
            loadImages();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        

        Display.getDisplay(midlet).setCurrent(this);
        

        // default is bpm equals 120 and measure 4/4
        metronome = new Metronome();
        state = Constants.STATE_CHOOSE_LANG;
        timer = new Timer();
        menuIdx = 0;
        // Create Thread and Start
        thread = new Thread(this);
        thread.start();
        repaint();

        
        

    }

    //-------------------------------------------------------------------
    // LOAD RESOURCES FUNCTIONS
    //-------------------------------------------------------------------
    /**
     * Load all fonts used in MainMenu
     * @throws java.io.IOException
     */
    public void loadFonts() throws IOException {
     
        final Image images1 = Image.createImage("/arial_12.png");
        final Image images2 = Image.createImage("/out.png");
        font[0] = new Font(images1, FontConstants.FONT_ARIAL_CHARSET, FontConstants.FONT_ARIAL_WIDTHS, 0);
        font[1] = new Font(images2, FontConstants.FONT_OUT_CHARSET, FontConstants.FONT_OUT_WIDTHS, 0);

        final Image images3 = Image.createImage("/numbers_green.png");
        final Image images4 = Image.createImage("/numbers_red.png");
        final Image images5 = Image.createImage("/tw_font.png");


        fontMetronome[0] = new Font(images3, FontConstants.FONT_GREEN_NUMBERS_CHARSET, FontConstants.FONT_GREEN_NUMBERS_WIDTHS, 0);
        fontMetronome[1] = new Font(images4, FontConstants.FONT_RED_NUMBERS_CHARSET, FontConstants.FONT_RED_NUMBERS_WIDTHS, 0);
        fontMetronome[2] = new Font(images5, FontConstants.FONT_TW_CHARSET, FontConstants.FONT_TW_WIDTHS, 0);
    }

    /**
     * Load all images used in MainMenu
     * @throws java.io.IOException
     */
    public void loadImages() throws IOException {
        imageBGMainMenu = Image.createImage("/bg.png");

        imageBGTitle = Image.createImage("/menu_title.png");
        imageCancel = Image.createImage("/cancel.png");
        imageOK = Image.createImage("/ok.png");


        imageArrowUp = Image.createImage("/arrow_up.png");
        imageArrowDown = Image.createImage("/arrow_down.png");
        imageArrowLeft = Image.createImage("/white_arrow_left.png");
        imageArrowRight = Image.createImage("/white_arrow_right.png");


        imageOptionsGrid = Image.createImage("/optionsGridMainMenu.png");
        imageOptionsBar = Image.createImage("/optionsBar.png");



        imageBall = Image.createImage("/ball.png");

        imageSplash = Image.createImage("/Splash.png");
        
    }
    public void loadImagesLang() throws IOException
    {
        imageMenu = Image.createImage("/images_multilang/" + languageStr + "/menuitems.png");
        imageBGMetronome = Image.createImage("/images_multilang/" + languageStr + "/metronome_canvas_bg.png");
    }

    /**
     * Load about and help text
     * @throws java.io.IOException
     */
    public void loadText() throws IOException {
        try {
            
            TxtReader txtReader = new TxtReader();
            textAbout = txtReader.readFile("/" + languageStr + "/about.txt");
            textHelp = txtReader.readFile("/" + languageStr + "/help.txt");
            textCommons = txtReader.readFile("/" + languageStr + "/common.txt");
        } catch (IOException io) {
            io.printStackTrace();
        }

    }

    //--------------------------------------------------------------
    // DRAW FUNCTIONS
    //--------------------------------------------------------------
    /**
     * Draw Soft Keys
     * @param g
     */
    public void drawSoftKeys(Graphics g) {
        switch (state) {
            case Constants.STATE_MAIN_MENU:
            case Constants.STATE_EXIT:
                
                g.setClip(0, 0, width, height);
                g.drawImage(imageOK, 0, height - imageOK.getHeight(), Graphics.TOP | Graphics.LEFT);
                g.drawImage(imageCancel, width - imageCancel.getWidth(), height - imageCancel.getHeight(), Graphics.TOP | Graphics.LEFT);
                break;
            case Constants.STATE_OPTIONS:
            case Constants.STATE_METRONOME_OPTIONS:
                g.drawImage(imageOK, 0, height - imageOK.getHeight(), Graphics.TOP | Graphics.LEFT);
                break;
            case Constants.STATE_ABOUT:
            case Constants.STATE_HELP:
                g.drawImage(imageCancel, width - imageCancel.getWidth(), height - imageCancel.getHeight(), Graphics.TOP | Graphics.LEFT);
                break;

        }
    }

    /**
     * Draw About
     * @param g
     */
    public void drawAbout(Graphics g) {
        g.drawImage(imageBGMainMenu, (width - imageBGMainMenu.getWidth()) / 2, (height - imageBGMainMenu.getHeight()) / 2, 20);
      
        font[1].write(g, textCommons[Constants.STRING_ABOUT], 5, 0,
                width, font[1].getHeight(), Component.ALIGN_TOP_LEFT);

        g.drawImage(imageOptionsGrid, 0, width/6, Graphics.TOP | Graphics.LEFT);

        if (textHelp.length <= Constants.DEVICE_MAX_NUMBER_OF_LINES) {
            for (int i = 0; i < textAbout.length; i++) {
                font[0].write(g, textAbout[i], 0,
                        Constants.ABOUT_AND_HELP_TEXT_INITIAL_Y
                        + ((int) (font[0].getHeight() * i * 1.5)),
                        width, 0, Component.ALIGN_TOP_CENTER);
            }
        }
        else
        {
            
            g.drawImage(imageArrowUp, width - 15, height/6, Graphics.TOP | Graphics.LEFT);
            g.drawImage(imageArrowDown, width - 15, height - height/6, Graphics.TOP | Graphics.LEFT);

            for (int i = firstLineScroll; i < firstLineScroll + Constants.DEVICE_MAX_NUMBER_OF_LINES; i++) {

                font[0].write(g, textAbout[i], 0,
                        Constants.ABOUT_AND_HELP_TEXT_INITIAL_Y
                        + ((int) (font[0].getHeight() * (i - firstLineScroll) * 1.5)),
                        width, 0, Component.ALIGN_TOP_CENTER);
            }
        }

    }

    /**
     * Draw Help
     * @param g
     */
    public void drawHelp(Graphics g) {
        g.drawImage(imageBGMainMenu, (width - imageBGMainMenu.getWidth()) / 2, (height - imageBGMainMenu.getHeight()) / 2, 20);
      
        font[1].write(g, textCommons[Constants.STRING_HELP], 5, 0,
                width, font[1].getHeight(), Component.ALIGN_TOP_LEFT);

         g.drawImage(imageOptionsGrid, 0, width/6, Graphics.TOP | Graphics.LEFT);
         
        if (textHelp.length <= Constants.DEVICE_MAX_NUMBER_OF_LINES) {
            for (int i = 0; i < textHelp.length; i++) {
                font[0].write(g, textHelp[i], 0,
                        Constants.ABOUT_AND_HELP_TEXT_INITIAL_Y
                        + ((int) (font[0].getHeight() * i * 1.5)),
                        width, 0, Component.ALIGN_TOP_CENTER);
            }
        } else {
            
            g.drawImage(imageArrowUp, width - 15, height/6, Graphics.TOP | Graphics.LEFT);
            g.drawImage(imageArrowDown, width - 15, height - height/6, Graphics.TOP | Graphics.LEFT);

            for (int i = firstLineScroll; i < firstLineScroll + Constants.DEVICE_MAX_NUMBER_OF_LINES; i++) {

                font[0].write(g, textHelp[i], 0,
                        Constants.ABOUT_AND_HELP_TEXT_INITIAL_Y
                        + ((int) (font[0].getHeight() * (i - firstLineScroll) * 1.5)),
                        width, 0, Component.ALIGN_TOP_CENTER);
            }


        }
    }

    /**
     * Draw Options Menu
     * @param g
     */
    public void drawOptions(Graphics g) {
        g.drawImage(imageBGMainMenu, (width - imageBGMainMenu.getWidth()) / 2, (height - imageBGMainMenu.getHeight()) / 2, 20);
      
        font[1].write(g, textCommons[Constants.STRING_OPTIONS], 5, 0,
                width, font[1].getHeight(), Component.ALIGN_TOP_LEFT);

        g.drawImage(imageArrowLeft, 5, height/2 -10, Graphics.TOP | Graphics.LEFT);
        g.drawImage(imageArrowRight, width - 5 - imageArrowRight.getWidth(), height/2 -10, Graphics.TOP | Graphics.LEFT);
        g.drawImage(imageOptionsBar, 0, height/2 -20, Graphics.TOP | Graphics.LEFT);

        font[0].write(g, this.textCommons[Constants.STRING_KITS], 0, height/2 -10,
                width, font[1].getHeight(), Component.ALIGN_TOP_CENTER);

        font[0].write(g, textCommons[Constants.STRING_BASS_DRUM_AND_SNARE + optionsSelectedSoundComponents], 0, height/2 + 10,
                width, font[1].getHeight(), Component.ALIGN_TOP_CENTER);



    }

    /**
     * Draw Exit Menu
     * @param g
     */
    public void drawExit(Graphics g) {
        g.drawImage(imageBGMainMenu, (width - imageBGMainMenu.getWidth()) / 2, (height - imageBGMainMenu.getHeight()) / 2, 20);
      
        font[1].write(g, textCommons[Constants.STRING_EXIT], 5, 0,
                width, font[1].getHeight(), Component.ALIGN_TOP_LEFT);

        font[0].write(g, textCommons[Constants.STRING_EXIT_TEXT], 0, 80,
                width, 0, Component.ALIGN_TOP_CENTER);
    }

    /**
     * Draw Main Menu
     * @param g
     */
    public void drawMenu(Graphics g) {
        g.drawImage(imageBGMainMenu, (width - imageBGMainMenu.getWidth()) / 2, (height - imageBGMainMenu.getHeight()) / 2, 20);
      
        int cy = 0;

        menuAnimation(g);
        
        for (int i = 0; i < Constants.MAIN_MENU_LENGHT; i++) {
            //#ifndef LUXURY
            cy = 45 + (i * 22);

            g.setClip(23, cy, 82, 20);


            if (menuIdx == i) {
                g.drawImage(imageMenu, 23, cy - 20, Graphics.TOP | Graphics.LEFT);
            } else {
                g.drawImage(imageMenu, 23, cy, Graphics.TOP | Graphics.LEFT);
            }

            //offset of the label is 6 pixels from the top of the button
            cy += 6;
            //set the clipping rectangle to where the label will be drawn
            g.setClip(23, cy, 82, 10);
            //draw the label so that it is inside the clipping rectangle
            g.drawImage(imageMenu, 23, cy - (40 + (i * 10)), Graphics.TOP | Graphics.LEFT);
            //#else
//#             cy = 67 + (i * 33);
//#           
//#             g.setClip(34, cy, 113, 27);
//# 
//# 
//#            if (menuIdx == i) {
//#                 g.drawImage(imageMenu, 34, cy - 27 , Graphics.TOP | Graphics.LEFT);
//#             } else {
//#                 g.drawImage(imageMenu, 34, cy, Graphics.TOP | Graphics.LEFT);
//#             }
//# 
//#             //offset of the label is 6 pixels from the top of the button
//#             cy += 7;
//#             
//#             //set the clipping rectangle to where the label will be drawn
//#             g.setClip(34, cy , 113, 13);
//#             // draw the label so that it is inside the clipping rectangle
//#             g.drawImage(imageMenu, 34, cy - (55 + (i * 14)), Graphics.TOP | Graphics.LEFT);
//#            
//#             
            //#endif
        }

    }

    void drawSplash(Graphics g) {
        if (imageSplash != null) {
            g.drawImage(imageSplash, 0, 0, Graphics.TOP | Graphics.LEFT);
        }
    }

    void drawChooseLanguage(Graphics g)
    {
        g.drawImage(this.imageBGMainMenu, 0, 0, Graphics.TOP | Graphics.LEFT);
            g.setColor(0x111111);
            g.fillRect(10, 75, width - 20, 20);
            g.setColor(0xFFFFFF);
            g.drawRect(10, 75, width - 20, 20);

            g.setColor(0x111111);
            g.fillRect(10, 95, width - 20, 20);
            g.setColor(0xFFFFFF);
            g.drawRect(10, 95, width - 20, 20);


            
        if(menuLanguageId == Constants.ENGLISH)
        {
            g.setColor(0x555555);
            g.fillRect(10, 75, width - 20, 10);
            g.setColor(0x777777);
            g.fillRect(10, 85, width - 20, 10);
            g.setColor(0xFFFFFF);
            g.drawRect(10, 75, width - 20, 20);
            font[1].write(g, "CHOOSE", 0, 5, width, font[1].getHeight(), Component.ALIGN_TOP_CENTER);
            font[1].write(g, "YOUR", 0, 25, width, font[1].getHeight(), Component.ALIGN_TOP_CENTER);
            font[1].write(g, "LANGUAGE", 0, 45, width, font[1].getHeight(), Component.ALIGN_TOP_CENTER);
        }
        else if(menuLanguageId == Constants.PORTUGUESE)
        {
            g.setColor(0x555555);
            g.fillRect(10, 95, width - 20, 10);
            g.setColor(0x777777);
            g.fillRect(10, 105, width - 20, 10);
            g.setColor(0xFFFFFF);
            g.drawRect(10, 95, width - 20, 20);
            font[1].write(g, "ESCOLHA", 0, 5, width, font[1].getHeight(), Component.ALIGN_TOP_CENTER);
            font[1].write(g, "SEU", 0, 25, width, font[1].getHeight(), Component.ALIGN_TOP_CENTER);
            font[1].write(g, "IDIOMA", 0, 45, width, font[1].getHeight(), Component.ALIGN_TOP_CENTER);
        }
        
        
        font[0].write(g,"ENGLISH", 0, 80, width, font[0].getHeight(), Component.ALIGN_TOP_CENTER);
        font[0].write(g,"PORTUGUÊS", 0, 100, width, font[0].getHeight(), Component.ALIGN_TOP_CENTER);

        
    }

    public void drawMetronome(Graphics g) {
        g.setColor(0x00000000);
        g.fillRect(0, 0, width, height);
        // background
        g.drawImage(imageBGMetronome, 0, 0, Graphics.TOP | Graphics.LEFT);


        if (MetronomeCanvas.getState() == Constants.STATE_METRONOME_STARTED) {
            taskMetronome(g);
        }


        // Measure
        fontMetronome[2].write(g, metronome.getNumerator() + "/" + metronome.getDenominator(), 10, height/2 + height/10, width, 0, Component.ALIGN_TOP_CENTER);
        // BPM
        fontMetronome[2].write(g, String.valueOf(metronome.getBeatsPerMinute()), 10, height/2 + height/4 - 10, width, 0, Component.ALIGN_TOP_CENTER);

        //#ifdef LUXURY
//#         g.drawImage(imageBall, Constants.BALL_BPM_INITIAL_X + (int) (metronome.getBeatsPerMinute() * 0.43), width + 1, Graphics.TOP | Graphics.LEFT);
        //#else
         g.drawImage(imageBall, Constants.BALL_BPM_INITIAL_X + (int) (metronome.getBeatsPerMinute() * 0.3), width + 1, Graphics.TOP | Graphics.LEFT);
        //#endif
    }
    //-----------------------------------------------------------------------
    // ANIMATIONS
    //-----------------------------------------------------------------------
    boolean animationFinish1 = false;
    boolean animationFinish2 = false;

    /**
     * Draw game title animation
     * @param g
     */
    public void menuAnimation(Graphics g) {

        if (!animationFinish1) {
            menuAnimationX += 1;
            if (menuAnimationX > 100) {
                animationFinish1 = true;
            }

        } else if (animationFinish1 && !animationFinish2) {
            menuAnimationX -= 5;
            if (menuAnimationX <= 5) {
                animationFinish2 = true;
            }

        }
        g.drawImage(imageBGTitle, menuAnimationX, menuAnimationY, Graphics.TOP | Graphics.LEFT);

    }

    public void resetAnimation() {
        animationFinish2 = animationFinish1 = false;
        menuAnimationX = Constants.MENU_ANIMATION_X_INITIAL;
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

    public void processEvents(int keyCode) {
        switch (state) {
            case Constants.STATE_CHOOSE_LANG:
                switch (keyCode) {
                    case Canvas.KEY_NUM5:
                    case DEVICE_BUTTON_LSK:
                    case DEVICE_BUTTON_FIRE: {
                        switch (menuLanguageId) {
                            case Constants.ENGLISH:
                                
                                languageStr = Constants.ENGLISH_RES;
                                try {
                                    this.loadImagesLang();
                                    this.loadText();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                state = Constants.STATE_SPLASH;
                                break;

                            case Constants.PORTUGUESE:
                                
                                languageStr = Constants.PORTUGUESE_RES;
                                try {
                                    this.loadImagesLang();
                                    this.loadText();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                state = Constants.STATE_SPLASH;
                                break;
                            default:
                                
                                languageStr = Constants.ENGLISH_RES;
                                try {
                                    this.loadImagesLang();
                                    this.loadText();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                state = Constants.STATE_SPLASH;
                                break;
                        }
                        break;
                    }
                    case DEVICE_BUTTON_UP:
                        if (menuLanguageId == 0) {
                            menuIdx = 1;
                        } else if (menuLanguageId - 1 >= 0) {
                            menuLanguageId--;
                        }
                        break;
                    case DEVICE_BUTTON_DOWN:
                        if (menuLanguageId + 1 < 2) {
                            menuLanguageId++;
                        } else {
                            menuLanguageId = 0;
                        }

                        break;
                }
                break;
            case Constants.STATE_MAIN_MENU:
                switch (keyCode) {
                    case Canvas.KEY_NUM5:
                    case DEVICE_BUTTON_LSK:
                    case DEVICE_BUTTON_FIRE: {
                        switch (menuIdx) {
                            case 0:
                                state = Constants.STATE_START;
                                state = Constants.STATE_METRONOME_STOPPED;

                                break;

                            case 1:
                                state = Constants.STATE_OPTIONS;
                                break;
                            case 2:
                                state = Constants.STATE_HELP;
                                break;
                            case 3:
                                state = Constants.STATE_ABOUT;
                                break;

                        }
                        break;
                    }
                    case DEVICE_BUTTON_RSK:
                    case DEVICE_BUTTON_CLEAR:
                        state = Constants.STATE_EXIT;
                        break;
                    case DEVICE_BUTTON_UP:
                        if (menuIdx == 0) {
                            menuIdx = 3;
                        } else if (menuIdx - 1 >= 0) {
                            menuIdx--;
                        }
                        break;
                    case DEVICE_BUTTON_DOWN:
                        if (menuIdx + 1 < Constants.MAIN_MENU_LENGHT) {
                            menuIdx++;
                        } else {
                            menuIdx = 0;
                        }

                        break;
                }
                break;
            case Constants.STATE_HELP:
                case Constants.STATE_ABOUT:
                switch (keyCode) {
                    case DEVICE_BUTTON_RSK:
                    case DEVICE_BUTTON_CLEAR:
                        state = Constants.STATE_MAIN_MENU;
                        resetAnimation();
                        firstLineScroll = 0;
                        break;
                    case DEVICE_BUTTON_UP:
                        if (firstLineScroll > 0) {
                            firstLineScroll--;
                        }
                        break;
                    case DEVICE_BUTTON_DOWN:

                        if (firstLineScroll < textHelp.length - Constants.DEVICE_MAX_NUMBER_OF_LINES && state == Constants.STATE_HELP) {
                            this.firstLineScroll++;
                        }
                        else if(firstLineScroll < textAbout.length - Constants.DEVICE_MAX_NUMBER_OF_LINES && state == Constants.STATE_ABOUT) {
                            this.firstLineScroll++;
                        }

                        break;


                }
                break;
            case Constants.STATE_OPTIONS:
            case Constants.STATE_METRONOME_OPTIONS:
                switch (keyCode) {
                    case DEVICE_BUTTON_LSK:
                    case DEVICE_BUTTON_CLEAR:

                        if (state == Constants.STATE_OPTIONS) {
                            state = Constants.STATE_MAIN_MENU;
                            resetAnimation();
                        } else {
                            state = Constants.STATE_METRONOME_STOPPED;
                        }
                        break;
                    case DEVICE_BUTTON_UP:

                    case DEVICE_BUTTON_RIGHT:
                    case Canvas.KEY_NUM6:

                    case Constants.OPTIONS_SOUND_KITS:
                        if (optionsSelectedSoundComponents + 1 < Constants.DIFFERENT_SOUNDS) {
                            optionsSelectedSoundComponents++;
                        } else if (optionsSelectedSoundComponents + 1 == Constants.DIFFERENT_SOUNDS) {
                            optionsSelectedSoundComponents = 0;
                        }
                        break;


                    case DEVICE_BUTTON_LEFT:
                    case Canvas.KEY_NUM4:

                        if (optionsSelectedSoundComponents > 0) {
                            optionsSelectedSoundComponents--;
                        } else if (optionsSelectedSoundComponents - 1 < 0) {
                            optionsSelectedSoundComponents = Constants.DIFFERENT_SOUNDS - 1;
                        }
                        break;



                }
                break;
            case Constants.STATE_EXIT:
                switch (keyCode) {
                    case DEVICE_BUTTON_RSK:
                    case DEVICE_BUTTON_CLEAR:
                        state = Constants.STATE_MAIN_MENU;
                        resetAnimation();
                        break;
                    case Canvas.KEY_NUM5:
                    case DEVICE_BUTTON_FIRE:
                    case DEVICE_BUTTON_LSK:
                        midlet.kill();
                        break;

                }
                break;

            case Constants.STATE_METRONOME_STARTED:
            case Constants.STATE_METRONOME_STOPPED:
                switch (keyCode) {
                    case DEVICE_BUTTON_RIGHT:
                        stop();
                        if (metronome.getBeatsPerMinute() < Metronome.BPM_MAX) {
                            metronome.setBeatsPerMinute(metronome.getBeatsPerMinute() + 1);
                        }
                        break;
                    case DEVICE_BUTTON_LEFT:
                        stop();
                        if (metronome.getBeatsPerMinute() > Metronome.BPM_MIN) {
                            metronome.setBeatsPerMinute(metronome.getBeatsPerMinute() - 1);
                        }
                        break;
                    case DEVICE_BUTTON_UP:
                        metronome.setNumerator(metronome.getNumerator() + 1);
                        break;
                    case DEVICE_BUTTON_DOWN:
                        metronome.setNumerator(metronome.getNumerator() == 1 ? 1 : metronome.getNumerator() - 1);
                        break;
                    case DEVICE_BUTTON_FIRE:
                    case Canvas.KEY_NUM5:
                        if (MetronomeCanvas.getState() == Constants.STATE_METRONOME_STARTED) {
                            stop();
                        } else if (MetronomeCanvas.getState() == Constants.STATE_METRONOME_STOPPED) {
                            start();
                        }
                        break;
                    case Canvas.KEY_NUM2:
                        metronome.setDenominator(metronome.getDenominator(), Actions.INCREMENT);
                        break;
                    case Canvas.KEY_NUM8:
                        metronome.setDenominator(metronome.getDenominator(), Actions.DECREMENT);
                        break;
                    case DEVICE_BUTTON_LSK:
                        MetronomeCanvas.setState(Constants.STATE_METRONOME_OPTIONS);
                        break;
                    case DEVICE_BUTTON_RSK:
                        MetronomeCanvas.setState(Constants.STATE_MAIN_MENU);
                        Display.getDisplay(midlet).setCurrent(this);
                        MetronomeCanvas.setState(Constants.STATE_MAIN_MENU);
                        break;
                    case Canvas.KEY_POUND:
                           if(optionsSelectedSoundComponents < Constants.DIFFERENT_SOUNDS - 1)
                                optionsSelectedSoundComponents++;
                           else
                               optionsSelectedSoundComponents = 0;
                           break;

                }
                break;

        }
    }

    public void run() {
        while (getState() != Constants.STATE_KILL) {
            if (getState() == Constants.STATE_SPLASH) {

                timer.schedule(new CountDown(), 5000);

            }

            if (getState() < Constants.STATE_METRONOME_STARTED) {
                repaint();
                this.serviceRepaints();

                try {
                    Thread.sleep(14);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else if (getState() >= Constants.STATE_METRONOME_STARTED) {
                try {
                    repaint();
                    if (MetronomeCanvas.getState() != Constants.STATE_METRONOME_STARTED) {
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

    public void paint(Graphics g) {
        g.setColor(0x00000000);
        g.fillRect(0, 0, width, height);

        

        switch (state) {
            case Constants.STATE_CHOOSE_LANG:
                this.drawChooseLanguage(g);
                break;
            case Constants.STATE_SPLASH:
                drawSplash(g);
                break;

            case Constants.STATE_MAIN_MENU:
                drawMenu(g);
                break;
            case Constants.STATE_OPTIONS:
            case Constants.STATE_METRONOME_OPTIONS:
                drawOptions(g);
                break;
            case Constants.STATE_HELP:
                drawHelp(g);
                break;
            case Constants.STATE_ABOUT:
                drawAbout(g);
                break;
            case Constants.STATE_EXIT:
                drawExit(g);
                break;
            case Constants.STATE_METRONOME_STARTED:
            case Constants.STATE_METRONOME_STOPPED:
                drawMetronome(g);
                break;
        }
        // Draw Background Image
        drawSoftKeys(g);
    }
    //----------------------------------------------------------------------
    // GETTERS AND SETTERS
    //----------------------------------------------------------------------

    public static int getState() {
        return state;
    }

    public static void setState(int state) {
        MetronomeCanvas.state = state;
    }

    public static int getSoundType() {
        return optionsSelectedSoundType;
    }

    public static void setSoundType(int type) {
        MetronomeCanvas.optionsSelectedSoundType = type;
    }

    public static int getSoundComponents() {
        return optionsSelectedSoundComponents;
    }

    public static void setSoundComponents(int components) {
        MetronomeCanvas.optionsSelectedSoundComponents = components;
    }

    /**
     * start metronome
     */
    public void start() {
        MetronomeCanvas.setState(Constants.STATE_METRONOME_STARTED);
    }

    /**
     * Stop Metronome
     */
    public void stop() {
        count = 1;
        MetronomeCanvas.setState(Constants.STATE_METRONOME_STOPPED);
    }

    public synchronized void  taskMetronome(Graphics g) {
        if (count == 1) {
            fontMetronome[1].write(g, String.valueOf(count++), 0, height/5, width, 0, Component.ALIGN_TOP_CENTER);

            if (MetronomeCanvas.getSoundComponents() == 0) {
                metronome.playSnare();
            } else if (MetronomeCanvas.getSoundComponents() == 1) {
                metronome.playBassDrumAndCrash();
            } else if(getSoundComponents() == 2) {
                metronome.playTomsLow();
            }
            else
                metronome.playMetronomeBell();

        } else if (count == metronome.getNumerator() || count > metronome.getNumerator()) {
            fontMetronome[0].write(g, String.valueOf(count++), 0, height/5, width, 0, Component.ALIGN_TOP_CENTER);
            if (MetronomeCanvas.getSoundComponents() == 2) {
                metronome.playTomsMid();
            }
            else if(getSoundComponents() == 3)
            {
                metronome.playMetronomeClick();
            }
            else if (MetronomeCanvas.getSoundComponents() == 0) {
                metronome.playBassDrum();
            } else if (MetronomeCanvas.getSoundComponents() == 1) {
                metronome.playSnare();
            } else if(getSoundComponents() == 2){
                metronome.playToms();
            }

            count = 1;
        } else {
            fontMetronome[0].write(g, String.valueOf(count++), 0, height/5, width, 0, Component.ALIGN_TOP_CENTER);

            if (MetronomeCanvas.getSoundComponents() == 0) {
                metronome.playBassDrum();
            } else if (MetronomeCanvas.getSoundComponents() == 1) {
                metronome.playBassDrumAndHiHat();
            } else if(getSoundComponents() == 2){
                metronome.playToms();
            }
            else
            {
                metronome.playMetronomeClick();
            }
        }
    }

    //----------------------------------------------------
    // Animations and effects
    //----------------------------------------------------
    /**
     *
     * @param mode
     */
    public void fade(int mode) {
        int i;
        int r, g, b;
        switch (mode) {
            case Constants.FADE_OUT:
                for (i = 0; i < (width * height); i++) {
                    r = (fadeRGB[i] & 255);
                    g = ((fadeRGB[i] >> 8) & 255);
                    b = ((fadeRGB[i] >> 16) & 255);
                    fadeRGB[i] = ((fadeCount << 24) | (b << 16) | (g << 8) | r);
                }
                fadeCount = fadeCount < 255 - 12 ? fadeCount + 12 : 255;
                break;
            case Constants.FADE_IN:
                for (i = 0; i < (width * height); i++) {
                    r = (fadeRGB[i] & 255);
                    g = ((fadeRGB[i] >> 8) & 255);
                    b = ((fadeRGB[i] >> 16) & 255);
                    fadeRGB[i] = ((fadeCount << 24) | (b << 16) | (g << 8) | r);
                }
                fadeCount = fadeCount > 12 ? fadeCount - 12 : 0;
                break;

        }
    }

    
    public void hideNotify()
    {
        
        state = Constants.STATE_METRONOME_STOPPED;

    }
    public void showNotify()
    {
        
    }

    private void dismiss() {
        timer.cancel();
        state = Constants.STATE_MAIN_MENU;
    }

    private class CountDown extends TimerTask {

        public void run() {
            dismiss();
        }
    }
}
