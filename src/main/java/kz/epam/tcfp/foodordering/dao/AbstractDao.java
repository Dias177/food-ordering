package kz.epam.tcfp.foodordering.dao;

import kz.epam.tcfp.foodordering.connectionpool.ProxyConnection;
import kz.epam.tcfp.foodordering.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDao<K, T extends Entity> {

    protected static final Logger logger = LogManager.getLogger();
    protected ProxyConnection connection;

    protected AbstractDao() {}

    public abstract List<T> findAll() throws DaoException;
    public abstract T findEntityById(K id) throws DaoException;
    public abstract boolean deleteEntityById(K id) throws DaoException;
    public abstract boolean deleteEntity(T t) throws DaoException;
    public abstract boolean create(T t) throws DaoException;
    public abstract int update(T t) throws DaoException;

    public void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Error in closing statement", e);
            }
        }
    }

    public void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }
}