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

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_CART;
import static kz.epam.tcfp.foodordering.util.ValueConstants.*;

public class OrderFoodCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_CART);
        Set<Food> cart = (Set<Food>) req.getSession().getAttribute(CART);
        boolean isFormValid = true;
        long userId = (long) req.getSession().getAttribute(USER_ID);
        Map<Food, Integer> orderDetails = new HashMap<>();
        double totalPrice = 0;
        int i = 1;
        for (Food food : cart) {
            int foodQuantity = Integer.parseInt(req.getParameter(PARAM_NAME_FOOD_QUANTITY + i));
            if (foodQuantity < MIN_FOOD_QUANTITY || food.getPrice() < MIN_FOOD_PRICE) {
                isFormValid = false;
                break;
            }
            totalPrice += food.getPrice() * foodQuantity;
            orderDetails.put(food, foodQuantity);
            i++;
        }
        if (isFormValid) {
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
