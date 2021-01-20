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
}
