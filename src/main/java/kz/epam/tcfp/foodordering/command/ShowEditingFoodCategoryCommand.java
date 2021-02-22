package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.FoodCategory;
import kz.epam.tcfp.foodordering.logic.FoodCategoryLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.FOOD_CATEGORIES;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_EDIT_FOOD_CATEGORY;

public class ShowEditingFoodCategoryCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_FOOD_CATEGORY);
        List<FoodCategory> foodCategories = FoodCategoryLogic.getAll();
        req.setAttribute(FOOD_CATEGORIES, foodCategories);
        return page;
    }
}
