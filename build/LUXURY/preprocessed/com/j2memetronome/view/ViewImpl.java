/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2memetronome.view;

import com.j2memetronome.Metronome;
import com.j2memetronome.appstate.ApplicationState;
import com.j2memetronome.dao.FontDAO;
import com.j2memetronome.dao.ImageDAO;
import com.j2memetronome.dao.TextDAO;
import com.j2memetronome.dao.mapper.FontMapper;
import com.j2memetronome.dao.mapper.TextMapper;
import com.j2memetronome.device.DeviceSpecification;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import mwt.Component;
import mwt.Font;

/**
 *
 * @author dmartins
 */
public class ViewImpl implements View{

    private SoftkeyPainter softkeyPainter;
    private DeviceSpecification deviceSpecification;
 
    public ViewImpl(DeviceSpecification deviceSpecification)
    {
        this.deviceSpecification = deviceSpecification;
        softkeyPainter = new SoftkeyPainter();
    }

    public int getWidth() {
        return deviceSpecification.getWidth();
    }

    public int getHeight() {
        return deviceSpecification.getHeight();
    }

    public int maxLines() {
        return deviceSpecification.maxLines();
    }

    public int supportedSounds() {
        return deviceSpecification.supportedSounds();
    }

    public void draw(Graphics graphics, FontDAO fontDAO, ImageDAO imageDAO, TextDAO textDAO, ApplicationState applicationState, Metronome metronome) throws Exception {
        graphics.setColor(0x00000000);
        graphics.fillRect(0, 0,getWidth(), getHeight());
        switch (applicationState.getState()) {
            case ApplicationState.SPLASH:
                drawSplash(graphics, fontDAO, imageDAO, textDAO);
                break;
            case ApplicationState.MAIN_MENU:
                drawMainMenu(graphics, fontDAO, imageDAO, textDAO);
                break;
            case ApplicationState.OPTIONS:
                drawOptions(graphics, fontDAO, imageDAO, textDAO, metronome);
                break;
            case ApplicationState.HELP:
                drawHelp(graphics, fontDAO, imageDAO, textDAO);
                break;
            case ApplicationState.ABOUT:
                drawAbout(graphics, fontDAO, imageDAO, textDAO);
                break;
            case ApplicationState.EXIT:
                drawExit(graphics, fontDAO, imageDAO, textDAO);
                break;

            case ApplicationState.METRONOME_STARTED:
                drawMetronomeStarted(graphics, fontDAO, imageDAO, textDAO, metronome);
                break;
            case ApplicationState.METRONOME_STOPPED:
                drawMetronomeCore(graphics, fontDAO, imageDAO, textDAO, metronome);
                break;

        }





    }
    
    private void drawBackground(Graphics g, ImageDAO imageDAO, String backgroundImagePath) throws IOException
    {
        g.drawImage(imageDAO.get(backgroundImagePath), 0, 0, Graphics.TOP | Graphics.LEFT);
    }

    
    private void drawSplash(Graphics graphics, FontDAO fontDAO, ImageDAO imageDAO, TextDAO textDAO) throws IOException {
        drawBackground(graphics, imageDAO, "/Splash.png");

    }

    private void drawMainMenu(Graphics graphics, FontDAO fontDAO, ImageDAO imageDAO, TextDAO textDAO) throws IOException 
    {       
        drawBackground(graphics, imageDAO, "/bg.png");
        
        // TODO put in constructor
        MainMenuPainter mainMenuPainter = new MainMenuPainter(imageDAO, deviceSpecification.getMainMenuConfiguration());
        
        mainMenuPainter.paint(graphics);
        softkeyPainter.paint(graphics, imageDAO, this, SoftKeyType.BOTH);
    }

    private void drawOptions(Graphics g, FontDAO fontDAO, ImageDAO imageDAO, TextDAO textDAO, Metronome metronome) throws IOException 
    {
        drawBackground(g, imageDAO, "/bg.png");
        
        Image arrowLeft = imageDAO.get("/white_arrow_left.png");
        Image arrowRight = imageDAO.get("/white_arrow_right.png"); 
        Image optionsBar = imageDAO.get("/optionsBar.png");
        Font contour = fontDAO.get(FontMapper.CONTOUR);
        Font arial = fontDAO.get(FontMapper.ARIAL);
        
        String text[] = textDAO.get("/en/common.txt");
   
              
        contour.write(g, text[TextMapper.OPTIONS], 5, 0, deviceSpecification.getWidth(), contour.getHeight(), Component.ALIGN_TOP_LEFT);

        g.drawImage(arrowLeft, 5, deviceSpecification.getHeight() / 2 - 10, Graphics.TOP | Graphics.LEFT);
        g.drawImage(arrowRight, deviceSpecification.getWidth() - 5 - arrowRight.getWidth(), deviceSpecification.getHeight() / 2 - 10, Graphics.TOP | Graphics.LEFT);
        g.drawImage(optionsBar, 0, deviceSpecification.getHeight() / 2 - 20, Graphics.TOP | Graphics.LEFT);

        arial.write(g, text[TextMapper.KITS], 0, deviceSpecification.getHeight() / 2 - 10,
                deviceSpecification.getWidth(), arial.getHeight(), Component.ALIGN_TOP_CENTER);

        arial.write(g, text[TextMapper.BASS_DRUM_AND_SNARE + metronome.getKit()], 0, deviceSpecification.getHeight() / 2 + 10,
                deviceSpecification.getWidth(), arial.getHeight(), Component.ALIGN_TOP_CENTER);
        
        softkeyPainter.paint(g, imageDAO, this, SoftKeyType.LEFT);

    }
    int scroll = 0;

