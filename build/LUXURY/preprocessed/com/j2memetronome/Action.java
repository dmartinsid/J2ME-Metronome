package com.j2memetronome;

/**
 *
 * @author Deivid Cunha Martins
 */
public class Action {

    public static final Action INCREMENT = new Action("increment");
    public static final Action DECREMENT = new Action("decrement");
    private final String action;

    private Action(String action) {
        this.action = action;
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        final Action other = (Action) object;
        if ((this.action == null) ? (other.action != null) : !this.action.equals(other.action)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.action != null ? this.action.hashCode() : 0);
        return hash;
    }




}
