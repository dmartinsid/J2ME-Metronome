package com.j2memetronome.device.sony;

import com.j2memetronome.device.DeviceSpecification;
import com.j2memetronome.view.MainMenuConfiguration;
import com.j2memetronome.view.MetronomeScreenConfiguration;

/**
 *
 * @author Deivid Martins
 */
public class SonyEricssonMidsized implements DeviceSpecification{

    private MainMenuConfiguration mainMenuConfiguration;
    private MetronomeScreenConfiguration metronomeScreenConfiguration;
    
    public SonyEricssonMidsized()
    {
        mainMenuConfiguration = new MainMenuConfiguration(new Double(20.0), 10, 23, 5, 45, 7);
        metronomeScreenConfiguration = new MetronomeScreenConfiguration(80, 93, 80, 110, 0.3, 130);
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
    
    public MetronomeScreenConfiguration getMetronomeScreenConfiguration() {
        return metronomeScreenConfiguration;
    }
    

}
