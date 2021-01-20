package kz.epam.tcfp.foodordering.util;

import java.util.ResourceBundle;

public class RegexManager {

    private static final String PATH_REGEX_PROPERTIES = "regex";
    private static final ResourceBundle bundle = ResourceBundle.getBundle(PATH_REGEX_PROPERTIES);

    private RegexManager() {
        throw new IllegalStateException("Utility class");
    }

    public static String getProperty(String key) {
        return bundle.getString(key);
    }
}
