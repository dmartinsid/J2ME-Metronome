package com.j2memetronome.dao;

import java.io.IOException;

/**
 *
 * @author Deivid Martins
 */
public interface TextDAO  {

    String[] get(String path) throws IOException;

}
