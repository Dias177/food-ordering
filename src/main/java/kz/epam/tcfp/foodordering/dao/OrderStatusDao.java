package kz.epam.tcfp.foodordering.dao;

import kz.epam.tcfp.foodordering.entity.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusDao extends AbstractDao<Long, OrderStatus> {

    private static final String COLUMN_LABEL_ID = "id";
    private static final String COLUMN_LABEL_NAME = "name";
    private static final String SQL_SELECT_ALL = "SELECT * FROM order_status ORDER BY id";
    private static final String SQL_SELECT_ORDER_STATUS_BY_NAME = "SELECT * FROM order_status WHERE name = ?";
    private static final String SQL_CREATE_ORDER_STATUS = "INSERT INTO order_status (name) VALUES (?)";
    private static final String SQL_DELETE_ORDER_STATUS_BY_ID = "DELETE FROM order_status WHERE id = ?";
    private static final String SQL_SELECT_ORDER_STATUS_BY_ID = "SELECT * FROM order_status WHERE id = ?";
    private static final String SQL_UPDATE_ORDER_STATUS = "UPDATE order_status SET name = ? WHERE id = ?";

    public OrderStatus findByName(String name) throws DaoException {
        OrderStatus orderStatus = new OrderStatus();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ORDER_STATUS_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                orderStatus = new OrderStatus(resultSet.getString(COLUMN_LABEL_NAME));
                orderStatus.setId(resultSet.getLong(COLUMN_LABEL_ID));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding order status by name", e);
        } finally {
            close(statement);
        }
        return orderStatus;
    }

    @Override
    public List<OrderStatus> findAll() throws DaoException {
        List<OrderStatus> orderStatuses = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                OrderStatus orderStatus = new OrderStatus(resultSet.getString(COLUMN_LABEL_NAME));
                orderStatus.setId(resultSet.getLong(COLUMN_LABEL_ID));
                orderStatuses.add(orderStatus);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding all order statuses", e);
        } finally {
            close(statement);
        }
        return orderStatuses;
    }

    @Override
    public OrderStatus findEntityById(Long id) throws DaoException {
        OrderStatus orderStatus = new OrderStatus();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ORDER_STATUS_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                orderStatus = new OrderStatus(resultSet.getString(COLUMN_LABEL_NAME));
                orderStatus.setId(resultSet.getLong(COLUMN_LABEL_ID));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding order status by id", e);
        } finally {
            close(statement);
        }
        return orderStatus;
    }

    @Override
    public boolean deleteEntityById(Long id) throws DaoException {
        int rows;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_ORDER_STATUS_BY_ID);
            statement.setLong(1, id);
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in deleting order status by id", e);
        } finally {
            close(statement);
        }
        return rows > 0;
    }

    @Override
    public boolean deleteEntity(OrderStatus orderStatus) throws DaoException {
        int rows;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_ORDER_STATUS_BY_ID);
            long id = orderStatus.getId();
            statement.setLong(1, id);
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in deleting order status", e);
        } finally {
            close(statement);
        }
        return rows > 0;
    }

    @Override
    public boolean create(OrderStatus orderStatus) throws DaoException {
        OrderStatus os = findByName(orderStatus.getName());
        if (os.getName() != null && os.getId() != 0) {
            return false;
        }
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_ORDER_STATUS);
            statement.setString(1, orderStatus.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in creating order status", e);
        } finally {
            close(statement);
        }
        return true;
    }

    @Override
    public int update(OrderStatus orderStatus) throws DaoException {
        int rows;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_ORDER_STATUS);
            statement.setString(1, orderStatus.getName());
            statement.setLong(2, orderStatus.getId());
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in updating order status", e);
        } finally {
            close(statement);
        }
        return rows;
    }
}
