package com.j2memetronome.view;

/**
 *
 * @author Deivid Martins
 */
class SoftKeyType {

    public static final SoftKeyType LEFT = new SoftKeyType(0);
    public static final SoftKeyType RIGHT = new SoftKeyType(1);
    public static final SoftKeyType BOTH = new SoftKeyType(2);

    private int type;

    private SoftKeyType(int type) {
        this.type = type;
    }

    int getType()
    {
        return type;
    }

    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.type;
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SoftKeyType other = (SoftKeyType) obj;
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

 

 }
