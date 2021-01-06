package kz.epam.tcfp.foodordering.connectionpool;

public class ConnectionPoolException extends Exception {

    public ConnectionPoolException(String msg, Exception e) {
        super(msg, e);
    }
}
