package kz.epam.tcfp.foodordering.util;

import java.util.ResourceBundle;

public class ConfigurationManager {

    private static final String PATH_CONFIG_PROPERTIES = "config";
    private static final ResourceBundle bundle = ResourceBundle.getBundle(PATH_CONFIG_PROPERTIES);

    private ConfigurationManager() {
        throw new IllegalStateException("Utility class");
    }

    public static String getProperty(String key) {
        return bundle.getString(key);
    }
}
