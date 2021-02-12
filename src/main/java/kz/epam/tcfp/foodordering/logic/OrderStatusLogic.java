package kz.epam.tcfp.foodordering.logic;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.dao.EntityTransaction;
import kz.epam.tcfp.foodordering.dao.OrderStatusDao;
import kz.epam.tcfp.foodordering.entity.OrderStatus;

import java.util.List;

public class OrderStatusLogic {

    private static final OrderStatusDao orderStatusDao = new OrderStatusDao();
    private static final EntityTransaction transaction = new EntityTransaction();

    private OrderStatusLogic() {
        throw new IllegalStateException("Logic utility class");
    }

    public static List<OrderStatus> getAll() throws DaoException {
        List<OrderStatus> orderStatuses;
        transaction.init(orderStatusDao);
        try {
            orderStatuses = orderStatusDao.findAll();
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return orderStatuses;
    }

    public static boolean statusExists(String name) throws DaoException {
        OrderStatus orderStatus;
        transaction.init(orderStatusDao);
        try {
            orderStatus = orderStatusDao.findByName(name);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return orderStatus.getName() != null && orderStatus.getId() != 0;
    }

    public static void add(String name) throws DaoException {
        OrderStatus orderStatus = new OrderStatus(name);
        transaction.initTransaction(orderStatusDao);
        try {
            orderStatusDao.create(orderStatus);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new DaoException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    public static boolean remove(long id) throws DaoException {
        boolean isRemoved;
        transaction.initTransaction(orderStatusDao);
        try {
            isRemoved = orderStatusDao.deleteEntityById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new DaoException(e);
        } finally {
            transaction.endTransaction();
        }
        return isRemoved;
    }
}
