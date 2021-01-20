package kz.epam.tcfp.foodordering.util;

import java.util.ResourceBundle;

public class MessageManager {

    private static final String PATH_MESSAGES_PROPERTIES = "messages";
    private static final ResourceBundle bundle = ResourceBundle.getBundle(PATH_MESSAGES_PROPERTIES);

    private MessageManager() {
        throw new IllegalStateException("Utility class");
    }
    public static String getProperty(String key) {
        return bundle.getString(key);
    }
}
