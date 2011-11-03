package com.j2memetronome.midi;

/**
 *
 * @author dmartins
 */
public interface DrumKit {

    // TODO must use the enum pattern to be more readable
    
    byte[] DRUM_NUMBERS = {
        0x23, // Acoustic Bass Drum
        0x24, // Bass Drum
        0x25, // Side Stick
        0x26, // Acoustic Snare
        0x27, // Hand Clap
        0x28, // Electric Snare
        0x29, // Low Floor Tom
        0x2A, // Closed Hi-Hat
        0x2B, // High Floor Tom
        0x2C, // Pedal Hi-Hat
        0x2D, // Low Tom
        0x2E, // Open Hi-Hat
        0x2F, // Low-Mid Tom
        0x30, // Hi-Mid Tom
        0x31, // Crash Cymbal 1
        0x32, // High Tom
        0x33, // Ride Cymbal 1
        0x34, // Chinese Cymbal
        0x35, // Ride Bell
        0x36, // Tambourine
        0x37, // Splash Cymbal
        0x38, // Cowbell
        0x39, // Crash Cymbal 2
        0x3A, // Vibraslap
        0x3B, // Ride Cymbal 2
        0x22, // Metronome Bell
        0x21  // Metronome Click
    };

     int ACOUSTIC_BASS_DRUM = 0;
     int BASS_DRUM = 1;
     int SIDE_STICK = 2;
     int ACOUSTIC_SNARE = 3;
     int HAND_CLAP = 4;
     int ELECTRIC_SNARE = 5;
     int LOW_FLOOR_TOM = 6;
     int CLOSED_HI_HAT = 7;
     int HIGH_FLOOR_TOM = 8;
     int PEDAL_HI_HAT = 9;
     int LOW_TOM = 10;
     int OPEN_HI_HAT = 11;
     int LOW_MID_TOM = 12;
     int HI_MID_TOM = 13;
     int CRASH_CYMBAL_1 = 14;
     int HIGH_TOM = 15;
     int RIDE_CYMBAL_1 = 16;
     int CHINESE_CYMBAL = 17;
     int RIDE_BELL = 18;
     int TAMBOURINE = 19;
     int SPLASH_CYMBAL = 20;
     int COWBELL = 21;
     int CRASH_CYMBAL_2 = 22;
     int VIBRASLAP = 23;
     int RIDE_CYMBAL2 = 24;
     int METRONOME_BELL = 25;
     int METRONOME_CLICK = 26;
}
