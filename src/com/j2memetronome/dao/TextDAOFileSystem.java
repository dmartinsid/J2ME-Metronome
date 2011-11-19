package com.j2memetronome.dao;

import com.j2memetronome.text.TextReader;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author Deivid Martins
 */
public class TextDAOFileSystem implements TextDAO {

    private java.util.Vector texts;

    public TextDAOFileSystem() {
        texts = new Vector();
    }
    public String[] get(String path) throws IOException 
    {
        int index = add(path);
        return ((TextResource) texts.elementAt(index)).getText();

    }
    
    private int add(String path) throws IOException
    {
        TextResource textResource = new TextResource(path);
        
        if(!texts.contains(textResource))
        {
            textResource.setText(new TextReader(path).readFile());
            texts.addElement(textResource);
        }
        
        return texts.indexOf(textResource);
    }

}
