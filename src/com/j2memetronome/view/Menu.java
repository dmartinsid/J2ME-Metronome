package com.j2memetronome.view;

/**
 *
 * @author Deivid Martins
 */
public class Menu {

    private int index;
    
    public static final int START = 0;
    public static final int OPTIONS = START + 1;
    public static final int HELP = OPTIONS + 1;
    public static final int ABOUT = HELP + 1;
    public static int LENGHT = 4;

    public Menu() {
        index = 0;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int maxLength()
    {
        return LENGHT;
    }

    public void nextIndex()
    {
        if(index == LENGHT - 1)
            index = 0;
        else
            index++;
    }

    public void previousIndex()
    {
        if(index == 0)
            index = LENGHT - 1;
        else
            index--;
    }
    

}
