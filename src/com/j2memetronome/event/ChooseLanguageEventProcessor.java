/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2memetronome.event;

import com.j2memetronome.appstate.ApplicationState;
import com.j2memetronome.device.GenericDevice;
import com.j2memetronome.i18n.Language;
import com.j2memetronome.resource.ResourceLoader;
import javax.microedition.lcdui.Canvas;

/**
 *
 * @author Deivid Martins
 */
public class ChooseLanguageEventProcessor implements EventProcessor {

    public void processEvent(int eventCode, ApplicationState applicationState, ResourceLoader resourceLoader) {
        switch (eventCode) {
            case Canvas.KEY_NUM5:
            case GenericDevice.LSK:
            case GenericDevice.FIRE: {

                try {
                    resourceLoader.loadImagesLang();
                    resourceLoader.loadText();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                applicationState.next();
                break;

            }
            case GenericDevice.UP:
                Language.next();
                break;
            case GenericDevice.DOWN:
                Language.previous();
                break;
        }
    }
}
