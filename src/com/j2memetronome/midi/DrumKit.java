package com.j2memetronome.midi;

/**
 *  This interface contains the drum kit components
 * 
 *  @author Deivid Cunha Martins
 */
public interface DrumKit
{
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

     // MIDI numbers of each component
     int[] DRUM_NUMBERS = {
        35, // Acoustic Bass Drum
        36, // Bass Drum
        37, // Side Stick
        38, // Acoustic Snare
        39, // Hand Clap
        40, // Electric Snare
        41, // Low Floor Tom
        42, // Closed Hi-Hat
        43, // High Floor Tom
        44, // Pedal Hi-Hat
        45, // Low Tom
        46, // Open Hi-Hat
        47, // Low-Mid Tom
        48, // Hi-Mid Tom
        49, // Crash Cymbal 1
        50, // High Tom
        51, // Ride Cymbal 1
        52, // Chinese Cymbal
        53, // Ride Bell
        54, // Tambourine
        55, // Splash Cymbal
        56, // Cowbell
        57, // Crash Cymbal 2
        58, // Vibraslap
        59, // Ride Cymbal 2
    };

}
