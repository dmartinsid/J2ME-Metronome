package com.j2memetronome.dao;

import com.j2memetronome.font.FontAttributes;
import java.io.IOException;
import javax.microedition.lcdui.Image;
import mwt.Font;

/**
 *
 * @author Deivid Martins
 */
public class FontDAOFileSystem implements FontDAO {

    public Font get(FontAttributes fontAttributes) throws IOException
    {
        return new Font(Image.createImage(fontAttributes.getPath()), fontAttributes.getCharset(),fontAttributes.getWidths(), 0);
    }

}
