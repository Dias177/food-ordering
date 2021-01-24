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

public class ShowFoodDetailCommand implements ActionCommand {

    private static final String PATH_PAGE_FOOD_DETAIL = "path.page.food.detail";
    private static final String FOOD = "food";
    private static final String PARAM_NAME_FOOD_ID = "food_id";
    private static final String FOOD_CATEGORIES = "foodCategories";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_FOOD_DETAIL);
        String id = req.getParameter(PARAM_NAME_FOOD_ID);
        long idLong = Long.parseLong(id);
        Food food = FoodLogic.getFood(idLong);
        List<FoodCategory> foodCategoryList = FoodCategoryLogic.getAll();
        req.setAttribute(FOOD, food);
        req.setAttribute(FOOD_CATEGORIES, foodCategoryList);
        return page;
    }
}
