package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.entity.Order;
import kz.epam.tcfp.foodordering.entity.OrderDetail;
import kz.epam.tcfp.foodordering.entity.OrderStatus;
import kz.epam.tcfp.foodordering.logic.FoodLogic;
import kz.epam.tcfp.foodordering.logic.OrderDetailLogic;
import kz.epam.tcfp.foodordering.logic.OrderLogic;
import kz.epam.tcfp.foodordering.logic.OrderStatusLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowMyOrdersCommand implements ActionCommand {

    private static final String PATH_PAGE_MY_ORDERS = "path.page.my.orders";
    private static final String USER_ID = "userId";
    private static final String FOODS = "foods";
    private static final String ORDER_STATUSES = "orderStatuses";
    private static final String ORDERS = "orders";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_MY_ORDERS);
        List<OrderStatus> orderStatuses = OrderStatusLogic.getAll();
        List<Food> foodList = FoodLogic.getAllItems();
        long userId = (long) req.getSession().getAttribute(USER_ID);
        List<Order> orderList = OrderLogic.getAllOrdersByUserId(userId);
        Map<Order, List<OrderDetail>> orders = new HashMap<>();
        for (Order order : orderList) {
            List<OrderDetail> orderDetailsList = OrderDetailLogic.getAllByOrderId(order.getId());
            orders.put(order, orderDetailsList);
        }
        req.setAttribute(FOODS, foodList);
        req.setAttribute(ORDER_STATUSES, orderStatuses);
        req.setAttribute(ORDERS, orders);
        return page;
    }
}
