package com.j2memetronome.font;

/**
 *
 * @author Deivid Martins
 */
public class FontAttributes {

    private String path;
    private char[] charset;
    private int[] widths;

    public FontAttributes(String path, char[] charset, int[] widths)
    {
        this.path = path;
        this.charset = charset;
        this.widths = widths;
    }
    public char[] getCharset() {
        return charset;
    }

    public String getPath() {
        return path;
    }

    public int[] getWidths() {
        return widths;
    }

    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.path != null ? this.path.hashCode() : 0);

        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FontAttributes other = (FontAttributes) obj;
        if ((this.path == null) ? (other.path != null) : !this.path.equals(other.path)) {
            return false;
        }

        return true;
    }


    
    



}
