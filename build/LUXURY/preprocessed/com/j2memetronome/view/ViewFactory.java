/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.j2memetronome.view;

import com.j2memetronome.device.SonyEricssonLuxury;
import com.j2memetronome.device.SonyEricssonMidsized;
import com.j2memetronome.device.SonyEricssonQVGA;

/**
 *
 * @author Deivid Martins
 */
public class ViewFactory 
{

    public View getView()
    {
        //#ifdef QVGA
//#         return new ViewImpl(new SonyEricssonQVGA());
        //#elif LUXURY
        return new ViewImpl(new SonyEricssonLuxury());
        //#elif NKLUXURY
//#     view = new ViewNKLuxury();
        //#elif NKTOUCH
//#     view = new ViewNKTouch();View
        //#else
//#         return new ViewImpl(new SonyEricssonMidsized());
        //#endif

    }

}
