package com.j2memetronome.dao.mapper;

import com.j2memetronome.font.FontAttributes;
import com.j2memetronome.font.FontsMwt;

/**
 *
 * @author Deivid Martins
 */
public class FontMapper {

    public static final FontAttributes ARIAL = new FontAttributes(ImageMapper.FONT_ARIAL, FontsMwt.ARIAL_CHARSET, FontsMwt.ARIAL_WIDTHS);
    public static final FontAttributes CONTOUR = new FontAttributes(ImageMapper.FONT_CONTOUR, FontsMwt.OUT_CHARSET, FontsMwt.OUT_WIDTHS);
    public static final FontAttributes METRONOME = new FontAttributes(ImageMapper.FONT_METRONOME, FontsMwt.TW_CHARSET, FontsMwt.TW_WIDTHS);
    public static final FontAttributes GREEN = new FontAttributes(ImageMapper.FONT_GREEN, FontsMwt.GREEN_NUMBERS_CHARSET, FontsMwt.GREEN_NUMBERS_WIDTHS);
    public static final FontAttributes RED = new FontAttributes(ImageMapper.FONT_RED, FontsMwt.RED_NUMBERS_CHARSET, FontsMwt.RED_NUMBERS_WIDTHS);
}
