/*
 * NewEmptyJMUnitTest.java
 * JMUnit based test
 *
 * Created on 07/09/2011, 23:55:01
 */
package com.j2memetronome.test;

import com.j2memetronome.Metronome;
import com.j2memetronome.note.RhythmicFigure;
import jmunit.framework.cldc10.*;

/**
 * @author Deivid Martins
 */
public class MetronomeTest extends TestCase {

    public MetronomeTest() {
        super(2, "MetronomeTest");
    }

    public void test(int testNumber) throws Throwable {

        switch (testNumber) {
            case 0:
                sleepTimeMustBe500To120BPMIn4By4();
                break;
            case 1:
                sleepTimeMustBe600To100BPMFor4By4();
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
}
