package com.j2memetronome.i18n;

/**
 *
 * @author Deivid Martins
 */
public class Language {

    public static final Language ENGLISH = new Language("en");
    public static final Language PORTUGUESE = new Language("pt");

    private Language(String locale) {
        this.locale = locale;
    }
    private static Language current;
    private String locale;

    public static void setLanguage(Language language) {
        current = language;
    }

    public static Language current() {
        return current;
    }

    public static void next()
    {
        if(current.equals(ENGLISH))
        {
            current = PORTUGUESE;
        }
    }

    public static void previous()
    {
        if(current.equals(PORTUGUESE))
            current = ENGLISH;

    }



    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Language other = (Language) obj;
        if ((this.locale == null) ? (other.locale != null) : !this.locale.equals(other.locale)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.locale != null ? this.locale.hashCode() : 0);
        return hash;
    }


}
