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

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_MENU;
import static kz.epam.tcfp.foodordering.util.ValueConstants.*;

public class SortMenuItemsCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_MENU);
        String sortBy = req.getParameter(PARAM_NAME_SORT_BY);
        long foodCategoryId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_CATEGORY_ID_UNDERSCORE));
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
