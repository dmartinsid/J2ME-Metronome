package com.j2memetronome.device;

import com.j2memetronome.view.MainMenuConfiguration;

/**
 *
 * @author Deivid Martins
 */
public class SonyEricssonQVGA implements  DeviceSpecification{

    private MainMenuConfiguration mainMenuConfiguration;

    public SonyEricssonQVGA()
    {
        mainMenuConfiguration = new MainMenuConfiguration(new Double(37.5), 17, 50, 20, 80, 10);
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
}
