package com.j2memetronome.device;

import com.j2memetronome.view.MainMenuConfiguration;

/**
 *
 * @author dmartins
 */
public class SonyEricssonLuxury implements DeviceSpecification{
    
    private MainMenuConfiguration mainMenuConfiguration;

    public SonyEricssonLuxury()
    {
        mainMenuConfiguration = new MainMenuConfiguration(new Double(27.5), 13, 34, 10, 67, 7);
    }
    public int getWidth() {
        return 176;
    }

    public int getHeight() {
        return 220;
    }

    public int maxLines() {
        return 9;
    }

    public int supportedSounds() {
        return 4;
    }

    public MainMenuConfiguration getMainMenuConfiguration() {
        return mainMenuConfiguration;
    }
    
    
}
