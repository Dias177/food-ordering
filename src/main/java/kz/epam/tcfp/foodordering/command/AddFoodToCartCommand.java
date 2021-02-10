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

public class AddFoodToCartCommand implements ActionCommand {

    private static final String PARAM_NAME_FOOD_ID = "food_id";
    private static final String CART = "cart";
    private static final String PATH_PAGE_MENU = "path.page.menu";
    private static final String FOOD_ITEMS = "foodItems";
    private static final String FOOD_CATEGORIES = "foodCategories";
    private static final String CURRENT_CATEGORY = "currentCategory";
    private static final String ALL = "All";
    private static final String IS_SUCCESS_ADD_FOOD_TO_CART = "isSuccessAddFoodToCart";
    private static final boolean SUCCESS = true;

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_MENU);
        String id = req.getParameter(PARAM_NAME_FOOD_ID);
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
