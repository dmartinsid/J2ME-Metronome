package com.j2memetronome.view;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Deivid Martins
 */
public class RectanglePainter {

    void paint(Graphics g, Rectangle rectangle, int color, int borderColor) {
        g.setColor(color);
        g.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        g.setColor(borderColor);
        g.drawRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }
    /*
     * Two colors
     */
    void paint(Graphics g, Rectangle rectangle, int color1, int color2, int borderColor)
    {
          g.setColor(color1);
            //g.fillRect(10, 95, WIDTH - 20, 10);
            g.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight()/2);
            g.setColor(color2);
            //g.fillRect(10, 105, WIDTH - 20, 10);
            g.fillRect(rectangle.getX(), rectangle.getY() + rectangle.getHeight()/2, rectangle.getWidth(), rectangle.getHeight()/2);
            g.setColor(borderColor);
            g.drawRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        
    }
}
