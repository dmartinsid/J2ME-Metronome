/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.j2memetronome.view;

import com.j2memetronome.device.sony.SonyEricssonLuxury;
import com.j2memetronome.device.sony.SonyEricssonMidsized;
import com.j2memetronome.device.sony.SonyEricssonQVGA;

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
//#         return new ViewImpl(new SonyEricssonLuxury());
        //#else
        return new ViewImpl(new SonyEricssonMidsized());
        //#endif

    }

}
