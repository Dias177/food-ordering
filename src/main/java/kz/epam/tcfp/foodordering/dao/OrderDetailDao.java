package kz.epam.tcfp.foodordering.dao;

import kz.epam.tcfp.foodordering.entity.OrderDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDao extends AbstractDao<Long, OrderDetail> {

    private static final String SQL_CREATE_ORDER_DETAIL = "INSERT INTO order_detail (order_id, food_id, price, " +
            "quantity) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL_ORDER_DETAILS_BY_ORDER_ID = "SELECT * FROM order_detail " +
            "WHERE ORDER_ID = ?";
    private static final String SQL_SELECT_ALL_ORDER_DETAILS = "SELECT * FROM order_detail ORDER BY id";
    private static final String COLUMN_LABEL_ID = "id";
    private static final String COLUMN_LABEL_ORDER_ID = "order_id";
    private static final String COLUMN_LABEL_FOOD_ID = "food_id";
    private static final String COLUMN_LABEL_PRICE = "price";
    private static final String COLUMN_LABEL_QUANTITY = "quantity";

    public List<OrderDetail> findAllByOrderId(long id) throws DaoException{
        List<OrderDetail> orderDetails = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_ORDER_DETAILS_BY_ORDER_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail(resultSet.getLong(COLUMN_LABEL_ORDER_ID),
                        resultSet.getLong(COLUMN_LABEL_FOOD_ID), resultSet.getDouble(COLUMN_LABEL_PRICE),
                        resultSet.getInt(COLUMN_LABEL_QUANTITY));
                orderDetail.setId(resultSet.getLong(COLUMN_LABEL_ID));
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding all order details by order id", e);
        } finally {
            close(statement);
        }
        return orderDetails;
    }

    @Override
    public List<OrderDetail> findAll() throws DaoException {
        List<OrderDetail> orderDetails = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ORDER_DETAILS);
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail(resultSet.getLong(COLUMN_LABEL_ORDER_ID),
                        resultSet.getLong(COLUMN_LABEL_FOOD_ID), resultSet.getDouble(COLUMN_LABEL_PRICE),
                        resultSet.getInt(COLUMN_LABEL_QUANTITY));
                orderDetail.setId(resultSet.getLong(COLUMN_LABEL_ID));
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding all order details", e);
        } finally {
            close(statement);
        }
        return orderDetails;
    }

    @Override
    public OrderDetail findEntityById(Long id) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteEntityById(Long id) throws DaoException {
        return false;
    }

    @Override
    public boolean deleteEntity(OrderDetail orderDetail) throws DaoException {
        return false;
    }

    @Override
    public boolean create(OrderDetail orderDetail) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_ORDER_DETAIL);
            statement.setLong(1, orderDetail.getOrderId());
            statement.setLong(2, orderDetail.getFoodId());
            statement.setDouble(3, orderDetail.getPrice());
            statement.setInt(4, orderDetail.getQuantity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in creating order detail", e);
        } finally {
            close(statement);
        }

        return true;
    }

    @Override
    public int update(OrderDetail orderDetail) throws DaoException {
        return 0;
    }
}
