package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.*;
import kz.epam.tcfp.foodordering.logic.*;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowAllOrdersCommand implements ActionCommand {

    private static final String PATH_PAGE_ALL_ORDERS = "path.page.all.orders";
    private static final String FOODS = "foods";
    private static final String ORDER_STATUSES = "orderStatuses";
    private static final String ORDERS = "orders";
    private static final String USERS = "users";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_ALL_ORDERS);
        List<OrderStatus> orderStatuses = OrderStatusLogic.getAll();
        List<Food> foodList = FoodLogic.getAllItems();
        List<Order> orderList = OrderLogic.getAll();
        Map<Order, List<OrderDetail>> orders = new HashMap<>();
        List<User> users = new ArrayList<>();
        for (Order order : orderList) {
            List<OrderDetail> orderDetailsList = OrderDetailLogic.getAllByOrderId(order.getId());
            users.add(ProfileLogic.getUserById(order.getUserId()));
            orders.put(order, orderDetailsList);
        }
        req.setAttribute(USERS, users);
        req.setAttribute(FOODS, foodList);
        req.setAttribute(ORDER_STATUSES, orderStatuses);
        req.setAttribute(ORDERS, orders);
        return page;
    }
}
