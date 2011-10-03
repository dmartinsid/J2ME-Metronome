package com.j2memetronome.dao;

import com.j2memetronome.text.TextReader;
import java.io.IOException;

/**
 *
 * @author Deivid Martins
 */
public class TextDAOFileSystem implements TextDAO {

    public String[] get(String path) throws IOException 
    {
        TextReader textReader = new TextReader(path);
        return textReader.readFile();
    }

}
