package br.com.dmartins.app.metronome;

import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import mwt.Component;
import mwt.Font;

/**
 *
 * @author dmartins
 */
public class MetronomeCanvas extends Canvas implements Runnable, Device
{

    private Image imageBG,  imageBall,  imageOptionsBox,  imageOptionsMetronome,
            imageOptionsBG,  imageOptionsTitle,  imageArrowLeft,  imageArrowRight;
    private Metronome metronome;
    private Thread metronomeCanvasThread;
    private Drummer drummer;

    
    Font[] font = new Font[3];
    int fadeRGB[] = new int[DEVICE_WIDTH * DEVICE_HEIGHT];
    int fadeCount = 255;
    private int count = 1;
    private int optionSelected = 0;

    MetronomeCanvas() {
        MainMenu.setState(Constants.STATE_METRONOME_STOPPED);
        try {
            loadImages();
            loadFonts();
        } catch (Exception e) {
            e.printStackTrace();
        }

        drummer = new Drummer();
        // default is bpm equals 120 and measure 4/4
        metronome = new Metronome();

        setFullScreenMode(true);
        Display.getDisplay(dmartinsMetronomeMIDlet.getInstance()).setCurrent(this);

        // Create Thread and Start
        metronomeCanvasThread = new Thread(this);
        metronomeCanvasThread.start();
    }

    //------------------------------------------------------------
    // LOAD RESOURCES FUNCTIONS
    //------------------------------------------------------------
    /**
     * Load all images used in MetronomeCanvas
     * @throws java.io.IOException
     */
    public void loadImages() throws IOException {
        imageBG = Image.createImage("/metronome_canvas_bg.png");
        imageOptionsBG = Image.createImage("/metronome_canvas_options_bg.png");
        imageBall = Image.createImage("/ball.png");
        imageOptionsBox = Image.createImage("/optionsBox.png");
        imageOptionsMetronome = Image.createImage("/optionsMetronome.png");
        imageOptionsTitle = Image.createImage("/optionsTitleMC.png");
        imageArrowLeft = Image.createImage("/arrow_left.png");
        imageArrowRight = Image.createImage("/arrow_right.png");
    }

    /**
     * Load all fonts used in MetronomeCanvas
     * @throws java.io.IOException
     */
    public void loadFonts() throws IOException {
        final Image images1 = Image.createImage("/numbers_green.png");
        final Image images2 = Image.createImage("/numbers_red.png");
        final Image images3 = Image.createImage("/tw_font.png");

        font[0] = new Font(images1, FontConstants.FONT_GREEN_NUMBERS_CHARSET, FontConstants.FONT_GREEN_NUMBERS_WIDTHS, 0);
        font[1] = new Font(images2, FontConstants.FONT_RED_NUMBERS_CHARSET, FontConstants.FONT_RED_NUMBERS_WIDTHS, 0);
        font[2] = new Font(images3, FontConstants.FONT_TW_CHARSET, FontConstants.FONT_TW_WIDTHS, 0);

    }

    /**
     * start metronome
     */
    public void start() {
        MainMenu.setState(Constants.STATE_METRONOME_STARTED);
    }

    /**
     * Stop Metronome
     */
    public void stop() {
        count = 1;
        MainMenu.setState(Constants.STATE_METRONOME_STOPPED);
    }

