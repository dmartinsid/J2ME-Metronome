/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2memetronome.resource;

import com.j2memetronome.Constants;
import com.j2memetronome.font.FontConstants;
import com.j2memetronome.text.TxtReader;
import java.io.IOException;
import javax.microedition.lcdui.Image;
import mwt.Font;

/**
 *
 * @author dmartins
 */
public class ResourceLoader {

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
    public void loadText(int langId) throws IOException {
        try {

            TxtReader txtReader = new TxtReader();

            if (langId == Constants.PORTUGUESE) {
                textAbout = txtReader.readFile("/" + Constants.PORTUGUESE_RES + "/about.txt");
                textHelp = txtReader.readFile("/" + Constants.PORTUGUESE_RES + "/help.txt");
                textCommons = txtReader.readFile("/" + Constants.PORTUGUESE_RES + "/common.txt");
            } else {
                textAbout = txtReader.readFile("/" + Constants.ENGLISH_RES + "/about.txt");
                textHelp = txtReader.readFile("/" + Constants.ENGLISH_RES + "/help.txt");
                textCommons = txtReader.readFile("/" + Constants.ENGLISH_RES + "/common.txt");
            }
        } catch (IOException io) {
            io.printStackTrace();
        }

    }

    public void loadImagesLang(int langId) throws IOException {

        if (langId == Constants.PORTUGUESE) {
            menu = Image.createImage("/images_multilang/" + Constants.PORTUGUESE_RES + "/menuitems.png");
            bgMetronome = Image.createImage("/images_multilang/" + Constants.PORTUGUESE_RES + "/metronome_canvas_bg.png");
        } else {
            menu = Image.createImage("/images_multilang/" + Constants.ENGLISH_RES + "/menuitems.png");
            bgMetronome = Image.createImage("/images_multilang/" + Constants.ENGLISH_RES + "/metronome_canvas_bg.png");
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
