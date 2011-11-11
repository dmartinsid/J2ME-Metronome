package com.j2memetronome.dao.mapper;

import com.j2memetronome.font.FontAttributes;
import com.j2memetronome.font.FontsMwt;

/**
 *
 * @author Deivid Martins
 */
public class FontMapper {

    public static final FontAttributes ARIAL = new FontAttributes("/arial_12.png", FontsMwt.ARIAL_CHARSET, FontsMwt.ARIAL_WIDTHS);
    public static final FontAttributes CONTOUR = new FontAttributes("/out.png", FontsMwt.OUT_CHARSET, FontsMwt.OUT_WIDTHS);
    public static final FontAttributes METRONOME = new FontAttributes("/tw_font.png", FontsMwt.TW_CHARSET, FontsMwt.TW_WIDTHS);
    public static final FontAttributes GREEN = new FontAttributes("/numbers_green.png", FontsMwt.GREEN_NUMBERS_CHARSET, FontsMwt.GREEN_NUMBERS_WIDTHS);
    public static final FontAttributes RED = new FontAttributes("/numbers_red.png", FontsMwt.RED_NUMBERS_CHARSET, FontsMwt.RED_NUMBERS_WIDTHS);
}
