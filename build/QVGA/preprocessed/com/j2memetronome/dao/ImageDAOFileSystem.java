package com.j2memetronome.dao;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Deivid Martins
 */
public class ImageDAOFileSystem implements ImageDAO {

    public Image get(String path) throws IOException {
        return Image.createImage(path);
    }

}
