/*
 * No JUnit for J2ME =(
 *
 * 
 */
package com.j2memetronome.test;

import com.j2memetronome.model.Metronome;
import com.j2memetronome.musictheory.Measure;
import com.j2memetronome.musictheory.RhythmicFigure;
import jmunit.framework.cldc10.*;

/**
 * @author Deivid Martins
 */
public class MetronomeTest extends TestCase {

    public MetronomeTest() {
        super(4, "MetronomeTest");
    }

    public void test(int testNumber) throws Throwable {

        switch (testNumber) {
            case 0:
                sleepTimeMustBe500To120BPMIn4By4();
                break;
            case 1:
                sleepTimeMustBe600To100BPMFor4By4();
                break;
            case 2:
                shouldNotDecreaseNumeratorWhenNumeratorEquals1();
                break;
            case 3:
                shouldNoIncreaseNumeratorWhenNumeratorEquals350();
                break;

        }


    }

    private void sleepTimeMustBe500To120BPMIn4By4() throws Exception {
        Metronome metronome = new Metronome(120, 4, RhythmicFigure.QUARTER);
        assertEquals(metronome.sleepTime(), 500);

    }

    private void sleepTimeMustBe600To100BPMFor4By4() throws Exception {
        Metronome metronome = new Metronome(100, 4, RhythmicFigure.QUARTER);
        assertEquals(metronome.sleepTime(), 600);
    }

    private void shouldNotDecreaseNumeratorWhenNumeratorEquals1() {
        Metronome metronome = new Metronome(1, 4, RhythmicFigure.WHOLE);
        metronome.decreaseBeatsPerMinute();
        assertEquals(metronome.getBeatsPerMinute(), 1);
    }

    private void shouldNoIncreaseNumeratorWhenNumeratorEquals350() {
        Metronome metronome = new Metronome(350, 4, RhythmicFigure.WHOLE);
        metronome.increaseBeatsPerMinute();
        assertEquals(metronome.getBeatsPerMinute(), 350);
    }
}
