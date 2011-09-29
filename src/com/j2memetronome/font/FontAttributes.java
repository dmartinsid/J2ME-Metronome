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



}
