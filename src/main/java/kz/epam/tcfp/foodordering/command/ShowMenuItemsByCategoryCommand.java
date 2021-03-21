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

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_MENU;
import static kz.epam.tcfp.foodordering.util.ValueConstants.ALL;
import static kz.epam.tcfp.foodordering.util.ValueConstants.ALL_CATEGORY_ID;

public class ShowMenuItemsByCategoryCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_MENU);
        long foodCategoryId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_CATEGORY_ID_UNDERSCORE));
        List<Food> foodList;
        List<FoodCategory> foodCategories = FoodCategoryLogic.getAll();
        if (foodCategoryId == ALL_CATEGORY_ID) {
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
