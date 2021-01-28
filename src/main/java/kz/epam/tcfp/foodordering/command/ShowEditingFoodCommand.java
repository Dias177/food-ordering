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

public class ShowEditingFoodCommand implements ActionCommand {

    private static final String PARAM_NAME_FOOD_ID = "food_id";
    private static final String PATH_PAGE_EDIT_FOOD = "path.page.edit.food";
    private static final String CURRENT_FOOD_NAME = "currentFoodName";
    private static final String CURRENT_FOOD_CATEGORY_ID = "currentFoodCategoryId";
    private static final String CURRENT_FOOD_DESCRIPTION = "currentFoodDescription";
    private static final String CURRENT_FOOD_PRICE = "currentFoodPrice";
    private static final String FOOD_CATEGORIES = "foodCategories";
    private static final String FOOD_ID = "foodId";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_FOOD);
        long foodId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_ID));
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
