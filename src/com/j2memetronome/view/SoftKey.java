package com.j2memetronome.view;

import com.j2memetronome.dao.ImageDAO;
import com.j2memetronome.dao.mapper.ImageMapper;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Deivid Martins
 */


abstract class SoftKey {

    abstract public void paint(Graphics g, ImageDAO imageDAO, View view) throws IOException;

    public static final SoftKey LEFT = new SoftKey() {

        public void paint(Graphics g, ImageDAO imageDAO, View view) throws IOException {
            Image ok = imageDAO.get(ImageMapper.SOFTKEY_OK);
            g.drawImage(ok, 0, view.height() - ok.getHeight(), Graphics.TOP | Graphics.LEFT);
        }
    };
    public static final SoftKey RIGHT = new SoftKey() {

        public void paint(Graphics g, ImageDAO imageDAO, View view) throws IOException {
            Image cancel = imageDAO.get(ImageMapper.SOFTKEY_CANCEL);
            g.drawImage(cancel, view.width() - cancel.getWidth(), view.height() - cancel.getHeight(), Graphics.TOP | Graphics.LEFT);
        }
    };
    public static final SoftKey BOTH = new SoftKey() {

        public void paint(Graphics g, ImageDAO imageDAO, View view) throws IOException {
            LEFT.paint(g, imageDAO, view);
            RIGHT.paint(g, imageDAO, view);
        }
    };
 

  
}