    private void drawHelp(Graphics g, FontDAO fontDAO, ImageDAO imageDAO, TextDAO textDAO) throws IOException {
        drawAutoScrollPage(g, fontDAO, imageDAO, textDAO, "/en/help.txt", TextMapper.HELP);

    }

    private void drawAbout(Graphics g, FontDAO fontDAO, ImageDAO imageDAO, TextDAO textDAO) throws IOException {

        drawAutoScrollPage(g, fontDAO, imageDAO, textDAO, "/en/about.txt", TextMapper.ABOUT);
        

    }

    private void drawAutoScrollPage(Graphics g, FontDAO fontDAO, ImageDAO imageDAO, TextDAO textDAO, String textPath, int title) throws IOException {
        drawBackground(g, imageDAO, "/bg.png");
        
        Image optionsGrid = imageDAO.get("/optionsGridMainMenu.png");
        String textCommon[] = textDAO.get("/en/common.txt");
        String text[] = textDAO.get(textPath);
        Font contour = fontDAO.get(FontMapper.CONTOUR);
        Font arial = fontDAO.get(FontMapper.ARIAL);


        contour.write(g, textCommon[title], 5, 0, deviceSpecification.getWidth(), contour.getHeight(), Component.ALIGN_TOP_LEFT);

        // TODO need to be refactored- need to be removed the  "-4"
        g.drawImage(optionsGrid, 0, ABOUT_AND_HELP_TEXT_INITIAL_Y - 4, Graphics.TOP | Graphics.LEFT);

        scroll++;
        int firstLineScroll = scroll / 10;


        for (int i = firstLineScroll; i < firstLineScroll + deviceSpecification.maxLines() && i < text.length; i++) {

            arial.write(g, text[i], 0,
                    ABOUT_AND_HELP_TEXT_INITIAL_Y
                    + ((int) (arial.getHeight() * (i - firstLineScroll) * 1.5)),
                    deviceSpecification.getWidth(), 0, Component.ALIGN_TOP_CENTER);
        }


        if (firstLineScroll > text.length) {
            scroll = 0;
        }
        softkeyPainter.paint(g, imageDAO, this, SoftKeyType.RIGHT);
    }

    private void drawExit(Graphics g, FontDAO fontDAO, ImageDAO imageDAO, TextDAO textDAO) throws IOException {
        drawBackground(g, imageDAO, "/bg.png");
        
        String textCommon[] = textDAO.get("/en/common.txt");
        Font contour = fontDAO.get(FontMapper.CONTOUR);
        Font arial = fontDAO.get(FontMapper.ARIAL);


        contour.write(g, textCommon[TextMapper.EXIT], 5, 0, deviceSpecification.getWidth(), arial.getHeight(), Component.ALIGN_TOP_LEFT);
        arial.write(g, textCommon[TextMapper.EXIT_TEXT], 0, deviceSpecification.getHeight() / 2, deviceSpecification.getWidth(), 0, Component.ALIGN_TOP_CENTER);
        
        softkeyPainter.paint(g, imageDAO, this, SoftKeyType.BOTH);
    }

    private void drawMetronomeStarted(Graphics g, FontDAO fontDAO, ImageDAO imageDAO, TextDAO textDAO, Metronome metronome) throws IOException {

        drawMetronomeCore(g, fontDAO, imageDAO, textDAO, metronome);
        
        Font metronomeRed = fontDAO.get(FontMapper.RED);
        Font metronomeGreen = fontDAO.get(FontMapper.GREEN);

        if (metronome.getActualBeat() == 1) {
            metronomeRed.write(g, String.valueOf(metronome.getActualBeat()), 0, deviceSpecification.getHeight() / 5, deviceSpecification.getWidth(), 0, Component.ALIGN_TOP_CENTER);
        } else {
            metronomeGreen.write(g, String.valueOf(metronome.getActualBeat()), 0, deviceSpecification.getHeight() / 5, deviceSpecification.getWidth(), 0, Component.ALIGN_TOP_CENTER);
        }


        metronome.process(true);


    }

    private void drawMetronomeCore(Graphics g, FontDAO fontDAO, ImageDAO imageDAO, TextDAO textDAO, Metronome metronome) throws IOException {
        drawBackground(g, imageDAO, "/images_multilang/en/metronome_canvas_bg.png");

        Image ball = imageDAO.get("/ball.png");
        
        Font font = fontDAO.get(FontMapper.METRONOME);


        font.write(g, metronome.getNumerator() + "/" + metronome.getDenominator().intValue(), 110, 131, deviceSpecification.getWidth(), 0, Component.ALIGN_TOP_LEFT);

        font.write(g, String.valueOf(metronome.getBeatsPerMinute()), 110, 154, deviceSpecification.getWidth(), 0, Component.ALIGN_TOP_LEFT);

        g.drawImage(ball, BALL_BPM_INITIAL_X + (int) (metronome.getBeatsPerMinute() * 0.43), 177, Graphics.TOP | Graphics.LEFT);

    }
}
