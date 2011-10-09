package com.j2memetronome.view;

/**
 *
 * @author Deivid Martins
 */
public class MainMenuConfiguration {
    

    private Double rectangleHeight;
    private int textHeight;
    private int leftAlignment;
    private int spaceBetweenRectangles;
    private int startYposition;
    private int titleTextYposition;
    private int doubleRectangleHeight;
    private int diff;

    public MainMenuConfiguration(Double rectangleHeight, int textHeight, int leftAlignment, int spaceBetweenRectangles, int startYposition, int titleTextYposition) 
    {
        this.rectangleHeight = rectangleHeight;
        this.textHeight = textHeight;
        this.leftAlignment = leftAlignment;
        this.spaceBetweenRectangles = spaceBetweenRectangles;
        this.startYposition = startYposition;
        this.titleTextYposition = titleTextYposition;       
        this.doubleRectangleHeight = (int) (2 * rectangleHeight.doubleValue());
        
        // TODO need to be refactored, /8 eight is is not clear
        this.diff = textHeight % 2 != 0 ? textHeight + (textHeight/8) : textHeight; 
    }

    public int getDiff() {
        return diff;
    }

    public int getLeftAlignment() {
        return leftAlignment;
    }

    public int getDoubleRectangleHeight() {
        return doubleRectangleHeight;
    }

    public Double getRectangleHeight() {
        return rectangleHeight;
    }

    public int getSpaceBetweenRectangles() {
        return spaceBetweenRectangles;
    }

    public int getStartYposition() {
        return startYposition;
    }

    public int getTextHeight() {
        return textHeight;
    }

    public int getTitleTextYposition() {
        return titleTextYposition;
    }
    
    
    
    
    
    
}
