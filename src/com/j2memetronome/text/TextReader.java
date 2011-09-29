package com.j2memetronome.text;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

/**
 *
 * @author dmartins
 */
public class TextReader {

    private InputStream inputStream;
    private StringBuffer stringBuffer;
    private Vector temp;
    private String file;

    public TextReader()
    {
        
    }
     public TextReader(String file) throws IOException
    {
       this.file = file;
    }

    public String[] readFile() throws IOException {
        int count = 1, c;
        String result[];

        inputStream = this.getClass().getResourceAsStream(file);
        stringBuffer = new StringBuffer();
        temp = new Vector();

        while ((c = inputStream.read()) != -1) {
            if ((char) c == '\r') {
                temp.addElement(stringBuffer.toString());
                stringBuffer.delete(0, stringBuffer.length());
                count++;
            } else if ((char) c == '\n'); else {
                stringBuffer.append((char) c);
            }
        }
        temp.addElement(stringBuffer.toString());
        inputStream.close();

        result = new String[count];
        for (int i = 0; i < temp.size(); i++) {
            result[i] = new String(temp.elementAt(i).toString());
        }
        return result;
    }

    public String[] readFile(String file) throws IOException {
        this.file = file;
        return readFile();
    }


}
