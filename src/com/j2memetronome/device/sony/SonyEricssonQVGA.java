package com.j2memetronome.device.sony;

import com.j2memetronome.device.DeviceSpecification;
import com.j2memetronome.view.MainMenuConfiguration;
import com.j2memetronome.view.MetronomeScreenConfiguration;

/**
 *
 * @author Deivid Martins
 */
public class SonyEricssonQVGA implements  DeviceSpecification{

    private MainMenuConfiguration mainMenuConfiguration;
    private MetronomeScreenConfiguration metronomeScreenConfiguration;


    public SonyEricssonQVGA()
    {
        mainMenuConfiguration = new MainMenuConfiguration(new Double(37.5), 17, 50, 20, 80, 10);
        metronomeScreenConfiguration = new MetronomeScreenConfiguration(120, 178, 120, 210, 0.61, 241);
      
    }
    public int getWidth() {
        return 240;
    }

    public int getHeight() {
        return 320;
    }

    public int maxLines() {
        return 8;
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

