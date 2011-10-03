/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.j2memetronome.view;

import com.j2memetronome.appstate.ApplicationState;
import com.j2memetronome.dao.FontDAO;
import com.j2memetronome.dao.ImageDAO;
import com.j2memetronome.dao.ImageDAOFileSystem;
import com.j2memetronome.dao.TextDAO;
import com.j2memetronome.device.SonyEricssonLuxury;
import com.j2memetronome.i18n.Language;
import com.j2memetronome.resource.ResourceLoader;
import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import mwt.Component;
import mwt.Font;
import org.eclipse.swt.graphics.Color;
/**
 *
 * @author dmartins
 */
public class ViewSELuxury implements View, SonyEricssonLuxury {


    private Font arial;
    private Font contour;
    private Font metronomeRed;
    private Font metronomeGreen;
    private Font metronome;



    public void drawSoftKeys(Graphics g, int state, Image ok, Image cancel)
    {
      SoftkeyPainter softkeyPainter = new SoftkeyPainter();
        ImageDAO imageDAO = new ImageDAOFileSystem();

        try{


            // TODO refactoring in future, inject softtype, and call only a softkeyPainter.paint
            switch (state) {
                case ApplicationState.MAIN_MENU:
                case ApplicationState.EXIT:
                    softkeyPainter.paint(g, imageDAO, this, SoftKeyType.BOTH);

                    break;
                case ApplicationState.OPTIONS:
                case ApplicationState.METRONOME_OPTIONS:
                    softkeyPainter.paint(g, imageDAO, this, SoftKeyType.LEFT);
                    break;
                case ApplicationState.ABOUT:
                case ApplicationState.HELP:
                    softkeyPainter.paint(g, imageDAO, this, SoftKeyType.RIGHT);
                    break;

            }
        }
        catch(IOException iOException)
        {
            iOException.printStackTrace();
        }

    }

    public void drawAbout(Graphics g, Image bgMenu, Image optionsGrid, Image arrowUp, Image arrowDown, String titleAbout, String textAbout[], int firstLineScroll)
    {
        // Background
        g.drawImage(bgMenu, (WIDTH - bgMenu.getWidth()) / 2, (HEIGHT - bgMenu.getHeight()) / 2, 20);

        // Title
        contour.write(g, titleAbout, 5, 0,WIDTH, contour.getHeight(), Component.ALIGN_TOP_LEFT);

        // Grid
        g.drawImage(optionsGrid, 0, WIDTH/9, Graphics.TOP | Graphics.LEFT);

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
        
        Rectangle rectangleOne = new Rectangle(10, 75, WIDTH - 20, 20);
        Rectangle rectangleTwo = new Rectangle(10, 95, WIDTH - 20, 20);
        RectanglePainter rectanglePainter = new  RectanglePainter();
        
        rectanglePainter.paint(g, rectangleOne, 0x111111, 0xFFFFFF);
        rectanglePainter.paint(g, rectangleTwo, 0x111111, 0xFFFFFF);
         
       String text [] = new String[3];
       if (Language.current()  == Language.PORTUGUESE) {
            rectanglePainter.paint(g, rectangleTwo, 0x555555, 0x777777, 0xFFFFFF);
            text[0] = "ESCOLHA";
            text[1] = "SEU";
            text[2] = "IDIOMA";
                       
            
        }
        else {
           
           rectanglePainter.paint(g, rectangleOne, 0x555555, 0x777777, 0xFFFFFF);
            text[0] = "CHOOSE";
            text[1] = "YOUR";
            text[2] = "LANGUAGE";
        } 

       
        contour.write(g, text[0], 0, 5, WIDTH, contour.getHeight(), Component.ALIGN_TOP_CENTER);
        contour.write(g, text[1], 0, 25, WIDTH, contour.getHeight(), Component.ALIGN_TOP_CENTER);
        contour.write(g, text[2], 0, 45, WIDTH, contour.getHeight(), Component.ALIGN_TOP_CENTER);
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
                110, 131, WIDTH, 0, Component.ALIGN_TOP_LEFT);
        // BPM
        metronome.write(g, String.valueOf(bpm),
                110, 154, WIDTH, 0, Component.ALIGN_TOP_LEFT);

       g.drawImage(ball, BALL_BPM_INITIAL_X + (int) (bpm * 0.43), 177, Graphics.TOP | Graphics.LEFT);


    }


    public void drawMenu(Graphics g, Image bgMainMenu, Image bgTitle, Image menu, int index, int animX, int animY) {
        int cy = 0;
        g.drawImage(bgMainMenu, (WIDTH - bgMainMenu.getWidth()) / 2, (HEIGHT - bgMainMenu.getHeight()) / 2, 20);


        g.drawImage(bgTitle, animX, animY, Graphics.TOP | Graphics.LEFT);

        for (int i = 0; i < MAIN_MENU_LENGTH; i++) {
            cy = 67 + (i * 33);

            g.setClip(34, cy, 113, 27);


            if (index == i) {
                g.drawImage(menu, 34, cy - 27, Graphics.TOP | Graphics.LEFT);
            } else {
                g.drawImage(menu, 34, cy, Graphics.TOP | Graphics.LEFT);
            }

            //offset of the label is 6 pixels from the top of the button
            cy += 7;

            //set the clipping rectangle to where the label will be drawn
            g.setClip(34, cy, 113, 13);
            // draw the label so that it is inside the clipping rectangle
            g.drawImage(menu, 34, cy - (55 + (i * 14)), Graphics.TOP | Graphics.LEFT);

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

    public void draw(Graphics g, FontDAO fontDAO, ImageDAO imageDAO, TextDAO textDAO) {
        
    }


}
