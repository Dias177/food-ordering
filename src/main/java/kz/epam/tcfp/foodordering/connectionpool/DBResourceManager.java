package kz.epam.tcfp.foodordering.connectionpool;

import java.util.ResourceBundle;

public class DBResourceManager {

    private static final DBResourceManager instance = new DBResourceManager();
    private static final String PATH_DATABASE_PROPERTIES = "database";
    private static final ResourceBundle bundle = ResourceBundle.getBundle(PATH_DATABASE_PROPERTIES);

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
