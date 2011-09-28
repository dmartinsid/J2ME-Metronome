package com.j2memetronome.view;

/**
 *
 * @author Deivid Martins
 */
public class Menu {

    private int index;
    private int MAIN_MENU_LENGHT = 4;

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
        return MAIN_MENU_LENGHT;
    }

    public void nextIndex()
    {
        if(index == MAIN_MENU_LENGHT - 1)
            index = 0;
        else
            index++;
    }

    public void previousIndex()
    {
        if(index == 0)
            index = 3;
        else
            index--;
    }
    

}
