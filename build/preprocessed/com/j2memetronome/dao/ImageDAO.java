package com.j2memetronome.dao;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Deivid Martins
 */
public interface ImageDAO {

    Image get(String path) throws IOException;

}
