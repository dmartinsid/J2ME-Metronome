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
import javax.microedition.lcdui.Graphics;
import mwt.Font;

/**
 *
 * @author Deivid Martins
 */
public interface View {


    void draw(Graphics graphics, FontDAO fontDAO, ImageDAO imageDAO, TextDAO textDAO, ApplicationState applicationState, Metronome metronome) throws Exception;

    int getWidth();
    int getHeight();
    int maxLines();
   
    int supportedSounds();

    int MAIN_MENU_LENGTH = 4;
    int ABOUT_AND_HELP_TEXT_INITIAL_Y = 30;
    int BALL_BPM_INITIAL_X = 8;
}