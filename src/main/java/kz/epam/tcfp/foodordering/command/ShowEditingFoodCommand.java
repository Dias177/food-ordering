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
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_EDIT_FOOD;

public class ShowEditingFoodCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_FOOD);
        long foodId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_ID_UNDERSCORE));
        Food food = FoodLogic.getFood(foodId);
        List<FoodCategory> foodCategories = FoodCategoryLogic.getAll();
        req.setAttribute(CURRENT_FOOD_NAME, food.getName());
        req.setAttribute(CURRENT_FOOD_CATEGORY_ID, food.getFoodCategoryId());
        req.setAttribute(CURRENT_FOOD_DESCRIPTION, food.getDescription());
        req.setAttribute(CURRENT_FOOD_PRICE, food.getPrice());
        req.setAttribute(FOOD_CATEGORIES, foodCategories);
        req.setAttribute(FOOD_ID, foodId);
        return page;
    }
}
