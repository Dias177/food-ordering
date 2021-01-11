package kz.epam.tcfp.foodordering.dao;

import kz.epam.tcfp.foodordering.connectionpool.ConnectionPool;
import kz.epam.tcfp.foodordering.connectionpool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class EntityTransaction {

    private static final Logger logger = LogManager.getLogger();
    private ProxyConnection connection;

    public void initTransaction(AbstractDao dao, AbstractDao... daos) {
        if (connection == null) {
            connection = ConnectionPool.getInstance().getConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Error in initializing transaction", e);
        }
        dao.setConnection(connection);
        for (AbstractDao daoElement : daos) {
            daoElement.setConnection(connection);
        }
    }

    public void endTransaction() {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error("Error in ending transaction", e);
            }
            ConnectionPool.getInstance().releaseConnection(connection);
            connection = null;
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error("Error in committing transaction", e);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error("Error in rollback transaction", e);
        }
    }

    public void init(AbstractDao dao) {
        if (connection == null) {
            ConnectionPool.getInstance().getConnection();
        }
        dao.setConnection(connection);
    }

    public void end() {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
            connection = null;
        }
    }
}
