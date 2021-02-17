package kz.epam.tcfp.foodordering.dao;

import kz.epam.tcfp.foodordering.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AbstractDao<Long, Order> {

    private static final String COLUMN_LABEL_ID = "id";
    private static final String COLUMN_LABEL_USER_ID = "user_id";
    private static final String COLUMN_LABEL_ORDER_STATUS_ID = "order_status_id";
    private static final String COLUMN_LABEL_PRICE = "price";
    private static final String COLUMN_LABEL_DATE = "date";
    private static final String SQL_CREATE_ORDER = "INSERT INTO `order` (user_id, order_status_id, price, date) " +
            "VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL_ORDERS_BY_USER_ID = "SELECT * FROM `order` WHERE user_id = ?";
    private static final String SQL_SELECT_LAST_ORDER = "SELECT * FROM `order` ORDER BY id DESC LIMIT 1";
    private static final String SQL_SELECT_ALL = "SELECT * FROM `order` ORDER BY id";
    private static final String SQL_SELECT_ORDER_BY_ID = "SELECT * FROM `order` WHERE id = ?";
    private static final String SQL_UPDATE_ORDER_STATUS_ID = "UPDATE `order` SET order_status_id = ?, " +
            "price = ? WHERE id = ?";
    private static final String SQL_DELETE_ORDER_BY_ID = "DELETE FROM `order` WHERE id = ?";

    public List<Order> findAllOrdersByUserId(long id) throws DaoException {
        List<Order> orders = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_ORDERS_BY_USER_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order(resultSet.getLong(COLUMN_LABEL_USER_ID),
                        resultSet.getLong(COLUMN_LABEL_ORDER_STATUS_ID), resultSet.getDouble(COLUMN_LABEL_PRICE),
                        resultSet.getTimestamp(COLUMN_LABEL_DATE));
                order.setId(resultSet.getLong(COLUMN_LABEL_ID));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding all orders by user id", e);
        } finally {
            close(statement);
        }
        return orders;
    }

    public Order findLastOrder() throws DaoException {
        Order order = new Order();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_LAST_ORDER);
            if (resultSet.next()) {
                order.setId(resultSet.getLong(COLUMN_LABEL_ID));
                order.setUserId(resultSet.getLong(COLUMN_LABEL_USER_ID));
                order.setOrderStatusId(resultSet.getLong(COLUMN_LABEL_ORDER_STATUS_ID));
                order.setPrice(resultSet.getDouble(COLUMN_LABEL_PRICE));
                order.setDate(resultSet.getTimestamp(COLUMN_LABEL_DATE));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding last order", e);
        } finally {
            close(statement);
        }
        return order;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orders = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                Order order = new Order(resultSet.getLong(COLUMN_LABEL_USER_ID),
                        resultSet.getLong(COLUMN_LABEL_ORDER_STATUS_ID), resultSet.getDouble(COLUMN_LABEL_PRICE),
                        resultSet.getTimestamp(COLUMN_LABEL_DATE));
                order.setId(resultSet.getLong(COLUMN_LABEL_ID));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding all orders", e);
        } finally {
            close(statement);
        }
        return orders;
    }

    @Override
    public Order findEntityById(Long id) throws DaoException {
        Order order = new Order();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = new Order(resultSet.getLong(COLUMN_LABEL_USER_ID),
                        resultSet.getLong(COLUMN_LABEL_ORDER_STATUS_ID), resultSet.getDouble(COLUMN_LABEL_PRICE),
                        resultSet.getTimestamp(COLUMN_LABEL_DATE));
                order.setId(resultSet.getLong(COLUMN_LABEL_ID));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding order by id", e);
        } finally {
            close(statement);
        }
        return order;
    }

    @Override
    public boolean deleteEntityById(Long id) throws DaoException {
        int rows;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_ORDER_BY_ID);
            statement.setLong(1, id);
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in deleting order by id", e);
        } finally {
            close(statement);
        }
        return rows > 0;
    }

    @Override
    public boolean deleteEntity(Order order) throws DaoException {
        int rows;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_ORDER_BY_ID);
            long id = order.getId();
            statement.setLong(1, id);
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in deleting order", e);
        } finally {
            close(statement);
        }
        return rows > 0;
    }

    @Override
    public boolean create(Order order) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_ORDER);
            statement.setLong(1, order.getUserId());
            statement.setLong(2, order.getOrderStatusId());
            statement.setDouble(3, order.getPrice());
            statement.setTimestamp(4, order.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in creating order", e);
        } finally {
            close(statement);
        }
        return true;
    }

    @Override
    public int update(Order order) throws DaoException {
        PreparedStatement statement = null;
        int affectedRows ;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_ORDER_STATUS_ID);
            statement.setLong(1, order.getOrderStatusId());
            statement.setDouble(2, order.getPrice());
            statement.setLong(3, order.getId());
            affectedRows =  statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in updating ordering status of order", e);
        } finally {
            close(statement);
        }
        return affectedRows;
    }
}
