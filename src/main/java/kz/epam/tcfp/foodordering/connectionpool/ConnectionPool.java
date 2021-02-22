package kz.epam.tcfp.foodordering.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static kz.epam.tcfp.foodordering.util.ValueConstants.DEFAULT_POOL_SIZE;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger();

    private static ConnectionPool instance;
    private final String driverName;
    private final String url;
    private final String user;
    private final String password;
    private int poolSize;
    private final BlockingQueue<ProxyConnection> freeConnections;

    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        url = dbResourceManager.getValue(DBParameter.DB_URL);
        user = dbResourceManager.getValue(DBParameter.USER);
        password = dbResourceManager.getValue(DBParameter.PASSWORD);
        try {
            poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.POOL_SIZE));
        } catch (NumberFormatException e) {
            poolSize = DEFAULT_POOL_SIZE;
        }
        freeConnections = new LinkedBlockingDeque<>(poolSize);
        try {
            initPoolData();
        } catch (ConnectionPoolException e) {
            logger.error("Error in initializing connection pool with data", e);
        }
    }

    private void initPoolData() throws ConnectionPoolException {
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

    public static ConnectionPool getInstance() {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPool();
                }
            }
        }
        return localInstance;
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
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

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                logger.error("Error in deregistering drivers from connection pool", e);
            }
        }
    }
}
