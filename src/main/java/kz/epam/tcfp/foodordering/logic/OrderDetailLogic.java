package kz.epam.tcfp.foodordering.logic;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.dao.EntityTransaction;
import kz.epam.tcfp.foodordering.dao.OrderDetailDao;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.entity.OrderDetail;

import java.util.List;
import java.util.Map;

public class OrderDetailLogic {

    private static final EntityTransaction transaction = new EntityTransaction();
    private static final OrderDetailDao orderDetailDao = new OrderDetailDao();

    private OrderDetailLogic() {
        throw new IllegalStateException("Logic utility class");
    }

    public static void addAll(Map<Food, Integer> orderDetails, long orderId) throws DaoException {
        for (Map.Entry<Food, Integer> entry : orderDetails.entrySet()) {
            Food food = entry.getKey();
            int quantity = entry.getValue();
            OrderDetail orderDetail = new OrderDetail(orderId, food.getId(), food.getPrice() * quantity, quantity);
            transaction.initTransaction(orderDetailDao);
            try {
                orderDetailDao.create(orderDetail);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                throw new DaoException(e);
            } finally {
                transaction.endTransaction();
            }
        }
    }

    public static List<OrderDetail> getAllByOrderId(long id) throws DaoException {
        List<OrderDetail> orderDetails;
        transaction.init(orderDetailDao);
        try {
            orderDetails = orderDetailDao.findAllByOrderId(id);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return orderDetails;
    }
}
