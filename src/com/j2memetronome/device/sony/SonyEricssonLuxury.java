package com.j2memetronome.device.sony;

import com.j2memetronome.device.DeviceSpecification;
import com.j2memetronome.view.MainMenuConfiguration;
import com.j2memetronome.view.MetronomeScreenConfiguration;

/**
 *
 * @author dmartins
 */
public class SonyEricssonLuxury implements DeviceSpecification{
    
    private MainMenuConfiguration mainMenuConfiguration;
    private MetronomeScreenConfiguration metronomeScreenConfiguration;

    public SonyEricssonLuxury()
    {
        mainMenuConfiguration = new MainMenuConfiguration(new Double(27.5), 13, 34, 10, 67, 7);
        metronomeScreenConfiguration = new MetronomeScreenConfiguration(110, 131, 110, 154, 0.43, 177);
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

    public MetronomeScreenConfiguration getMetronomeScreenConfiguration() {
        return metronomeScreenConfiguration;
    }
    
    
}
