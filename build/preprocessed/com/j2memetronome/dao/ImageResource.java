package com.j2memetronome.dao;

import javax.microedition.lcdui.Image;

/**
 *
 * @author Deivid Martins
 */
public class ImageResource {
    
    private Image image;
    private String path;

    public ImageResource(Image image, String path) {
        this.image = image;
        this.path = path;
    }

    public ImageResource(String path) {
        this.path = path;
    }
    
    

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
       
        final ImageResource other = (ImageResource) obj;

        if (!this.path.equals(other.path)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.image != null ? this.image.hashCode() : 0);
        hash = 89 * hash + (this.path != null ? this.path.hashCode() : 0);
        return hash;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    
    
    
}
