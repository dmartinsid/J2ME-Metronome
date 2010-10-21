/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.j2memetronome.view;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Deivid Martins
 */
public interface MetronomeView {

    void drawSoftKeys(Graphics g, int state, Image ok, Image cancel);
    void drawAbout(Graphics g, Image bgMenu, Image optionsGrid, Image arrowUp, Image arrowDown, String titleAbout, String textAbout[], int firstLineScroll);
    void drawHelp(Graphics g);
    void drawOptions(Graphics g);
    void drawExit(Graphics g);
    void drawSplash(Graphics g);
    void drawChooseLanguage(Graphics g);
    void drawMetronome(Graphics g);

}
