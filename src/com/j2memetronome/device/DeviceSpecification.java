package com.j2memetronome.device;

import com.j2memetronome.view.MainMenuConfiguration;
import com.j2memetronome.view.MetronomeScreenConfiguration;

/**
 *
 * Based on the device specification the View must render the screens
 * @author dmartins
 */
public interface DeviceSpecification
{

    int getWidth();
    int getHeight();
    int maxLines(); 
    int supportedSounds();
    MainMenuConfiguration getMainMenuConfiguration();
    MetronomeScreenConfiguration getMetronomeScreenConfiguration();
            
    // Device Keys
    int LEFT = -3;
    int RIGHT = -4;
    int UP = -1;
    int DOWN = -2;
    int FIRE = -5;
    int LSK = -6;
    int RSK = -7;
    int CLEAR = -8;

}
