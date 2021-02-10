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

public class ShowMenuItemsByCategoryCommand implements ActionCommand {

    private static final String PARAM_NAME_FOOD_CATEGORY_ID = "food_category_id";
    private static final String PATH_PAGE_MENU = "path.page.menu";
    private static final String FOOD_ITEMS = "foodItems";
    private static final String FOOD_CATEGORIES = "foodCategories";
    private static final String CURRENT_CATEGORY = "currentCategory";
    private static final String ALL = "All";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_MENU);
        long foodCategoryId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_CATEGORY_ID));
        List<Food> foodList;
        List<FoodCategory> foodCategories = FoodCategoryLogic.getAll();
        if (foodCategoryId == 0) {
            req.setAttribute(CURRENT_CATEGORY, ALL);
            foodList = FoodLogic.getAllItems();
        } else {
            req.setAttribute(CURRENT_CATEGORY, FoodCategoryLogic.getCategoryById(foodCategoryId));
            foodList = FoodLogic.getFoodByCategory(foodCategoryId);
        }
        req.setAttribute(FOOD_ITEMS, foodList);
        req.setAttribute(FOOD_CATEGORIES, foodCategories);
        return page;
    }
}
