package kz.epam.tcfp.foodordering.dao;

import kz.epam.tcfp.foodordering.entity.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusDao extends AbstractDao<Long, OrderStatus> {

    private static final String SQL_SELECT_ALL = "SELECT * FROM order_status ORDER BY id";
    private static final String COLUMN_LABEL_ID = "id";
    private static final String COLUMN_LABEL_NAME = "name";

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
        return null;
    }

    @Override
    public boolean deleteEntityById(Long id) throws DaoException {
        return false;
    }

    @Override
    public boolean deleteEntity(OrderStatus orderStatus) throws DaoException {
        return false;
    }

    @Override
    public boolean create(OrderStatus orderStatus) throws DaoException {
        return false;
    }

    @Override
    public int update(OrderStatus orderStatus) throws DaoException {
        return 0;
    }
}
