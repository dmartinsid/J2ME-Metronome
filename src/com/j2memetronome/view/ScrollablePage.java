
package com.j2memetronome.view;

import com.j2memetronome.dao.FontDAO;
import com.j2memetronome.dao.ImageDAO;
import com.j2memetronome.dao.TextDAO;
import com.j2memetronome.dao.mapper.FontMapper;
import com.j2memetronome.dao.mapper.ImageMapper;
import com.j2memetronome.dao.mapper.TextMapper;
import com.j2memetronome.device.DeviceSpecification;
import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import mwt.Component;
import mwt.Font;

/**
 *
 * @author Deivid Martins
 */
public class ScrollablePage {

    private int scroll = 0;
    private long start;
    private long end;
    private final int INITIAL_Y = 26;
    private DeviceSpecification deviceSpecification;

    public ScrollablePage(DeviceSpecification deviceSpecification) {
        this.deviceSpecification = deviceSpecification;
    }

    public void draw(Graphics g, FontDAO fontDAO, ImageDAO imageDAO, TextDAO textDAO, String textPath, int title) throws IOException {
        start = System.currentTimeMillis();
        
        checkIfUserCameFromAnotherScreen();

        Image optionsGrid = imageDAO.get(ImageMapper.AUTO_SCROLL_GRID);
        String textCommon[] = textDAO.get(TextMapper.COMMON);
        String text[] = textDAO.get(textPath);
        Font contour = fontDAO.get(FontMapper.CONTOUR);
        Font arial = fontDAO.get(FontMapper.ARIAL);


        contour.write(g, textCommon[title], 5, 0, deviceSpecification.getWidth(), contour.getHeight(), Component.ALIGN_TOP_LEFT);

        g.drawImage(optionsGrid, 0, INITIAL_Y, Graphics.TOP | Graphics.LEFT);

        

        int firstLine = scroll / 10;

        for (int i = firstLine; i < firstLine + deviceSpecification.maxLines() && i < text.length; i++) {

            arial.write(g, text[i], 0,
                    INITIAL_Y
                    + ((int) (arial.getHeight() * (i - firstLine) * 1.5)),
                    deviceSpecification.getWidth(), 0, Component.ALIGN_TOP_CENTER);
        }

        setScroll(firstLine, text);

        end = System.currentTimeMillis();
    }
    
    private void setScroll(int firstLine, String text[])
    {
        if (firstLine > text.length) 
            scroll = 1;
        else
            scroll++;
    }
    
    private void checkIfUserCameFromAnotherScreen()
    {
        if (start - end > 500) {
            scroll = 1;
        }
    }
}