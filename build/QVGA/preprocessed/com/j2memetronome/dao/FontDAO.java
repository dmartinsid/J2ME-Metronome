package com.j2memetronome.dao;

import com.j2memetronome.font.FontAttributes;
import java.io.IOException;
import mwt.Font;

/**
 *
 * @author Deivid Martins
 */
public interface FontDAO {

    Font get(FontAttributes fontAttributes) throws IOException;

}
