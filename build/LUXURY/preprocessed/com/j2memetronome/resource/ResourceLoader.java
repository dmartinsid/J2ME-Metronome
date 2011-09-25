/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2memetronome.resource;


import com.j2memetronome.font.FontConstants;
import com.j2memetronome.i18n.Language;
import com.j2memetronome.text.TxtReader;
import java.io.IOException;
import javax.microedition.lcdui.Image;
import mwt.Font;

/**
 *
 * @author dmartins
 */
public class ResourceLoader {

    // String IDs
    public static int STRING_ABOUT = 0;
    public static int STRING_HELP = 1;
    public static int STRING_OPTIONS = 2;
    public static int STRING_EXIT = 3;
    public static int STRING_BASS_DRUM_AND_SNARE = 4;
    public static int STRING_BASS_DRUM_SNARE_HH = 5;
    public static int STRING_TOMS = 6;
    public static int STRING_CLICK_AND_BELL = 7;
    public static int STRING_KITS = 8;
    public static int STRING_EXIT_TEXT = 9;

    public static final String ENGLISH_RES = "en";
    public static final String PORTUGUESE_RES = "pt";
    
    // Images Menu
    private Image menu,  bgMainMenu,  bgTitle,  oK,  cancel,  arrowUp,  arrowDown,  arrowLeft,  arrowRight,  optionsGrid,  optionsBar;

    // Images Metronome
    private Image bgMetronome,  ball;

    // Image Splash
    private Image splash;
    private Font arial;
    private Font contour;
    private Font metronomeRed;
    private Font metronomeGreen;
    private Font metronomeFont;
    private String textHelp[],  textAbout[],  textCommons[];

    public void loadFonts() throws IOException {
        arial = new Font(Image.createImage("/arial_12.png"), FontConstants.FONT_ARIAL_CHARSET,
                FontConstants.FONT_ARIAL_WIDTHS, 0);

        contour = new Font(Image.createImage("/out.png"), FontConstants.FONT_OUT_CHARSET,
                FontConstants.FONT_OUT_WIDTHS, 0);

        metronomeFont = new Font(Image.createImage("/tw_font.png"), FontConstants.FONT_TW_CHARSET,
                FontConstants.FONT_TW_WIDTHS, 0);

        metronomeGreen = new Font(Image.createImage("/numbers_green.png"), FontConstants.FONT_GREEN_NUMBERS_CHARSET,
                FontConstants.FONT_GREEN_NUMBERS_WIDTHS, 0);

        metronomeRed = new Font(Image.createImage("/numbers_red.png"), FontConstants.FONT_RED_NUMBERS_CHARSET,
                FontConstants.FONT_RED_NUMBERS_WIDTHS, 0);
    }

    public void loadImages() throws IOException {

        bgMainMenu = Image.createImage("/bg.png");
        bgTitle = Image.createImage("/menu_title.png");
        cancel = Image.createImage("/cancel.png");
        oK = Image.createImage("/ok.png");


        arrowUp = Image.createImage("/arrow_up.png");
        arrowDown = Image.createImage("/arrow_down.png");
        arrowLeft = Image.createImage("/white_arrow_left.png");
        arrowRight = Image.createImage("/white_arrow_right.png");


        optionsGrid = Image.createImage("/optionsGridMainMenu.png");
        optionsBar = Image.createImage("/optionsBar.png");



        ball = Image.createImage("/ball.png");

        splash = Image.createImage("/Splash.png");
    }

    /**
     * Load about and help text
     * @throws java.io.IOException
     */
    public void loadText() throws IOException {
        try {

            TxtReader txtReader = new TxtReader();

            if (Language.current()  == Language.PORTUGUESE) {
                textAbout = txtReader.readFile("/" + PORTUGUESE_RES + "/about.txt");
                textHelp = txtReader.readFile("/" + PORTUGUESE_RES + "/help.txt");
                textCommons = txtReader.readFile("/" + PORTUGUESE_RES + "/common.txt");
            } else {
                textAbout = txtReader.readFile("/" + ENGLISH_RES + "/about.txt");
                textHelp = txtReader.readFile("/" + ENGLISH_RES + "/help.txt");
                textCommons = txtReader.readFile("/" + ENGLISH_RES + "/common.txt");
            }
        } catch (IOException io) {
            io.printStackTrace();
        }

    }

    public void loadImagesLang() throws IOException {

        if (Language.current()  == Language.PORTUGUESE) {
            menu = Image.createImage("/images_multilang/" + PORTUGUESE_RES + "/menuitems.png");
            bgMetronome = Image.createImage("/images_multilang/" + PORTUGUESE_RES + "/metronome_canvas_bg.png");
        } else {
            menu = Image.createImage("/images_multilang/" + ENGLISH_RES + "/menuitems.png");
            bgMetronome = Image.createImage("/images_multilang/" + ENGLISH_RES + "/metronome_canvas_bg.png");
        }

    }

    public Font getArial() {
        return arial;
    }

    public Image getArrowDown() {
        return arrowDown;
    }

    public Image getArrowLeft() {
        return arrowLeft;
    }

    public Image getArrowRight() {
        return arrowRight;
    }

    public Image getArrowUp() {
        return arrowUp;
    }

    public Image getBall() {
        return ball;
    }

    public Image getBgMainMenu() {
        return bgMainMenu;
    }

    public Image getBgMetronome() {
        return bgMetronome;
    }

    public Image getBgTitle() {
        return bgTitle;
    }

    public Image getCancel() {
        return cancel;
    }

    public Font getContour() {
        return contour;
    }

    public Image getMenu() {
        return menu;
    }

    public Font getMetronomeFont() {
        return metronomeFont;
    }

    public Font getMetronomeGreen() {
        return metronomeGreen;
    }

    public Font getMetronomeRed() {
        return metronomeRed;
    }

    public Image getOK() {
        return oK;
    }

    public Image getOptionsBar() {
        return optionsBar;
    }

    public Image getOptionsGrid() {
        return optionsGrid;
    }

    public Image getSplash() {
        return splash;
    }

    public String[] getTextAbout() {
        return textAbout;
    }

    public String[] getTextCommons() {
        return textCommons;
    }

    public String[] getTextHelp() {
        return textHelp;
    }
}
