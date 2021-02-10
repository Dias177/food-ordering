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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SortAllOrdersCommand implements ActionCommand {

    private static final String PATH_PAGE_ALL_ORDERS = "path.page.all.orders";
    private static final String PARAM_NAME_SORT_BY = "sort_by";
    private static final String FOODS = "foods";
    private static final String ORDER_STATUSES = "orderStatuses";
    private static final String ORDERS = "orders";
    private static final String SORT_BY_DATE = "date";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_ALL_ORDERS);
        String sortBy = req.getParameter(PARAM_NAME_SORT_BY);
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
        req.setAttribute(FOODS, foodList);
        req.setAttribute(ORDER_STATUSES, orderStatuses);
        req.setAttribute(ORDERS, orders);
        return page;
    }
}
