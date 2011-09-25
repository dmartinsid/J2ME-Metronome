/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.j2memetronome.view;

/**
 *
 * @author Deivid Martins
 */
public class ViewFactory 
{

    public View getView()
    {
        //#ifdef QVGA
//#     view = new ViewSEQVGA();
        //#elif LUXURY
//#         return new ViewSELuxury();
        //#elif NKLUXURY
//#     view = new ViewNKLuxury();
        //#elif NKTOUCH
//#     view = new ViewNKTouch();View
        //#else
    view = new ViewSEMidsized();
        //#endif

    }

}
