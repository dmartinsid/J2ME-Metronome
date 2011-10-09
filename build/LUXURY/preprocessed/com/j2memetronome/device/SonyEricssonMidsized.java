/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.j2memetronome.device;

import com.j2memetronome.view.MainMenuConfiguration;

/**
 *
 * @author dmartins
 */
public class SonyEricssonMidsized implements DeviceSpecification{

    private MainMenuConfiguration mainMenuConfiguration;

    public SonyEricssonMidsized()
    {
        mainMenuConfiguration = new MainMenuConfiguration(new Double(27.5), 13, 34, 10, 67, 7);
    }
    public int getWidth() {
        return 128;
    }

    public int getHeight() {
        return 160;
    }

    public int maxLines() {
        return 6;
    }

    public int supportedSounds() {
        return 4;
    }

    public MainMenuConfiguration getMainMenuConfiguration() {
        return mainMenuConfiguration;
    }

}
