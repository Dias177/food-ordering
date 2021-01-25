package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.logic.FoodLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Set;

public class RemoveFoodFromCartCommand implements ActionCommand {

    private static final String PARAM_NAME_FOOD_ID = "food_id";
    private static final String PATH_PAGE_CART = "path.page.cart";
    private static final String CART = "cart";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_CART);
        long foodId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_ID));
        Food foodToRemove = FoodLogic.getFood(foodId);
        Set<Food> cart = (Set<Food>) req.getSession().getAttribute(CART);
        cart.remove(foodToRemove);
        req.setAttribute(CART, cart);
        return page;
    }
}
