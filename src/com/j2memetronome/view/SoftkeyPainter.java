package com.j2memetronome.view;

import com.j2memetronome.dao.ImageDAO;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Deivid Martins
 */
public class SoftkeyPainter {

    void paint(Graphics g, ImageDAO imageDAO, View view, SoftKeyType softKeyType) throws IOException {
        g.setClip(0, 0, view.getWidth(), view.getHeight());
        if (softKeyType.equals(SoftKeyType.LEFT)) {
            drawLeft(g, imageDAO, view);
        } else if (softKeyType.equals(SoftKeyType.RIGHT)) {

            drawRight(g, imageDAO, view);

        } else if (softKeyType.equals(SoftKeyType.BOTH)) {
            drawBoth(g, imageDAO, view);
        }

    }

    private void drawRight(Graphics g, ImageDAO imageDAO, View view) throws IOException {
        Image cancel = imageDAO.get("/cancel.png");
        g.drawImage(cancel, view.getWidth() - cancel.getWidth(), view.getHeight() - cancel.getHeight(), Graphics.TOP | Graphics.LEFT);
    }

    private void drawLeft(Graphics g, ImageDAO imageDAO, View view) throws IOException {
        Image ok = imageDAO.get("/ok.png");
        g.drawImage(ok, 0, view.getHeight() - ok.getHeight(), Graphics.TOP | Graphics.LEFT);
    }

    private void drawBoth(Graphics g, ImageDAO imageDAO, View view) throws IOException {
        drawLeft(g, imageDAO, view);
        drawRight(g, imageDAO, view);
    }
}
