package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.*;
import kz.epam.tcfp.foodordering.logic.*;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_ALL_ORDERS;
import static kz.epam.tcfp.foodordering.util.ValueConstants.SORT_BY_DATE;

public class SortAllOrdersCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_ALL_ORDERS);
        String sortBy = req.getParameter(PARAM_NAME_SORT_BY);
        List<User> users = ProfileLogic.getAllCustomers();
        List<OrderStatus> orderStatuses = OrderStatusLogic.getAll();
        List<Food> foodList = FoodLogic.getAllItems();
        List<Order> orderList = OrderLogic.getAll();
        Map<Order, List<OrderDetail>> orders = new TreeMap<>();
        if (SORT_BY_DATE.equalsIgnoreCase(sortBy)) {
            orders = new TreeMap<>((o1, o2) -> {
                int res =  o1.getDate().compareTo(o2.getDate());
                if (res == 0) {
                    res = (int) (o1.getPrice() - o2.getPrice());
                    if (res == 0) {
                        res = (int) (o1.getId() - o2.getId());
                    }
                }
                return res;
            });
        }
        for (Order order : orderList) {
            List<OrderDetail> orderDetailsList = OrderDetailLogic.getAllByOrderId(order.getId());
            orders.put(order, orderDetailsList);
        }
        req.setAttribute(USERS, users);
        req.setAttribute(FOODS, foodList);
        req.setAttribute(ORDER_STATUSES, orderStatuses);
        req.setAttribute(ORDERS, orders);
        return page;
    }
}
