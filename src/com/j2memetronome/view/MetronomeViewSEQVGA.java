/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.j2memetronome.view;

import com.j2memetronome.Constants;
import com.j2memetronome.appstate.ApplicationState;
import com.j2memetronome.device.SEQVGA;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import mwt.Component;
import mwt.Font;

/**
 *
 * @author Deivid Martins
 */
public class MetronomeViewSEQVGA implements MetronomeView, SEQVGA {


    private Font arial;
    private Font contour;

    public void drawSoftKeys(Graphics g, int state, Image ok, Image cancel)
    {
        switch (state)
        {
            case ApplicationState.MAIN_MENU:
            case ApplicationState.EXIT:
                g.setClip(0, 0, WIDTH, HEIGHT);
                g.drawImage(ok, 0, HEIGHT - ok.getHeight(), Graphics.TOP | Graphics.LEFT);
                g.drawImage(cancel, WIDTH - cancel.getWidth(), HEIGHT - cancel.getHeight(), Graphics.TOP | Graphics.LEFT);
                break;
            case ApplicationState.OPTIONS:
            case ApplicationState.METRONOME_OPTIONS:
                g.drawImage(ok, 0, HEIGHT - ok.getHeight(), Graphics.TOP | Graphics.LEFT);
                break;
            case ApplicationState.ABOUT:
            case ApplicationState.HELP:
                g.drawImage(cancel, WIDTH - cancel.getWidth(), HEIGHT - cancel.getHeight(), Graphics.TOP | Graphics.LEFT);
                break;

        }
    }

    public void drawAbout(Graphics g, Image bgMenu, Image optionsGrid, Image arrowUp, Image arrowDown, String titleAbout, String textAbout[], int firstLineScroll)
    {
        // Background
        g.drawImage(bgMenu, (WIDTH - bgMenu.getWidth()) / 2, (HEIGHT - bgMenu.getHeight()) / 2, 20);

        // Title
        arial.write(g, titleAbout, 5, 0,WIDTH, arial.getHeight(), Component.ALIGN_TOP_LEFT);

        // Grid
        g.drawImage(optionsGrid, 0, WIDTH/9, Graphics.TOP | Graphics.LEFT);

        // show about text
        if (textAbout.length <= MAX_NUMBER_OF_LINES) 
            for (int i = 0; i < textAbout.length; i++) 
                contour.write(g, textAbout[i], 0, Constants.ABOUT_AND_HELP_TEXT_INITIAL_Y
                        + ((int) (contour.getHeight() * i * 1.5)), WIDTH, 0, Component.ALIGN_TOP_CENTER);
        else
        {

            g.drawImage(arrowUp, WIDTH - 15, HEIGHT/6, Graphics.TOP | Graphics.LEFT);
            g.drawImage(arrowDown, WIDTH - 15, HEIGHT - HEIGHT/6, Graphics.TOP | Graphics.LEFT);

            for (int i = firstLineScroll; i < firstLineScroll + Constants.DEVICE_MAX_NUMBER_OF_LINES; i++)
            {
                contour.write(g, textAbout[i], 0, Constants.ABOUT_AND_HELP_TEXT_INITIAL_Y
                        + ((int) (contour.getHeight() * (i - firstLineScroll) * 1.5)),
                        WIDTH, 0, Component.ALIGN_TOP_CENTER);
            }
        }
    }

    public void drawHelp(Graphics g, Image bgMenu, Image optionsGrid, Image arrowUp, Image arrowDown, String titleHelp, String textHelp[], int firstLineScroll) {
         g.drawImage(bgMenu, (WIDTH - bgMenu.getWidth()) / 2, (HEIGHT - bgMenu.getHeight()) / 2, 20);

        arial.write(g, titleHelp, 5, 0,
                WIDTH, arial.getHeight(), Component.ALIGN_TOP_LEFT);

        g.drawImage(optionsGrid, 0, WIDTH/9, Graphics.TOP | Graphics.LEFT);


        if (textHelp.length <= Constants.DEVICE_MAX_NUMBER_OF_LINES) {
            for (int i = 0; i < textHelp.length; i++) {
                contour.write(g, textHelp[i], 0,
                        Constants.ABOUT_AND_HELP_TEXT_INITIAL_Y
                        + ((int) (contour.getHeight() * i * 1.5)),
                        WIDTH, 0, Component.ALIGN_TOP_CENTER);
            }
        } else {

            g.drawImage(arrowUp, WIDTH - 15, HEIGHT/6, Graphics.TOP | Graphics.LEFT);
            g.drawImage(arrowDown, WIDTH - 15, HEIGHT - HEIGHT/6, Graphics.TOP | Graphics.LEFT);

            for (int i = firstLineScroll; i < firstLineScroll + Constants.DEVICE_MAX_NUMBER_OF_LINES; i++) {

                contour.write(g, textHelp[i], 0,
                        Constants.ABOUT_AND_HELP_TEXT_INITIAL_Y
                        + ((int) (contour.getHeight() * (i - firstLineScroll) * 1.5)),
                        WIDTH, 0, Component.ALIGN_TOP_CENTER);
            }


        }
    }

