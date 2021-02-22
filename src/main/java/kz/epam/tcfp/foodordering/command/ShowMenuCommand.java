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

public class ShowMenuCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_MENU);
        List<Food> foodItems = FoodLogic.getAllItems();
        List<FoodCategory> foodCategories = FoodCategoryLogic.getAll();
        req.setAttribute(CURRENT_CATEGORY, ALL);
        req.setAttribute(FOOD_ITEMS, foodItems);
        req.setAttribute(FOOD_CATEGORIES, foodCategories);
        return page;
    }
}
