package kz.epam.tcfp.foodordering.logic;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.dao.EntityTransaction;
import kz.epam.tcfp.foodordering.dao.OrderDao;
import kz.epam.tcfp.foodordering.entity.Order;

import java.sql.Timestamp;
import java.util.List;

public class OrderLogic {

    private static final OrderDao orderDao = new OrderDao();
    private static final EntityTransaction transaction = new EntityTransaction();

    private OrderLogic() {
        throw new IllegalStateException("Logic utility class");
    }

    public static void add(long userId, long orderStatusId, double price) throws DaoException {
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderStatusId(orderStatusId);
        order.setPrice(price);
        order.setDate(new Timestamp(System.currentTimeMillis()));
        transaction.initTransaction(orderDao);
        try {
            orderDao.create(order);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new DaoException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    public static long getLastOrderId() throws DaoException {
        Order order;
        transaction.init(orderDao);
        try {
            order = orderDao.findLastOrder();
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return order.getId();
    }

    public static List<Order> getAllOrdersByUserId(long id) throws DaoException {
        List<Order> orders;
        transaction.init(orderDao);
        try {
            orders = orderDao.findAllOrdersByUserId(id);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return orders;
    }

    public static List<Order> getAll() throws DaoException {
        List<Order> orders;
        transaction.init(orderDao);
        try {
            orders = orderDao.findAll();
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return orders;
    }

    public static Order getOrderById(long id) throws DaoException {
        Order order;
        transaction.init(orderDao);
        try {
            order = orderDao.findEntityById(id);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return order;
    }

    public static boolean updateStatus(long orderId, long orderStatusId) throws DaoException {
        Order orderToUpdate = getOrderById(orderId);
        if (orderToUpdate.getOrderStatusId() == orderStatusId) {
            return false;
        }
        orderToUpdate.setOrderStatusId(orderStatusId);
        int affectedRows;
        transaction.initTransaction(orderDao);
        try {
            affectedRows = orderDao.update(orderToUpdate);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new DaoException(e);
        } finally {
            transaction.endTransaction();
        }
        return affectedRows > 0;
    }
}
