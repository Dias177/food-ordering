package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.logic.OrderDetailLogic;
import kz.epam.tcfp.foodordering.logic.OrderLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;
import kz.epam.tcfp.foodordering.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrderFoodCommand implements ActionCommand {

    private static final String PATH_PAGE_MAIN = "path.page.main";
    private static final String PATH_PAGE_CART = "path.path.cart";
    private static final String CART = "cart";
    private static final String USER_ID = "userId";
    private static final long DEFAULT_ORDER_STATUS_ID = 1;
    private static final String PARAM_NAME_FOOD_QUANTITY = "foodQuantity";
    private static final String ERROR_INVALID_ORDER_FOOD_QUANTITY = "errorInvalidOrderFoodQuantity";
    private static final String MESSAGE_ORDER_FOOD_QUANTITY_ERROR = "message.order.food.quantity.error";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page;
        Set<Food> cart = (Set<Food>) req.getSession().getAttribute(CART);
        boolean isValidForm = true;
        long userId = (long) req.getSession().getAttribute(USER_ID);
        Map<Food, Integer> orderDetails = new HashMap<>();
        double totalPrice = 0;
        int i = 1;
        for (Food food : cart) {
            int foodQuantity = Integer.parseInt(req.getParameter(PARAM_NAME_FOOD_QUANTITY + i));
            if (foodQuantity < 1) {
                isValidForm = false;
                break;
            }
            totalPrice += food.getPrice() * foodQuantity;
            orderDetails.put(food, foodQuantity);
            i++;
        }
        /*
        insert order in db
        get id of that order from db
        insert corresponding order details in db
        make cart empty
         */
        if (isValidForm) {
            OrderLogic.add(userId, DEFAULT_ORDER_STATUS_ID, totalPrice);
            long orderId = OrderLogic.getLastOrderId();
            OrderDetailLogic.addAll(orderDetails, orderId);
            req.getSession().setAttribute(CART, new HashSet<Food>());
            page = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
        } else {
            page = ConfigurationManager.getProperty(PATH_PAGE_CART);
            req.setAttribute(ERROR_INVALID_ORDER_FOOD_QUANTITY, MessageManager.getProperty(MESSAGE_ORDER_FOOD_QUANTITY_ERROR));
        }
        return page;
    }
}
