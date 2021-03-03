package kz.epam.tcfp.foodordering.util;

public class ValueConstants {
    public static final double MIN_FOOD_PRICE = 0;
    public static final long DEFAULT_ORDER_STATUS_ID = 1;
    public static final int MIN_FOOD_QUANTITY = 1;

    public static final int DEFAULT_POOL_SIZE = 32;
    public static final long CUSTOMER_ROLE_ID = 2;
    public static final String CUSTOMER_ROLE_NAME = "Customer";

    public static final boolean SUCCESS = true;
    public static final boolean ERROR = true;
    public static final boolean TRUE = true;

    public static final String LOCALE_LANGUAGE_RU = "ru";
    public static final String LOCALE_COUNTRY_RU = "RU";

    public static final String ALL = "All";

    public static final String SORT_BY_DATE = "date";
    public static final String SORT_BY_NAME = "name";
    public static final String SORT_BY_PRICE = "price";
    public static final String SORT_BY_BIRTHDAY = "birthday";

    private ValueConstants() {
        throw new IllegalStateException("Utility class");
    }
}
