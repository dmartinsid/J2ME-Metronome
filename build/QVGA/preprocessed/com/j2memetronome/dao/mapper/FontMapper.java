package com.j2memetronome.dao.mapper;

import com.j2memetronome.font.FontAttributes;
import com.j2memetronome.font.FontConstants;

/**
 *
 * @author Deivid Martins
 */
public class FontMapper {

    public static final FontAttributes ARIAL = new FontAttributes("/arial_12.png", FontConstants.FONT_ARIAL_CHARSET, FontConstants.FONT_ARIAL_WIDTHS);
    public static final FontAttributes CONTOUR = new FontAttributes("/out.png", FontConstants.FONT_OUT_CHARSET, FontConstants.FONT_OUT_WIDTHS);
    public static final FontAttributes METRONOME = new FontAttributes("/tw_font.png", FontConstants.FONT_TW_CHARSET, FontConstants.FONT_TW_WIDTHS);
    public static final FontAttributes GREEN = new FontAttributes("/numbers_green.png", FontConstants.FONT_GREEN_NUMBERS_CHARSET, FontConstants.FONT_GREEN_NUMBERS_WIDTHS);
    public static final FontAttributes RED = new FontAttributes("/numbers_red.png", FontConstants.FONT_RED_NUMBERS_CHARSET, FontConstants.FONT_RED_NUMBERS_WIDTHS);
}
