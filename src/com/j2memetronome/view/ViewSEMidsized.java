/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.j2memetronome.view;

import com.j2memetronome.appstate.ApplicationState;
import com.j2memetronome.device.SonyEricssonMidsized;
import com.j2memetronome.i18n.Language;
import com.j2memetronome.resource.ResourceLoader;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import mwt.Component;
import mwt.Font;

/**
 *
 * @author dmartins
 */
public class ViewSEMidsized implements View, SonyEricssonMidsized {


    private Font arial;
    private Font contour;
    private Font metronomeRed;
    private Font metronomeGreen;
    private Font metronome;



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
        contour.write(g, titleAbout, 5, 0,WIDTH, contour.getHeight(), Component.ALIGN_TOP_LEFT);

        // Grid
        g.drawImage(optionsGrid, 0, WIDTH/6, Graphics.TOP | Graphics.LEFT);

        // show about text
        if (textAbout.length <= MAX_NUMBER_OF_LINES)
            for (int i = 0; i < textAbout.length; i++)
                arial.write(g, textAbout[i], 0, ABOUT_AND_HELP_TEXT_INITIAL_Y
                        + ((int) (arial.getHeight() * i * 1.5)), WIDTH, 0, Component.ALIGN_TOP_CENTER);
        else
        {

            g.drawImage(arrowUp, WIDTH - 15, HEIGHT/6, Graphics.TOP | Graphics.LEFT);
            g.drawImage(arrowDown, WIDTH - 15, HEIGHT - HEIGHT/6, Graphics.TOP | Graphics.LEFT);

            for (int i = firstLineScroll; i < firstLineScroll + MAX_NUMBER_OF_LINES && i < textAbout.length; i++)
            {
                arial.write(g, textAbout[i], 0, ABOUT_AND_HELP_TEXT_INITIAL_Y
                        + ((int) (arial.getHeight() * (i - firstLineScroll) * 1.5)),
                        WIDTH, 0, Component.ALIGN_TOP_CENTER);
            }
        }
    }

    public void drawHelp(Graphics g, Image bgMenu, Image optionsGrid, Image arrowUp, Image arrowDown, String titleHelp, String textHelp[], int firstLineScroll) {
         g.drawImage(bgMenu, (WIDTH - bgMenu.getWidth()) / 2, (HEIGHT - bgMenu.getHeight()) / 2, 20);

        contour.write(g, titleHelp, 5, 0,
                WIDTH, contour.getHeight(), Component.ALIGN_TOP_LEFT);

        g.drawImage(optionsGrid, 0, WIDTH/6, Graphics.TOP | Graphics.LEFT);


        if (textHelp.length <= MAX_NUMBER_OF_LINES) {
            for (int i = 0; i < textHelp.length; i++) {
                arial.write(g, textHelp[i], 0,
                        ABOUT_AND_HELP_TEXT_INITIAL_Y
                        + ((int) (arial.getHeight() * i * 1.5)),
                        WIDTH, 0, Component.ALIGN_TOP_CENTER);
            }
        } else {

            g.drawImage(arrowUp, WIDTH - 15, HEIGHT/6, Graphics.TOP | Graphics.LEFT);
            g.drawImage(arrowDown, WIDTH - 15, HEIGHT - HEIGHT/6, Graphics.TOP | Graphics.LEFT);

            for (int i = firstLineScroll; i < firstLineScroll + MAX_NUMBER_OF_LINES && i < textHelp.length; i++) {

                arial.write(g, textHelp[i], 0,
                        ABOUT_AND_HELP_TEXT_INITIAL_Y
                        + ((int) (arial.getHeight() * (i - firstLineScroll) * 1.5)),
                        WIDTH, 0, Component.ALIGN_TOP_CENTER);
            }


        }
    }

    public void drawOptions(Graphics g, Image bgMenu, Image optionsBar, Image arrowLeft, Image arrowRight, String titleOptions, String textOptions[], int selectedSoundComponent)
    {

        g.drawImage(bgMenu, (WIDTH - bgMenu.getWidth()) / 2, (HEIGHT - bgMenu.getHeight()) / 2, 20);

        contour.write(g, titleOptions, 5, 0, WIDTH, contour.getHeight(), Component.ALIGN_TOP_LEFT);

        g.drawImage(arrowLeft, 5, HEIGHT/2 -10, Graphics.TOP | Graphics.LEFT);
        g.drawImage(arrowRight, WIDTH - 5 - arrowRight.getWidth(), HEIGHT/2 -10, Graphics.TOP | Graphics.LEFT);
        g.drawImage(optionsBar, 0, HEIGHT/2 -20, Graphics.TOP | Graphics.LEFT);

        arial.write(g, textOptions[ResourceLoader.STRING_KITS], 0, HEIGHT/2 -10,
                WIDTH, arial.getHeight(), Component.ALIGN_TOP_CENTER);

        arial.write(g, textOptions[ResourceLoader.STRING_BASS_DRUM_AND_SNARE + selectedSoundComponent], 0, HEIGHT/2 + 10,
                WIDTH, arial.getHeight(), Component.ALIGN_TOP_CENTER);
    }

    public void drawExit(Graphics g, Image bgMenu, String titleExit, String textExit) {
        g.drawImage(bgMenu, (WIDTH - bgMenu.getWidth()) / 2, (HEIGHT - bgMenu.getHeight()) / 2, 20);

        contour.write(g, titleExit, 5, 0,
                WIDTH, arial.getHeight(), Component.ALIGN_TOP_LEFT);

        arial.write(g, textExit, 0, HEIGHT/2,
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
        g.fillRect(10, 75, WIDTH - 20, 20);
        g.setColor(0xFFFFFF);
        g.drawRect(10, 75, WIDTH - 20, 20);

        g.setColor(0x111111);
        g.fillRect(10, 95, WIDTH - 20, 20);
        g.setColor(0xFFFFFF);
        g.drawRect(10, 95, WIDTH - 20, 20);

        if (Language.current()  == Language.ENGLISH) {
            g.setColor(0x555555);
            g.fillRect(10, 75, WIDTH - 20, 10);
            g.setColor(0x777777);
            g.fillRect(10, 85, WIDTH - 20, 10);
            g.setColor(0xFFFFFF);
            g.drawRect(10, 75, WIDTH - 20, 20);
            contour.write(g, "CHOOSE", 0, 5, WIDTH, contour.getHeight(), Component.ALIGN_TOP_CENTER);
            contour.write(g, "YOUR", 0, 25, WIDTH, contour.getHeight(), Component.ALIGN_TOP_CENTER);
            contour.write(g, "LANGUAGE", 0, 45, WIDTH, contour.getHeight(), Component.ALIGN_TOP_CENTER);
        } else if (Language.current()  == Language.PORTUGUESE) {
            g.setColor(0x555555);
            g.fillRect(10, 95, WIDTH - 20, 10);
            g.setColor(0x777777);
            g.fillRect(10, 105, WIDTH - 20, 10);
            g.setColor(0xFFFFFF);
            g.drawRect(10, 95, WIDTH - 20, 20);
            contour.write(g, "ESCOLHA", 0, 5, WIDTH, contour.getHeight(), Component.ALIGN_TOP_CENTER);
            contour.write(g, "SEU", 0, 25, WIDTH, contour.getHeight(), Component.ALIGN_TOP_CENTER);
            contour.write(g, "IDIOMA", 0, 45, WIDTH, contour.getHeight(), Component.ALIGN_TOP_CENTER);
        }


        arial.write(g, "ENGLISH", 0, 80, WIDTH, arial.getHeight(), Component.ALIGN_TOP_CENTER);
        arial.write(g, "PORTUGUÊS", 0, 100, WIDTH, arial.getHeight(), Component.ALIGN_TOP_CENTER);

    }

    public void drawMetronome(Graphics g, Image bgMetronome, Image ball,
            int numerator, int denominator, int bpm, int count, boolean isFirst, boolean isStarted) {

        g.setColor(0x00000000);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // background
        g.drawImage(bgMetronome, 0, 0, Graphics.TOP | Graphics.LEFT);


        if (isStarted)
            if(isFirst)
                metronomeRed.write(g, String.valueOf(count), 0, HEIGHT/5, WIDTH, 0, Component.ALIGN_TOP_CENTER);
            else
                metronomeGreen.write(g, String.valueOf(count), 0, HEIGHT/5, WIDTH, 0, Component.ALIGN_TOP_CENTER);



        // Measure
        metronome.write(g, numerator + "/" + denominator,
                80, 93, WIDTH, 0, Component.ALIGN_TOP_LEFT);
        // BPM
        metronome.write(g, String.valueOf(bpm),
                80, 110, WIDTH, 0, Component.ALIGN_TOP_LEFT);

       g.drawImage(ball, BALL_BPM_INITIAL_X + (int) (bpm * 0.3), 130, Graphics.TOP | Graphics.LEFT);


    }


    public void drawMenu(Graphics g, Image bgMainMenu, Image bgTitle, Image menu, int index, int animX, int animY)
    {
        int cy = 0;
        g.drawImage(bgMainMenu, (WIDTH - bgMainMenu.getWidth()) / 2, (HEIGHT - bgMainMenu.getHeight()) / 2, 20);


        g.drawImage(bgTitle, animX, animY, Graphics.TOP | Graphics.LEFT);

        for (int i = 0; i < MAIN_MENU_LENGTH; i++)
        {
           cy = 45 + (i * 22);

             g.setClip(23, cy, 82, 20);


             if (index == i) {
                 g.drawImage(menu, 23, cy - 20, Graphics.TOP | Graphics.LEFT);
             } else {
                 g.drawImage(menu, 23, cy, Graphics.TOP | Graphics.LEFT);
             }

             //offset of the label is 6 pixels from the top of the button
             cy += 6;
             //set the clipping rectangle to where the label will be drawn
             g.setClip(23, cy, 82, 10);
             //draw the label so that it is inside the clipping rectangle
             g.drawImage(menu, 23, cy - (40 + (i * 10)), Graphics.TOP | Graphics.LEFT);


        }
    }

    public void setArial(Font arial) {
        this.arial = arial;
    }

    public void setContour(Font contour) {
        this.contour = contour;
    }

    public Font getMetronome() {
        return metronome;
    }

    public void setMetronome(Font metronome) {
        this.metronome = metronome;
    }

    public Font getMetronomeGreen() {
        return metronomeGreen;
    }

    public void setMetronomeGreen(Font metronomeGreen) {
        this.metronomeGreen = metronomeGreen;
    }

    public Font getMetronomeRed() {
        return metronomeRed;
    }

    public void setMetronomeRed(Font metronomeRed) {
        this.metronomeRed = metronomeRed;
    }

    public int getWidth()
    {
        return WIDTH;
    }
    public int getHeight()
    {
        return HEIGHT;
    }
    public int maxLines()
    {
        return MAX_NUMBER_OF_LINES;
    }

    public int supportedSounds()
    {
        return SUPPORTED_SOUNDS;
    }




}