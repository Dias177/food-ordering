package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.*;
import kz.epam.tcfp.foodordering.logic.*;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_ALL_ORDERS;

public class ShowAllOrdersCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_ALL_ORDERS);
        List<OrderStatus> orderStatuses = OrderStatusLogic.getAll();
        List<Food> foodList = FoodLogic.getAllItems();
        List<Order> orderList = OrderLogic.getAll();
        Map<Order, List<OrderDetail>> orders = new HashMap<>();
        Set<User> users = new HashSet<>();
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
