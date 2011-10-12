package com.j2memetronome.dao;

/**
 *
 * @author Deivid Martins
 */
public class TextResource {

    private String path;
    private String[] text;

    public TextResource(String[] text, String path) {
        this.text = text;
        this.path = path;
    }

    public TextResource(String path) {
        this.path = path;
    }
    

    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.path != null ? this.path.hashCode() : 0);
        return hash;
    }

    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final TextResource other = (TextResource) obj;

        if (!this.path.equals(other.path)) {
            return false;
        }
        return true;
    }

    public void setText(String[] text) {
        this.text = text;
    }

    public String[] getText() {
        return text;
    }
    
    
}
