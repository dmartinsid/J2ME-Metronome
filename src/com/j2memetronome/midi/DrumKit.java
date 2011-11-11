package com.j2memetronome.midi;

/**
 *
 * @author Deivid Martins
 */
public class DrumKit {

    private final int midiMapping;
    
    public static final DrumKit METRONOME_BELL = new DrumKit(34);
    public static final DrumKit METRONOME_CLICK = new DrumKit(35);
    public static final DrumKit ACOUSTIC_BASS_DRUM = new DrumKit(35);
    public static final DrumKit BASS_DRUM = new DrumKit(36);
    public static final DrumKit SIDE_STICK = new DrumKit(37);
    public static final DrumKit ACOUSTIC_SNARE = new DrumKit(38);
    public static final DrumKit HAND_CLAP = new DrumKit(39);
    public static final DrumKit ELECTRIC_SNARE = new DrumKit(40);
    public static final DrumKit LOW_FLOOR_TOM = new DrumKit(41);
    public static final DrumKit CLOSED_HI_HAT = new DrumKit(42);
    public static final DrumKit HIGH_FLOOR_TOM = new DrumKit(43);
    public static final DrumKit PEDAL_HI_HAT = new DrumKit(44);
    public static final DrumKit LOW_TOM = new DrumKit(45);
    public static final DrumKit OPEN_HI_HAT = new DrumKit(46);
    public static final DrumKit LOW_MID_TOM = new DrumKit(47);
    public static final DrumKit HI_MID_TOM = new DrumKit(48);
    public static final DrumKit CRASH_CYMBAL_1 = new DrumKit(49);
    public static final DrumKit HIGH_TOM = new DrumKit(50);
    public static final DrumKit RIDE_CYMBAL_1 = new DrumKit(51);
    public static final DrumKit CHINESE_CYMBAL = new DrumKit(52);
    public static final DrumKit RIDE_BELL = new DrumKit(53);
    public static final DrumKit TAMBOURINE = new DrumKit(54);
    public static final DrumKit SPLASH_CYMBAL = new DrumKit(55);
    public static final DrumKit COWBELL = new DrumKit(56);
    public static final DrumKit CRASH_CYMBAL_2 = new DrumKit(57);
    public static final DrumKit VIBRASLAP = new DrumKit(58);
    public static final DrumKit RIDE_CYMBAL2 = new DrumKit(59);
    


    private DrumKit(int midiMapping) {
        this.midiMapping = midiMapping;
    }
   
    public int midiMapping()
    {
        return midiMapping;
    }
}
