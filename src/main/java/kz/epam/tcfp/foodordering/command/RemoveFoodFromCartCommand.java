package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.logic.FoodLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Set;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.CART;
import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.PARAM_NAME_FOOD_ID_UNDERSCORE;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_CART;

public class RemoveFoodFromCartCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_CART);
        long foodId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_ID_UNDERSCORE));
        Food foodToRemove = FoodLogic.getFood(foodId);
        Set<Food> cart = (Set<Food>) req.getSession().getAttribute(CART);
        cart.remove(foodToRemove);
        req.setAttribute(CART, cart);
        return page;
    }
}
