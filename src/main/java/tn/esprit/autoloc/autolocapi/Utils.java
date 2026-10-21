package tn.esprit.autoloc.autolocapi;

public class Utils {

    private Utils() {}

    /**
     * Retourne true si la chaîne est non nulle et non vide (après trim).
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
