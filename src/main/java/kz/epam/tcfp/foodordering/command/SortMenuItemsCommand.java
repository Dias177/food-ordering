package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.entity.FoodCategory;
import kz.epam.tcfp.foodordering.logic.FoodCategoryLogic;
import kz.epam.tcfp.foodordering.logic.FoodLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortMenuItemsCommand implements ActionCommand {

    private static final String PATH_PAGE_MENU = "path.page.menu";
    private static final String FOOD_ITEMS = "foodItems";
    private static final String PARAM_NAME_SORT_BY = "sort_by";
    private static final String PARAM_NAME_FOOD_CATEGORY_ID = "food_category_id";
    private static final String SORT_BY_NAME = "name";
    private static final String SORT_BY_PRICE = "price";
    private static final String FOOD_CATEGORIES = "foodCategories";
    private static final String CURRENT_CATEGORY = "currentCategory";
    private static final String ALL = "All";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_MENU);
        String sortBy = req.getParameter(PARAM_NAME_SORT_BY);
        long foodCategoryId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_CATEGORY_ID));
        List<FoodCategory> foodCategories = FoodCategoryLogic.getAll();
        List<Food> foodItems;
        if (foodCategoryId == 0) {
            foodItems = FoodLogic.getAllItems();
            req.setAttribute(CURRENT_CATEGORY, ALL);
        } else {
            foodItems = FoodLogic.getFoodByCategory(foodCategoryId);
            req.setAttribute(CURRENT_CATEGORY, FoodCategoryLogic.getCategoryById(foodCategoryId));
        }
        if (SORT_BY_NAME.equalsIgnoreCase(sortBy)) {
            foodItems.sort(Comparator.comparing(Food::getName));
        } else if (SORT_BY_PRICE.equalsIgnoreCase(sortBy)) {
            Collections.sort(foodItems);
        }
        req.setAttribute(FOOD_CATEGORIES, foodCategories);
        req.setAttribute(FOOD_ITEMS, foodItems);
        return page;
    }
}
