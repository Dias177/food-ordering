package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.entity.FoodCategory;
import kz.epam.tcfp.foodordering.logic.FoodCategoryLogic;
import kz.epam.tcfp.foodordering.logic.FoodLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_MENU;
import static kz.epam.tcfp.foodordering.util.ValueConstants.ALL;
import static kz.epam.tcfp.foodordering.util.ValueConstants.SUCCESS;

public class AddFoodToCartCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_MENU);
        String id = req.getParameter(PARAM_NAME_FOOD_ID_UNDERSCORE);
        long idLong = Long.parseLong(id);
        Food food = FoodLogic.getFood(idLong);
        Set<Food> cart = (Set<Food>) req.getSession().getAttribute(CART);
        cart.add(food);
        List<Food> foodItems = FoodLogic.getAllItems();
        List<FoodCategory> foodCategories = FoodCategoryLogic.getAll();
        req.setAttribute(CURRENT_CATEGORY, ALL);
        req.setAttribute(FOOD_ITEMS, foodItems);
        req.setAttribute(FOOD_CATEGORIES, foodCategories);
        req.setAttribute(IS_SUCCESS_ADD_FOOD_TO_CART, SUCCESS);
        req.getSession().setAttribute(CART, cart);
        return page;
    }
}
