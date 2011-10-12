package com.j2memetronome.dao;

import com.j2memetronome.font.FontAttributes;
import mwt.Font;

/**
 *
 * @author Deivid Martins
 */
public class FontResource {

    private FontAttributes fontAttributes;
    private Font font;

    public FontResource(FontAttributes fontAttributes, Font font) {
        this.fontAttributes = fontAttributes;
        this.font = font;
    }

    public FontResource(FontAttributes fontAttributes) {
        this.fontAttributes = fontAttributes;
    }

    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.fontAttributes != null ? this.fontAttributes.hashCode() : 0);
        hash = 13 * hash + (this.font != null ? this.font.hashCode() : 0);
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FontResource other = (FontResource) obj;
        if (this.fontAttributes != other.fontAttributes && (this.fontAttributes == null || !this.fontAttributes.equals(other.fontAttributes))) {
            return false;
        }

        return true;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }
    
    

  
    
    
    
    
}