    public void drawOptions(Graphics g, Image bgMenu, Image optionsBar, Image arrowLeft, Image arrowRight, String titleOptions, String textOptions[], int selectedSoundComponent)
    {

        g.drawImage(bgMenu, (WIDTH - bgMenu.getWidth()) / 2, (HEIGHT - bgMenu.getHeight()) / 2, 20);

        arial.write(g, titleOptions, 5, 0, WIDTH, arial.getHeight(), Component.ALIGN_TOP_LEFT);

        g.drawImage(arrowLeft, 5, HEIGHT/2 -10, Graphics.TOP | Graphics.LEFT);
        g.drawImage(arrowRight, WIDTH - 5 - arrowRight.getWidth(), HEIGHT/2 -10, Graphics.TOP | Graphics.LEFT);
        g.drawImage(optionsBar, 0, HEIGHT/2 -20, Graphics.TOP | Graphics.LEFT);

        contour.write(g, textOptions[Constants.STRING_KITS], 0, HEIGHT/2 -10,
                WIDTH, contour.getHeight(), Component.ALIGN_TOP_CENTER);

        contour.write(g, textOptions[Constants.STRING_BASS_DRUM_AND_SNARE + selectedSoundComponent], 0, HEIGHT/2 + 10,
                WIDTH, contour.getHeight(), Component.ALIGN_TOP_CENTER);
    }

    public void drawExit(Graphics g, Image bgMenu, String titleExit, String textExit) {
        g.drawImage(bgMenu, (WIDTH - bgMenu.getWidth()) / 2, (HEIGHT - bgMenu.getHeight()) / 2, 20);

        arial.write(g, titleExit, 5, 0,
                WIDTH, arial.getHeight(), Component.ALIGN_TOP_LEFT);

        contour.write(g, textExit, 0, HEIGHT/2,
                WIDTH, 0, Component.ALIGN_TOP_CENTER);
    }

    public void drawSplash(Graphics g, Image splash)
    {
        g.drawImage(splash, 0, 0, Graphics.TOP | Graphics.LEFT);
    }

    public void drawChooseLanguage(Graphics g, Image bgMenu, int languageId)
    {
        g.drawImage(bgMenu, 0, 0, Graphics.TOP | Graphics.LEFT);
        g.setColor(0x111111);
        g.fillRect(10, 75, WIDTH - 20, 30);
        g.setColor(0xFFFFFF);
        g.drawRect(10, 75, WIDTH - 20, 30);

        g.setColor(0x111111);
        g.fillRect(10, 105, WIDTH - 20, 30);
        g.setColor(0xFFFFFF);
        g.drawRect(10, 105, WIDTH - 20, 30);

        if(languageId == Constants.ENGLISH)
        {
            g.setColor(0x555555);
            g.fillRect(10, 75, WIDTH - 20, 15);
            g.setColor(0x777777);
            g.fillRect(10, 90, WIDTH - 20, 15);
            g.setColor(0xFFFFFF);
            g.drawRect(10, 75, WIDTH - 20, 30);
            arial.write(g, "CHOOSE", 0, 5, WIDTH, arial.getHeight(), Component.ALIGN_TOP_CENTER);
            arial.write(g, "YOUR", 0, 25, WIDTH, arial.getHeight(), Component.ALIGN_TOP_CENTER);
            arial.write(g, "LANGUAGE", 0, 45, WIDTH, arial.getHeight(), Component.ALIGN_TOP_CENTER);
        }
        else if(languageId == Constants.PORTUGUESE)
        {
            g.setColor(0x555555);
            g.fillRect(10, 105, WIDTH - 20, 15);
            g.setColor(0x777777);
            g.fillRect(10, 120, WIDTH - 20, 15);
            g.setColor(0xFFFFFF);
            g.drawRect(10, 105, WIDTH - 20, 30);
            arial.write(g, "ESCOLHA", 0, 5, WIDTH, arial.getHeight(), Component.ALIGN_TOP_CENTER);
            arial.write(g, "SEU", 0, 25, WIDTH, arial.getHeight(), Component.ALIGN_TOP_CENTER);
            arial.write(g, "IDIOMA", 0, 45, WIDTH, arial.getHeight(), Component.ALIGN_TOP_CENTER);
        }


        contour.write(g,"ENGLISH", 0, 75, WIDTH, contour.getHeight(), Component.ALIGN_TOP_CENTER);
        contour.write(g,"PORTUGUÊS", 0, 105, WIDTH, contour.getHeight(), Component.ALIGN_TOP_CENTER);

    }

    public void drawMetronome(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setArial(Font arial) {
        this.arial = arial;
    }

    public void setContour(Font contour) {
        this.contour = contour;
    }

    

}
