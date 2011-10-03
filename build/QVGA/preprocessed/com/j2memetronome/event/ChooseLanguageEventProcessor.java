/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2memetronome.event;

import com.j2memetronome.appstate.ApplicationState;
import com.j2memetronome.device.GenericDevice;
import com.j2memetronome.i18n.Language;
import javax.microedition.lcdui.Canvas;

/**
 *
 * @author Deivid Martins
 */
public class ChooseLanguageEventProcessor implements EventProcessor {

    public void processEvent(int eventCode, ApplicationState applicationState) {
        switch (eventCode) {
            case Canvas.KEY_NUM5:
            case GenericDevice.LSK:
            case GenericDevice.FIRE: {

                
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
