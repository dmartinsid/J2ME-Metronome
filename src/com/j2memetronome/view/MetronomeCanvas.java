package com.j2memetronome.view;

import com.j2memetronome.Actions;
import com.j2memetronome.Constants;
import com.j2memetronome.FontConstants;
import com.j2memetronome.Metronome;
import com.j2memetronome.device.MidsizeDevice;
import com.j2memetronome.TxtReader;
import com.j2memetronome.dmartinsMetronomeMIDlet;

import java.io.IOException;
import javax.microedition.lcdui.*;

import mwt.Font;
import mwt.Component;
/**
 *
 * @author Deivid Martins
 */
public class MetronomeCanvas extends Canvas implements Runnable, MidsizeDevice
{
    private static int state;           // Application state
    private int menuIdx;                // To hold the current highlighted menu option
    private Thread menuThread;          // Menu Thread
    private int firstLineScroll = 0;

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

     

    private Metronome metronome;

    Font[] fontMetronome = new Font[3];
    int fadeRGB[] = new int[DEVICE_WIDTH * DEVICE_HEIGHT];
    int fadeCount = 255;
    private int count = 1;


    // Animation variables
    private int menuAnimationX = Constants.MENU_ANIMATION_X_INITIAL,  
            menuAnimationY = Constants.MENU_ANIMATION_Y_INITIAL;



    // Font
    private Font[] font = new Font[2];

    // Text
    private String textHelp[], textAbout[];

    // Options
    private int optionSelected = 0;
    private static int optionsSelectedSoundType = 0;
    private static int optionsSelectedSoundComponents = 0;
    
    public MetronomeCanvas()
    {
        state = Constants.STATE_MAIN_MENU;
        menuIdx = 0;
        try
        {
            loadFonts();
            loadImages();
            loadText();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }

        setFullScreenMode(true);

        // default is bpm equals 120 and measure 4/4
        metronome = new Metronome();
        
        // Create Thread and Start
        menuThread = new Thread(this);
        menuThread.start();
    }

    

    //-------------------------------------------------------------------
    // LOAD RESOURCES FUNCTIONS
    //-------------------------------------------------------------------

