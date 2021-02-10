package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.logic.OrderDetailLogic;
import kz.epam.tcfp.foodordering.logic.OrderLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrderFoodCommand implements ActionCommand {

    private static final String PATH_PAGE_CART = "path.page.cart";
    private static final String CART = "cart";
    private static final String USER_ID = "userId";
    private static final long DEFAULT_ORDER_STATUS_ID = 1;
    private static final int MIN_FOOD_QUANTITY = 1;
    private static final String PARAM_NAME_FOOD_QUANTITY = "foodQuantity";
    private static final String IS_ERROR_INVALID_ORDER_FOOD_QUANTITY = "isErrorInvalidOrderFoodQuantity";
    private static final String IS_SUCCESS_ORDER_FOOD = "isSuccessOrderFood";
    private static final boolean ERROR = false;
    private static final boolean SUCCESS = true;

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_CART);
        Set<Food> cart = (Set<Food>) req.getSession().getAttribute(CART);
        boolean isValidForm = true;
        long userId = (long) req.getSession().getAttribute(USER_ID);
        Map<Food, Integer> orderDetails = new HashMap<>();
        double totalPrice = 0;
        int i = 1;
        for (Food food : cart) {
            int foodQuantity = Integer.parseInt(req.getParameter(PARAM_NAME_FOOD_QUANTITY + i));
            if (foodQuantity < MIN_FOOD_QUANTITY) {
                isValidForm = false;
                break;
            }
            totalPrice += food.getPrice() * foodQuantity;
            orderDetails.put(food, foodQuantity);
            i++;
        }
        if (isValidForm) {
            OrderLogic.add(userId, DEFAULT_ORDER_STATUS_ID, totalPrice);
            long orderId = OrderLogic.getLastOrderId();
            OrderDetailLogic.addAll(orderDetails, orderId);
            req.getSession().setAttribute(CART, new HashSet<Food>());
            req.setAttribute(IS_SUCCESS_ORDER_FOOD, SUCCESS);
        } else {
            req.setAttribute(IS_ERROR_INVALID_ORDER_FOOD_QUANTITY, ERROR);
        }
        return page;
    }
}
