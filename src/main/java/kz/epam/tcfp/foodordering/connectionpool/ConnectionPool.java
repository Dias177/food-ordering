package kz.epam.tcfp.foodordering.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {

    INSTANCE;

    private static final Logger logger = LogManager.getLogger();
    private final String driverName;
    private final String url;
    private final String user;
    private final String password;
    private int poolSize;
    private final BlockingQueue<ProxyConnection> freeConnections;

    ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        url = dbResourceManager.getValue(DBParameter.DB_URL);
        user = dbResourceManager.getValue(DBParameter.USER);
        password = dbResourceManager.getValue(DBParameter.PASSWORD);
        try {
            poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.POOL_SIZE));
        } catch (NumberFormatException e) {
            poolSize = 32;
        }
        freeConnections = new LinkedBlockingDeque<>(poolSize);
    }

    public void initPoolData() throws ConnectionPoolException {
        try {
            Class.forName(driverName);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            }
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Cannot register database driver", e);
        } catch (SQLException e) {
            throw new ConnectionPoolException("SQLException in Connection Pool", e);
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = freeConnections.take();
        } catch (InterruptedException e) {
            logger.error("Error connection to the data source. Current thread is interrupted", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (!(connection instanceof ProxyConnection)) {
            throw new ClassCastException("Not ProxyConnection was passed");
        }
        freeConnections.offer((ProxyConnection) connection);
    }

    public void destroyPool() {
        for (int i = 0; i < poolSize; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException e) {
                logger.error("Error in destroying connection pool", e);
            } catch (InterruptedException e) {
                logger.error("Error in destroying connection pool. Current thread is interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }

    public void deregisterDrivers() {
        while (DriverManager.getDrivers().hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
            } catch (SQLException e) {
                logger.error("Error in deregistering drivers from connection pool", e);
            }
        }
    }
}