    /**
     * Load all fonts used in MainMenu
     * @throws java.io.IOException
     */
    public void loadFonts() throws IOException
    {
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
    public void loadImages() throws IOException
    {
        imageBGMainMenu = Image.createImage("/bg.png");
        imageMenu = Image.createImage("/menuitems.png");
        imageBGTitle = Image.createImage("/menu_title.png");
        imageCancel = Image.createImage("/cancel.png");
        imageOK = Image.createImage("/ok.png");

        
        imageArrowUp = Image.createImage("/arrow_up.png");
        imageArrowDown = Image.createImage("/arrow_down.png");
        imageArrowLeft = Image.createImage("/white_arrow_left.png");
        imageArrowRight = Image.createImage("/white_arrow_right.png");

        
        imageOptionsGrid = Image.createImage("/optionsGridMainMenu.png");
        imageOptionsBar = Image.createImage("/optionsBar.png");

        imageBGMetronome = Image.createImage("/metronome_canvas_bg.png");
        
        imageBall = Image.createImage("/ball.png");

        

    }

    /**
     * Load about and help text
     * @throws java.io.IOException
     */
    public void loadText() throws IOException
    {
        try
        {
            TxtReader txtReader = new TxtReader();
            textAbout = txtReader.readFile("/about.txt");
            textHelp = txtReader.readFile("/help.txt");           
        }
        catch(IOException io)
        {
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
    public void drawSoftKeys(Graphics g)
    {
        switch(state)
        {
            case Constants.STATE_MAIN_MENU:
            case Constants.STATE_EXIT:            
                g.drawImage(imageOK, 0, DEVICE_HEIGHT - imageOK.getHeight(), Graphics.TOP | Graphics.LEFT);
                g.drawImage(imageCancel, DEVICE_WIDTH - imageCancel.getWidth(), DEVICE_HEIGHT - imageCancel.getHeight(), Graphics.TOP | Graphics.LEFT);
                break;
            case Constants.STATE_OPTIONS:
                g.drawImage(imageOK, 0, DEVICE_HEIGHT - imageOK.getHeight(), Graphics.TOP | Graphics.LEFT);
                break;
            case Constants.STATE_ABOUT:
            case Constants.STATE_HELP:
                g.drawImage(imageCancel, 108, 145, Graphics.TOP | Graphics.LEFT);
                break;

        }
    }
    /**
     * Draw About
     * @param g
     */
    public void drawAbout(Graphics g)
    {
        font[1].write(g, Constants.MAIN_MENU_ABOUT_TITLE, 5,  0,
                DEVICE_WIDTH, font[1].getHeight(), Component.ALIGN_TOP_LEFT);

        g.drawImage(imageOptionsGrid, 0, 25, Graphics.TOP | Graphics.LEFT);
        for(int i = 0; i < textAbout.length; i ++)
        {
            font[0].write(g, textAbout[i], 0,
                    Constants.ABOUT_AND_HELP_TEXT_INITIAL_Y +
                    ((int)(font[0].getHeight() * i*1.5)),
                    DEVICE_WIDTH ,0, Component.ALIGN_TOP_CENTER);
        }
    }
    /**
     * Draw Help
     * @param g
     */
    public void drawHelp(Graphics g)
    {
        font[1].write(g, Constants.MAIN_MENU_HELP_TITLE, 5,  0, 
                DEVICE_WIDTH, font[1].getHeight(), Component.ALIGN_TOP_LEFT);

        if(textHelp.length <= DEVICE_MAX_NUMBER_OF_LINES)
        {
            for(int i = 0; i < textHelp.length; i ++)
            {
                font[0].write(g, textHelp[i], 0,
                    Constants.ABOUT_AND_HELP_TEXT_INITIAL_Y +
                    ((int)(font[0].getHeight() * i*1.5)),
                    DEVICE_WIDTH ,0, Component.ALIGN_TOP_CENTER);
            }
        }
        else
        {
            g.drawImage(imageOptionsGrid, 0, 25, Graphics.TOP | Graphics.LEFT);
            g.drawImage(imageArrowUp, DEVICE_WIDTH -15, 25, Graphics.TOP | Graphics.LEFT);
            g.drawImage(imageArrowDown, DEVICE_WIDTH -15, DEVICE_HEIGHT-25, Graphics.TOP | Graphics.LEFT);
           
            for(int i = firstLineScroll; i < firstLineScroll + DEVICE_MAX_NUMBER_OF_LINES; i ++)
            {
                
                font[0].write(g, textHelp[i], 0,
                    Constants.ABOUT_AND_HELP_TEXT_INITIAL_Y +
                    ((int)(font[0].getHeight() * (i-firstLineScroll)*1.5)),
                    DEVICE_WIDTH ,0, Component.ALIGN_TOP_CENTER);
            }
            
            
        }
    }
    /**
     * Draw Options Menu
     * @param g
     */
    public void drawOptions(Graphics g)
    {
        font[1].write(g, Constants.MAIN_MENU_OPTIONS_TITLE, 5, 0,
                DEVICE_WIDTH, font[1].getHeight(), Component.ALIGN_TOP_LEFT);
         
        switch(optionSelected)
        {
            case Constants.OPTIONS_SOUND_KITS:
                g.drawImage(imageArrowLeft, 40, 70, Graphics.TOP | Graphics.LEFT);
                g.drawImage(imageArrowRight, DEVICE_WIDTH - 5 - imageArrowRight.getWidth(), 70, Graphics.TOP | Graphics.LEFT);
                g.drawImage(imageOptionsBar, 0, 70, Graphics.TOP | Graphics.LEFT);
                break;
            
        }


        
        font[0].write(g, Constants.SOUND_OPTIONS_COMPONENTS_TEXT , 10, 70,
                DEVICE_WIDTH, font[1].getHeight(), Component.ALIGN_TOP_LEFT);

        font[0].write(g, Constants.SOUND_OPTIONS_COMPONENTS[optionsSelectedSoundComponents], 10, 90,
                60, font[1].getHeight(), Component.ALIGN_TOP_LEFT);

   

    }
    /**
     * Draw Exit Menu
     * @param g
     */
    public void drawExit(Graphics g)
    {
        font[1].write(g, Constants.MAIN_MENU_EXIT_TITLE, 5,  0,
                DEVICE_WIDTH, font[1].getHeight(), Component.ALIGN_TOP_LEFT);

        font[0].write(g, Constants.EXIT_TEXT[0].toUpperCase(), 0, 80 ,
                DEVICE_WIDTH ,0, Component.ALIGN_TOP_CENTER);
    }


    /**
     * Draw Main Menu
     * @param g
     */
    public void drawMenu(Graphics g)
    {        
        int cy = 0;

        menuAnimation(g);
        
        for (int i = 0; i < Constants.MAIN_MENU_LENGHT; i++)
        {

            cy = 45 + (i * 22);
            g.setClip(23, cy, 82, 20);

            if (menuIdx == i) 
                g.drawImage(imageMenu, 23, cy - 20, Graphics.TOP | Graphics.LEFT);
            else
                g.drawImage(imageMenu, 23, cy, Graphics.TOP | Graphics.LEFT);
            
            //offset of the label is 6 pixels from the top of the button
            cy += 6;
            //set the clipping rectangle to where the label will be drawn
            g.setClip(23, cy, 82, 8);
            //draw the label so that it is inside the clipping rectangle
            g.drawImage(imageMenu, 23, cy - (40 + (i * 8)), Graphics.TOP | Graphics.LEFT);
        }
    }

    public void drawMetronome(Graphics g)
    {
        g.setColor(0x00000000);
        g.fillRect(0, 0, DEVICE_WIDTH, DEVICE_HEIGHT);
        // background
        g.drawImage(imageBGMetronome, 0, 0, Graphics.TOP | Graphics.LEFT);


        if (MetronomeCanvas.getState() == Constants.STATE_METRONOME_STARTED) {
            taskMetronome(g);
        }

        if (MetronomeCanvas.getState() == Constants.STATE_OPTIONS) {
            drawOptions(g);
        } else {
            // Measure
            fontMetronome[2].write(g, metronome.getNumerator() + "/" + metronome.getDenominator(), 30, 92, DEVICE_WIDTH, 0, Component.ALIGN_TOP_CENTER);
            // BPM
            fontMetronome[2].write(g, String.valueOf(metronome.getBeatsPerMinute()), 10, 110, DEVICE_WIDTH, 0, Component.ALIGN_TOP_CENTER);

            g.drawImage(imageBall, Constants.BALL_BPM_INITIAL_X + (int) (metronome.getBeatsPerMinute() * 0.3), 129, Graphics.TOP | Graphics.LEFT);
        }
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

        if (!animationFinish1)
        {
            menuAnimationX += 1;
            if (menuAnimationX > 100) 
                animationFinish1 = true;
            
        } else if (animationFinish1 && !animationFinish2) {
            menuAnimationX -= 5;
            if (menuAnimationX <= 5) 
                animationFinish2 = true;
            
        }
        g.drawImage(imageBGTitle, menuAnimationX, menuAnimationY, Graphics.TOP | Graphics.LEFT);

    }
    public void resetAnimation()
    {
        animationFinish2  = animationFinish1 = false;
        menuAnimationX = Constants.MENU_ANIMATION_X_INITIAL;
    }

    

    //----------------------------------------------------------------
    // EVENTS
    //----------------------------------------------------------------
    protected void keyPressed(int keyCode)
    {
       processEvents(keyCode);

    }
    protected void keyRepeated(int keyCode)
    {
        processEvents(keyCode);
    }


    public void processEvents(int keyCode)
    {
        switch(state)
        {
            case Constants.STATE_MAIN_MENU:
                switch(keyCode)
                {
                    case Canvas.KEY_NUM5:
                    case DEVICE_BUTTON_LSK:
                    case DEVICE_BUTTON_FIRE:
                    {
                        switch(menuIdx)
                        {
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
                        if(menuIdx == 0)
                            menuIdx = 3;
                        else if(menuIdx - 1 >= 0)
                            menuIdx--;
                        break;
                    case DEVICE_BUTTON_DOWN:
                        if(menuIdx + 1 < Constants.MAIN_MENU_LENGHT)
                            menuIdx++;
                        else
                            menuIdx = 0;

                        break;
                }
                break;
            case Constants.STATE_HELP:
                switch(keyCode)
                {
                    case DEVICE_BUTTON_RSK:
                    case DEVICE_BUTTON_CLEAR:
                        state = Constants.STATE_MAIN_MENU;
                        resetAnimation();
                        break;
                    case DEVICE_BUTTON_UP:
                        if(firstLineScroll > 0)
                            firstLineScroll--;
                        break;
                    case DEVICE_BUTTON_DOWN:

                        if(firstLineScroll < textHelp.length - DEVICE_MAX_NUMBER_OF_LINES)
                            this.firstLineScroll++;
                        break;


                }
                break;
            case Constants.STATE_ABOUT:
                switch(keyCode)
                {
                    case DEVICE_BUTTON_RSK:
                    case DEVICE_BUTTON_CLEAR:
                        state = Constants.STATE_MAIN_MENU;
                        resetAnimation();
                        break;
                }
                break;
            case Constants.STATE_OPTIONS:
                switch(keyCode)
                {
                    case DEVICE_BUTTON_LSK:
                    case DEVICE_BUTTON_CLEAR:
                        state = Constants.STATE_MAIN_MENU;
                        resetAnimation();             
                        break;
                    case DEVICE_BUTTON_UP:
                    case Canvas.KEY_NUM2:
                        if(optionSelected > 0)
                            optionSelected--;
                        break;
                    case DEVICE_BUTTON_DOWN:
                    case Canvas.KEY_NUM8:
                        if(optionSelected + 1 < Constants.OPTIONS_NUMBER)
                        {
                            optionSelected++;
                        }
                        break;
                    case DEVICE_BUTTON_RIGHT:
                    case Canvas.KEY_NUM6:
                        switch(optionSelected)
                        {
                            case Constants.OPTIONS_SOUND_KITS:
                                if (optionsSelectedSoundComponents + 1 < Constants.SOUND_OPTIONS_COMPONENTS.length) {
                                    optionsSelectedSoundComponents++;
                                } else if (optionsSelectedSoundComponents + 1 == Constants.SOUND_OPTIONS_COMPONENTS.length) {
                                    optionsSelectedSoundComponents = 0;
                                }
                                break;
                        }
                        break;
                    case DEVICE_BUTTON_LEFT:
                    case Canvas.KEY_NUM4:
                        switch(optionSelected)
                        {

                            case Constants.OPTIONS_SOUND_KITS:
                                if (optionsSelectedSoundComponents > 0) {
                                    optionsSelectedSoundComponents--;
                                } else if (optionsSelectedSoundComponents - 1 < 0) {
                                    optionsSelectedSoundComponents = Constants.SOUND_OPTIONS_COMPONENTS.length - 1;
                                }
                                break;
                        }
                        break;


                }
                break;
            case Constants.STATE_EXIT:
                switch(keyCode)
                {
                    case DEVICE_BUTTON_RSK:
                    case DEVICE_BUTTON_CLEAR:
                        state = Constants.STATE_MAIN_MENU;
                        resetAnimation();
                        break;
                    case Canvas.KEY_NUM5:
                    case DEVICE_BUTTON_FIRE:
                    case DEVICE_BUTTON_LSK:
                        dmartinsMetronomeMIDlet.getInstance().kill();
                        break;

                }
                break;
        
        case Constants.STATE_METRONOME_STARTED:
            case Constants.STATE_METRONOME_STOPPED:
                switch (keyCode) {
                    case DEVICE_BUTTON_RIGHT:
                        stop();
                        if(metronome.getBeatsPerMinute() < Metronome.BPM_MAX)
                        metronome.setBeatsPerMinute(metronome.getBeatsPerMinute() + 1);
                        break;
                    case DEVICE_BUTTON_LEFT:
                        stop();
                        if(metronome.getBeatsPerMinute() > Metronome.BPM_MIN)
                        metronome.setBeatsPerMinute(metronome.getBeatsPerMinute() - 1);
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
                        MetronomeCanvas.setState(Constants.STATE_OPTIONS);
                        break;
                    case DEVICE_BUTTON_RSK:
                        MetronomeCanvas.setState(Constants.STATE_MAIN_MENU);

                        System.out.println(MetronomeCanvas.getState());
                        Display.getDisplay(dmartinsMetronomeMIDlet.getInstance()).setCurrent(new MetronomeCanvas());
                        MetronomeCanvas.setState(Constants.STATE_MAIN_MENU);
                        break;

                }
                break;
            
        }
    }

      public void run() {
        while (getState() != Constants.STATE_KILL) {
            if (getState() < Constants.STATE_METRONOME_STARTED) {
                repaint();

                try {
                    Thread.sleep(16);
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

    public void paint(Graphics g)
    {
        g.setColor(0x00000000);
        g.fillRect(0, 0, DEVICE_WIDTH, DEVICE_HEIGHT);

        // Draw Background Image
        g.drawImage(imageBGMainMenu, (DEVICE_WIDTH - imageBGMainMenu.getWidth()) / 2, (DEVICE_HEIGHT - imageBGMainMenu.getHeight()) / 2, 20);
        drawSoftKeys(g);

        switch(state)
        {
            case Constants.STATE_MAIN_MENU:
                drawMenu(g);
                break;
            case Constants.STATE_OPTIONS:
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
    }
    //----------------------------------------------------------------------
    // GETTERS AND SETTERS
    //----------------------------------------------------------------------
    public static int getState()
    {
        return state;
    }
    public static void setState(int state)
    {
        MetronomeCanvas.state = state;
    }
    public static int getSoundType()
    {
        return optionsSelectedSoundType;
    }
    public static void setSoundType(int type)
    {
        MetronomeCanvas.optionsSelectedSoundType = type;
    }
    public static int getSoundComponents()
    {
        return optionsSelectedSoundComponents;
    }
    public static void setSoundComponents(int components)
    {
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

    public void taskMetronome(Graphics g) {
        if (count == 1)
        {
            fontMetronome[1].write(g, String.valueOf(count++), 0, 35, DEVICE_WIDTH, 0, Component.ALIGN_TOP_CENTER);

            if(MetronomeCanvas.getSoundComponents() == 0)
                metronome.playBassDrum();
            else if(MetronomeCanvas.getSoundComponents() == 1)
                metronome.playBassDrumAndCrash();
            else
                metronome.playToms();

        }
        else if (count == metronome.getNumerator() || count > metronome.getNumerator())
        {
            fontMetronome[0].write(g, String.valueOf(count++), 0, 35, DEVICE_WIDTH, 0, Component.ALIGN_TOP_CENTER);
            if(MetronomeCanvas.getSoundComponents() == 2)
                metronome.playToms();
            else
                metronome.playSnare();

            count = 1;
        }
        else
        {
            fontMetronome[0].write(g, String.valueOf(count++), 0, 35, DEVICE_WIDTH, 0, Component.ALIGN_TOP_CENTER);

            if(MetronomeCanvas.getSoundComponents() == 0)
                metronome.playBassDrum();
            else if(MetronomeCanvas.getSoundComponents() == 1)
            {
                metronome.playBassDrumAndHiHat();
            }
            else
            {
                metronome.playToms();
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
                for (i = 0; i < (DEVICE_WIDTH * DEVICE_HEIGHT); i++) {
                    r = (fadeRGB[i] & 255);
                    g = ((fadeRGB[i] >> 8) & 255);
                    b = ((fadeRGB[i] >> 16) & 255);
                    fadeRGB[i] = ((fadeCount << 24) | (b << 16) | (g << 8) | r);
                }
                fadeCount = fadeCount < 255 - 12 ? fadeCount + 12 : 255;
                break;
            case Constants.FADE_IN:
                for (i = 0; i < (DEVICE_WIDTH * DEVICE_HEIGHT); i++) {
                    r = (fadeRGB[i] & 255);
                    g = ((fadeRGB[i] >> 8) & 255);
                    b = ((fadeRGB[i] >> 16) & 255);
                    fadeRGB[i] = ((fadeCount << 24) | (b << 16) | (g << 8) | r);
                }
                fadeCount = fadeCount > 12 ? fadeCount - 12 : 0;
                break;

        }
    }

}
