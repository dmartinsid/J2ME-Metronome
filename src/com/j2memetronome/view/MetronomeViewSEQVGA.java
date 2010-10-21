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

    public void drawHelp(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void drawOptions(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void drawExit(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void drawSplash(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void drawChooseLanguage(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
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
