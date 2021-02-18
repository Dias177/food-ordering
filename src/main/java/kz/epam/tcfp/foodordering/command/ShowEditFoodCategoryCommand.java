package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.FoodCategory;
import kz.epam.tcfp.foodordering.logic.FoodCategoryLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public class ShowEditFoodCategoryCommand implements ActionCommand {

    private static final String PATH_PAGE_EDIT_FOOD_CATEGORY = "path.page.edit.food.category";
    private static final String FOOD_CATEGORIES = "foodCategories";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_FOOD_CATEGORY);
        List<FoodCategory> foodCategories = FoodCategoryLogic.getAll();
        req.setAttribute(FOOD_CATEGORIES, foodCategories);
        return page;
    }
}