    public void taskMetronome(Graphics g) {
        if (count == 1)
        {
            font[1].write(g, String.valueOf(count++), 0, 35, DEVICE_WIDTH, 0, Component.ALIGN_TOP_CENTER);

            if(MainMenu.getSoundComponents() == 0)
                drummer.playDrum(Drummer.ACOUSTIC_BASS_DRUM);
            else if(MainMenu.getSoundComponents() == 1)
            {
                drummer.playDrum(Drummer.ACOUSTIC_BASS_DRUM);
                drummer.playDrum(Drummer.CRASH_CYMBAL_1);
            }
            else
            {
                drummer.playDrum(Drummer.HI_MID_TOM);
                drummer.playDrum(Drummer.LOW_MID_TOM);
            }


        } else if (count == metronome.getNumerator() || count > metronome.getNumerator())
        {
            font[0].write(g, String.valueOf(count++), 0, 35, DEVICE_WIDTH, 0, Component.ALIGN_TOP_CENTER);
            if(MainMenu.getSoundComponents() == 2)
            {
                drummer.playDrum(Drummer.LOW_TOM);
                drummer.playDrum(Drummer.HIGH_FLOOR_TOM);
            }
            else
                drummer.playDrum(Drummer.ACOUSTIC_SNARE);
            count = 1;
        } else {
            font[0].write(g, String.valueOf(count++), 0, 35, DEVICE_WIDTH, 0, Component.ALIGN_TOP_CENTER);
            if(MainMenu.getSoundComponents() == 0)
                drummer.playDrum(Drummer.ACOUSTIC_BASS_DRUM);
            else if(MainMenu.getSoundComponents() == 1)
            {
                drummer.playDrum(Drummer.ACOUSTIC_BASS_DRUM);
                drummer.playDrum(Drummer.CLOSED_HI_HAT);
            }
            else
            {
                drummer.playDrum(Drummer.LOW_FLOOR_TOM);
                drummer.playDrum(Drummer.HIGH_FLOOR_TOM);
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

    public void drawOptions(Graphics graphics) {

        if (imageOptionsMetronome != null && imageOptionsBox != null && imageOptionsMetronome != null) {
            graphics.drawImage(imageOptionsBG, 0, 0, Graphics.TOP | Graphics.LEFT);
            graphics.drawImage(imageOptionsMetronome, (DEVICE_WIDTH - imageOptionsMetronome.getWidth()) / 2,
                    18, Graphics.TOP | Graphics.LEFT);
            graphics.drawImage(imageOptionsTitle, (DEVICE_WIDTH - imageOptionsTitle.getWidth()) / 2, 5, Graphics.TOP | Graphics.LEFT);

            graphics.drawImage(imageOptionsBox, 5, 30, Graphics.TOP | Graphics.LEFT);

      
            font[2].write(graphics, Constants.SOUND_OPTIONS_COMPONENTS_TEXT.replace(':', ' '), 0, 75, DEVICE_WIDTH, 0, Component.ALIGN_TOP_CENTER);
            font[2].write(graphics, Constants.SOUND_OPTIONS_COMPONENTS[MainMenu.getSoundComponents()], 0, 145, DEVICE_WIDTH, 0, Component.ALIGN_TOP_CENTER);



            if (optionSelected == 0) {
                graphics.drawImage(imageArrowLeft, 10, 55, Graphics.TOP | Graphics.LEFT);
                graphics.drawImage(imageArrowRight, DEVICE_WIDTH - 10 - 8, 55, Graphics.TOP | Graphics.LEFT);
            } else if (optionSelected == 1) {
                graphics.drawImage(imageArrowLeft, 10, 85, Graphics.TOP | Graphics.LEFT);
                graphics.drawImage(imageArrowRight, DEVICE_WIDTH - 10 - 8, 85, Graphics.TOP | Graphics.LEFT);


            }


        }


        fade(Constants.FADE_IN);

        graphics.drawRGB(fadeRGB,
                0,
                DEVICE_WIDTH,
                0,
                0,
                DEVICE_WIDTH,
                DEVICE_HEIGHT,
                true);
    }

    protected void paint(Graphics g) {

        g.setColor(0x00000000);
        g.fillRect(0, 0, DEVICE_WIDTH, DEVICE_HEIGHT);
        // background
        g.drawImage(imageBG, 0, 0, Graphics.TOP | Graphics.LEFT);


        if (MainMenu.getState() == Constants.STATE_METRONOME_STARTED) {
            taskMetronome(g);
        }

        if (MainMenu.getState() == Constants.STATE_METRONOME_OPTIONS) {
            drawOptions(g);
        } else {
            // Measure
            font[2].write(g, metronome.getNumerator() + "/" + metronome.getDenominator(), 30, 92, DEVICE_WIDTH, 0, Component.ALIGN_TOP_CENTER);
            // BPM
            font[2].write(g, String.valueOf(metronome.getBeatsPerMinute()), 10, 110, DEVICE_WIDTH, 0, Component.ALIGN_TOP_CENTER);

            g.drawImage(imageBall, Constants.BALL_BPM_INITIAL_X + (int) (metronome.getBeatsPerMinute() * 0.3), 129, Graphics.TOP | Graphics.LEFT);
        }
    }

    protected void keyPressed(int keyCode) {
        processEvents(keyCode);

    }

    public void keyRepeated(int keyCode) {
        processEvents(keyCode);

    }

    public void processEvents(int keyCode) {
        switch (MainMenu.getState()) {
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
                        if (MainMenu.getState() == Constants.STATE_METRONOME_STARTED) {
                            stop();
                        } else if (MainMenu.getState() == Constants.STATE_METRONOME_STOPPED) {
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
                        MainMenu.setState(Constants.STATE_METRONOME_OPTIONS);
                        break;
                    case DEVICE_BUTTON_RSK:
                        MainMenu.setState(Constants.STATE_MAIN_MENU);

                        System.out.println(MainMenu.getState());
                        Display.getDisplay(dmartinsMetronomeMIDlet.getInstance()).setCurrent(new MainMenu());
                        MainMenu.setState(Constants.STATE_MAIN_MENU);
                        break;

                }
                break;
            case Constants.STATE_METRONOME_OPTIONS:
                switch (keyCode) {
                    case DEVICE_BUTTON_RSK:
                        MainMenu.setState(Constants.STATE_METRONOME_STOPPED);
                        fadeCount = 255;
                        break;
                    case DEVICE_BUTTON_UP:
                    case Canvas.KEY_NUM2:
                        if (optionSelected > 0) {
                            optionSelected--;
                        }
                        break;
                    case DEVICE_BUTTON_DOWN:
                    case Canvas.KEY_NUM8:
                        if (optionSelected + 1 < Constants.OPTIONS_NUMBER) {
                            optionSelected++;
                        }
                        break;
                    case DEVICE_BUTTON_RIGHT:
                    case Canvas.KEY_NUM6:
                        switch (optionSelected) {

                            case Constants.OPTIONS_SOUND_KITS:
                                if (MainMenu.getSoundComponents() + 1 < Constants.SOUND_OPTIONS_COMPONENTS.length) {
                                    MainMenu.setSoundComponents(MainMenu.getSoundComponents() + 1);
                                } else if (MainMenu.getSoundComponents() + 1 == Constants.SOUND_OPTIONS_COMPONENTS.length) {
                                    MainMenu.setSoundComponents(0);
                                }
                                break;
                        }
                        break;

                }
                break;



        }
    }

    public void run() {
        while (MainMenu.getState() >= Constants.STATE_METRONOME_STARTED) {
            try {
                repaint();
                if (MainMenu.getState() != Constants.STATE_METRONOME_STARTED) {
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
