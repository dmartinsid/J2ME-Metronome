package com.j2memetronome.dao;

import com.j2memetronome.font.FontAttributes;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Image;
import mwt.Font;

/**
 *
 * @author Deivid Martins
 */
public class FontDAOFileSystem implements FontDAO {

    private Vector fonts;

    public FontDAOFileSystem() {
        fonts = new Vector();
    }

    public Font get(FontAttributes fontAttributes) throws IOException {
        int index = add(fontAttributes);
        return ((FontResource) fonts.elementAt(index)).getFont();
      
    }
    
    private int add(FontAttributes fontAttributes) throws IOException
    {
        FontResource fontResource = new FontResource(fontAttributes);
        
        if (!fonts.contains(fontResource)) {
            Font font = new Font(Image.createImage(fontAttributes.getPath()), fontAttributes.getCharset(), fontAttributes.getWidths(), 0);
            fontResource.setFont(font);
            fonts.addElement(fontResource);
        }
        
        return fonts.indexOf(fontResource);
    }
}
