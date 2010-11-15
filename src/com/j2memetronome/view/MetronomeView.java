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
    void drawHelp(Graphics g, Image bgMenu, Image optionsGrid, Image arrowUp, Image arrowDown, String titleHelp, String textHelp[], int firstLineScroll) ;
    void drawOptions(Graphics g, Image bgMenu, Image optionsBar, Image arrowLeft, Image arrowRight, String titleOptions, String textOptions[], int selectedSoundComponent);
    void drawExit(Graphics g, Image bgMenu, String titleExit, String textExit);
    void drawSplash(Graphics g, Image splash);
    void drawChooseLanguage(Graphics g, Image bgMenu, int languageId);
    void drawMetronome(Graphics g);

}
