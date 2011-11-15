package com.j2memetronome.view;

import com.j2memetronome.dao.FontDAO;
import com.j2memetronome.dao.ImageDAO;
import com.j2memetronome.dao.mapper.FontMapper;
import com.j2memetronome.dao.mapper.ImageMapper;
import com.j2memetronome.device.DeviceSpecification;
import com.j2memetronome.model.Metronome;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import mwt.Component;
import mwt.Font;

/**
 *
 * @author Deivid Martins
 */
public class MetronomePainter {
    
    private final int BALL_BPM_INITIAL_X = 8;
    
    public void drawCore(Graphics g, FontDAO fontDAO, ImageDAO imageDAO, 
            DeviceSpecification deviceSpecification, Metronome metronome) throws IOException {
 

        Image ball = imageDAO.get(ImageMapper.BALL);
        
        Font font = fontDAO.get(FontMapper.METRONOME);

        
        font.write(g, metronome.getMeasure().toString(), 
                deviceSpecification.getMetronomeScreenConfiguration().getMeasureX(), 
                deviceSpecification.getMetronomeScreenConfiguration().getMeasureY(), 
                deviceSpecification.getWidth(), 0, Component.ALIGN_TOP_LEFT);

        font.write(g, String.valueOf(metronome.getBeatsPerMinute()), 
                deviceSpecification.getMetronomeScreenConfiguration().getBpmX(),
                deviceSpecification.getMetronomeScreenConfiguration().getBpmY(), 
                deviceSpecification.getWidth(), 0, Component.ALIGN_TOP_LEFT);

        g.drawImage(ball, BALL_BPM_INITIAL_X + (int) (metronome.getBeatsPerMinute() * deviceSpecification.getMetronomeScreenConfiguration().getBallCoefficientX()), 
                deviceSpecification.getMetronomeScreenConfiguration().getBallY(), Graphics.TOP | Graphics.LEFT);

    }
    
    public void drawStarted(Graphics g, FontDAO fontDAO, ImageDAO imageDAO,  DeviceSpecification deviceSpecification, 
            Metronome metronome) throws IOException {

        drawCore(g, fontDAO, imageDAO, deviceSpecification, metronome);
        
        Font metronomeRed = fontDAO.get(FontMapper.RED);
        Font metronomeGreen = fontDAO.get(FontMapper.GREEN);

        if (metronome.getActualBeat() == 1) {
            metronomeRed.write(g, String.valueOf(metronome.getActualBeat()), 0, deviceSpecification.getHeight() / 5, deviceSpecification.getWidth(), 0, Component.ALIGN_TOP_CENTER);
        } else {
            metronomeGreen.write(g, String.valueOf(metronome.getActualBeat()), 0, deviceSpecification.getHeight() / 5, deviceSpecification.getWidth(), 0, Component.ALIGN_TOP_CENTER);
        }


        metronome.play();


    }
    
}
