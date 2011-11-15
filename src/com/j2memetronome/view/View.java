package com.j2memetronome.view;

import com.j2memetronome.model.Metronome;
import com.j2memetronome.appstate.ApplicationState;
import com.j2memetronome.dao.FontDAO;
import com.j2memetronome.dao.ImageDAO;
import com.j2memetronome.dao.TextDAO;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Deivid Martins
 */
public interface View {


    void draw(Graphics graphics, FontDAO fontDAO, ImageDAO imageDAO, TextDAO textDAO, ApplicationState applicationState, Metronome metronome) throws Exception;

    int width();
    int height();
    int maxLines();
    Menu menu();
    int supportedSounds();
    
    
}
